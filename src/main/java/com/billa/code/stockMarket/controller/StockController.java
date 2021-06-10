package com.billa.code.stockMarket.controller;

import com.billa.code.stockMarket.model.StockResponseModel;
import com.billa.code.stockMarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class StockController {
    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    StockService stockService;

    @CrossOrigin(origins = "*")
    @GetMapping(value="/getStock", produces= MediaType.APPLICATION_JSON_VALUE)
    StockResponseModel getStocks() {
        return stockService.getStockResponse();
    }
}
