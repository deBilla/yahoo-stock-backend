package com.billa.code.stockMarket.controller;

import com.billa.code.stockMarket.model.StockResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class StockController {
    private final List<StockResponseModel> stocks = new ArrayList<>();

    @CrossOrigin(origins = "*")
    @GetMapping(value="/getStock", produces= MediaType.APPLICATION_JSON_VALUE)
    List<StockResponseModel> getStocks() {
        return stocks;
    }

    @KafkaListener(topics = "stock-market-data", groupId = "group-3")
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
