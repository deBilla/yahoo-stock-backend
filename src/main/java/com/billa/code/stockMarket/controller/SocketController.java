package com.billa.code.stockMarket.controller;
import com.billa.code.stockMarket.model.StockResponseModel;
import com.billa.code.stockMarket.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

@Controller
public class SocketController {
    @Autowired
    SimpMessagingTemplate template;

    private final List<StockResponseModel> stocks = new ArrayList<>();

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    @MessageMapping("/hello")
    public void greeting() {
        scheduler.scheduleAtFixedRate(() -> {
            template.convertAndSend("/topic/message", stocks);
        }, 0, 2, SECONDS);
    }

    @KafkaListener(topics = "stock-market-data", groupId = "group-2")
    public void listen(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StockResponseModel stockResponseModel = objectMapper.readValue(message, StockResponseModel.class);
            stocks.clear();
            stocks.add(stockResponseModel);
            System.out.println("Received Messasge in group - group-id: " + stockResponseModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
