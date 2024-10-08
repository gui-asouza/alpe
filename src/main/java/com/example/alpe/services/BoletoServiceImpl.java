package com.example.alpe.services;

import com.example.alpe.dto.BoletoDto;
import com.example.alpe.exceptions.BoletoException;
import com.example.alpe.models.BoletoModel;
import com.example.alpe.models.NotaFiscalModel;
import com.example.alpe.repositories.BoletoRepository;
import com.example.alpe.repositories.NotaFiscalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoServiceImpl.class);

    @Value(value = "${kafka.topic.boleto.cobranca}")
    private String boletoCobrancaTopic;

    @Value(value = "${kafka.topic.boleto.entrada}")
    private String boletoEntradaTopic;

    private final BoletoRepository boletoRepository;
    private final NotaFiscalRepository notaFiscalRepository;
    public final KafkaSendService kafkaSendService;

    public BoletoServiceImpl(BoletoRepository boletoRepository, NotaFiscalRepository notaFiscalRepository, KafkaSendService kafkaSendService) {
        this.boletoRepository = boletoRepository;
        this.notaFiscalRepository = notaFiscalRepository;
        this.kafkaSendService = kafkaSendService;
    }

    @Override
    @Transactional
    public void entradaBoleto(BoletoDto boletoMessage) throws BoletoException {
        try {
            LOGGER.info("[BOLETO-SERVICE-IMPL] - Verificando se boleto já existe: NumIdentificador {}", boletoMessage.getNumIdentBoleto());
            Optional<BoletoModel> boletoModelOptional = boletoRepository
                    .findBoletoModelByNumIdentBoleto(boletoMessage.getNumIdentBoleto());

            if (boletoModelOptional.isPresent()) {
                LOGGER.error("[BOLETO-SERVICE-IMPL] - Boleto NumIdentificador {} já importado", boletoMessage.getNumIdentBoleto());
                throw new BoletoException(String.format("Boleto NumIdentificador %s já importado", boletoMessage.getNumIdentBoleto()));
            }

            LOGGER.info("[BOLETO-SERVICE-IMPL] - Verificando se a Nota Fiscal do boleto foi recebida");
            NotaFiscalModel notaFiscal = notaFiscalIsPresent(boletoMessage);

            if (notaFiscal != null && notaFiscal.getIsValid()) {
                LOGGER.info("[BOLETO-SERVICE-IMPL] - Salvando novo boleto: {}", boletoMessage);
                BoletoModel boleto = new BoletoModel();
                boleto.setNumIdentBoleto(boletoMessage.getNumIdentBoleto());
                boleto.setDataVencimento(boletoMessage.getDataVencimento());
                boleto.setValorBoleto(boletoMessage.getValorBoleto());
                boleto.setNomePagador(boletoMessage.getNomePagador());
                boleto.setDocPagador(boletoMessage.getDocPagador());
                boleto.setNomeBeneficiario(boletoMessage.getNomeBeneficiario());
                boleto.setDocBeneficiario(boletoMessage.getDocBeneficiario());
                BoletoModel boletoModel = boletoRepository.save(boleto);

                LOGGER.info("[BOLETO-SERVICE-IMPL] - Enviando boleto para sistema de cobrança. Topic: {}", boletoCobrancaTopic);
                kafkaSendService.sendMessage(boletoCobrancaTopic, boletoModel.toString());
            } else if (notaFiscal == null) {
                LOGGER.info("[BOLETO-SERVICE-IMPL] - Nota fiscal do boleto ainda não recebido. Enviando boleto para fila de entrada. Topic: {}", boletoEntradaTopic);
                kafkaSendService.sendMessage(boletoEntradaTopic, boletoMessage.toString());
            } else {
                throw new BoletoException(String.format("Nota fiscal %s referente ao boleto %s não é uma nota fiscal valida", notaFiscal.getNumNF(), boletoMessage.getNumIdentBoleto()));
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao processar boleto: {}", e.getMessage(), e);
            throw new BoletoException(e);
        }
    }

    private NotaFiscalModel notaFiscalIsPresent(BoletoDto boletoMessage) {
        Optional<NotaFiscalModel> notaFiscal = notaFiscalRepository.findNotaFiscalModelByDocClienteAndDocEmpresaAndValor(
                boletoMessage.getDocBeneficiario(), boletoMessage.getDocPagador(), boletoMessage.getValorBoleto());

        if (!notaFiscal.isPresent()) {
            return null;
        }
        return notaFiscal.get();
    }
}
