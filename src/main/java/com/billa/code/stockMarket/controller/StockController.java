package com.billa.code.stockMarket.controller;

import com.billa.code.stockMarket.model.StockResponseModel;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.billa.code.stockMarket.StockMarketApplication.stocks;

@RestController
@EnableAutoConfiguration
public class StockController {

    @CrossOrigin(origins = "*")
    @GetMapping(value="/getStock", produces= MediaType.APPLICATION_JSON_VALUE)
    List<StockResponseModel> getStocks() {
        return stocks;
    }
}
