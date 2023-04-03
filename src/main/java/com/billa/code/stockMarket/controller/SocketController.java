package com.billa.code.stockMarket.controller;
import com.billa.code.stockMarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.billa.code.stockMarket.StockMarketApplication.stocks;
import static java.util.concurrent.TimeUnit.SECONDS;

@Controller
public class SocketController {
    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    StockService stockService;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    @MessageMapping("/hello")
    public void greeting() {
        scheduler.scheduleAtFixedRate(() -> {
            template.convertAndSend("/topic/message", stocks);
        }, 0, 2, SECONDS);
    }
}
