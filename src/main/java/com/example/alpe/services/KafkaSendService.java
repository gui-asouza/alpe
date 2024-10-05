package com.example.alpe.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSendService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSendService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        LOGGER.info("[Kafka-Send-Service]- Sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
