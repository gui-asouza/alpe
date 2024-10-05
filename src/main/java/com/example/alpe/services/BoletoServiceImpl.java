package com.example.alpe.services;

import com.example.alpe.controllers.NotaFiscalControllerImpl;
import com.example.alpe.dto.BoletoDto;
import com.example.alpe.models.BoletoModel;
import com.example.alpe.repositories.BoletoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoServiceImpl.class);

    private final BoletoRepository boletoRepository;
    public final KafkaSendService kafkaSendService;

    @Value(value = "${kafka.topic.boleto.cobranca}")
    private String boletoCobrancaTopic;

    public BoletoServiceImpl(BoletoRepository boletoRepository, KafkaSendService kafkaSendService) {
        this.boletoRepository = boletoRepository;
        this.kafkaSendService = kafkaSendService;
    }

    @Override
    public void entradaBoleto(BoletoDto boletoMessage) {
        try {
            LOGGER.info("[Boleto-Service-Impl] - Verificando se boleto já existe: NumIdentificador {}", boletoMessage.getNumIdentBoleto());
            Optional<BoletoModel> boletoModelOptional = boletoRepository
                    .findBoletoModelByNumIdentBoleto(boletoMessage.getNumIdentBoleto());

            if(boletoModelOptional.isPresent()) {
                LOGGER.error("[Boleto-Service-Impl] - Boleto NumIdentificador {} já existe", boletoMessage.getNumIdentBoleto());
            }

            LOGGER.info("[Boleto-Service-Impl] - Salvando novo boleto: {}", boletoMessage);
            BoletoModel boleto = new BoletoModel();
            boleto.setNumIdentBoleto(boletoMessage.getNumIdentBoleto());
            boleto.setDataVencimento(boletoMessage.getDataVencimento());
            boleto.setValorBoleto(boletoMessage.getValorBoleto());
            boleto.setNomePagador(boletoMessage.getNomePagador());
            boleto.setDocPagador(boletoMessage.getDocPagador());
            boleto.setNomeBeneficiario(boletoMessage.getNomeBeneficiario());
            boleto.setDocBeneficiario(boletoMessage.getDocBeneficiario());
            BoletoModel boletoModel = boletoRepository.save(boleto);

            LOGGER.info("[Boleto-Service-Impl] - Enviando boleto para topic: {}", boletoCobrancaTopic);
            kafkaSendService.sendMessage(boletoCobrancaTopic, boletoModel.toString());

        } catch (Exception e) {
            LOGGER.error("[Boleto-Service-Impl] - Erro ao processar boleto: {}", e.getMessage(), e);
         }
    }
}
