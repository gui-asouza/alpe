package com.example.alpe.listeners;

import com.example.alpe.dto.BoletoDto;
import com.example.alpe.services.BoletoService;
import com.example.alpe.services.BoletoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BoletoNotificationListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoNotificationListener.class);

    private BoletoService boletoService;

    public BoletoNotificationListener(BoletoServiceImpl boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = {"${kafka.topic.boleto.entrada}"})
    public void entradaBoletoNotification(BoletoDto message) {
        LOGGER.info("Recebido boleto notification: {}", message);
        boletoService.entradaBoleto(message);
    }
}
