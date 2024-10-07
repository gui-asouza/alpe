package com.example.alpe.services;

import com.example.alpe.dto.BoletoDto;
import com.example.alpe.models.BoletoModel;
import com.example.alpe.repositories.BoletoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoServiceImpl.class);

    @Value(value = "${kafka.topic.boleto.cobranca}")
    private String boletoCobrancaTopic;

    private final BoletoRepository boletoRepository;
    public final KafkaSendService kafkaSendService;

    public BoletoServiceImpl(BoletoRepository boletoRepository, KafkaSendService kafkaSendService) {
        this.boletoRepository = boletoRepository;
        this.kafkaSendService = kafkaSendService;
    }

    @Override
    public void entradaBoleto(BoletoDto boletoMessage) {
        try {
            LOGGER.info("[BOLETO-SERVICE-IMPL] - Verificando se boleto já existe: NumIdentificador {}", boletoMessage.getNumIdentBoleto());
            Optional<BoletoModel> boletoModelOptional = boletoRepository
                    .findBoletoModelByNumIdentBoleto(boletoMessage.getNumIdentBoleto());

            if (boletoModelOptional.isPresent()) {
                LOGGER.error("[BOLETO-SERVICE-IMPL] - Boleto NumIdentificador {} já importado", boletoMessage.getNumIdentBoleto());
            }

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

        } catch (Exception e) {
            LOGGER.error("[BOLETO-SERVICE-IMPL] - Erro ao processar boleto: {}", e.getMessage(), e);
        }
    }
}
