package com.billa.code.stockMarket.service;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class DispatchServer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DispatchServer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String data) {
        kafkaTemplate.send("stock-market-data",data);
    }
}