package com.billa.code.stockMarket;

import com.billa.code.stockMarket.model.StockResponseModel;
import com.billa.code.stockMarket.service.DispatchServer;
import com.billa.code.stockMarket.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class StockMarketApplication implements CommandLineRunner {
    private final DispatchServer dispatchServer;

    @Autowired
    StockService stockService;

    public StockMarketApplication(DispatchServer dispatchServer) {
        this.dispatchServer = dispatchServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(StockMarketApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                StockResponseModel stock = stockService.getStockResponse();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String json = objectMapper.writeValueAsString(stock);
                    dispatchServer.publish(json);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1000 * 30); // Schedule the task to run every 1 second
    }
}
