package com.billa.code.stockMarket.service;

import com.billa.code.stockMarket.model.StockModel;
import com.billa.code.stockMarket.model.StockResponseModel;
import com.billa.code.stockMarket.wrapper.StockWrapper;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    public StockWrapper findStock(String symbol) {
        try {
            return new StockWrapper(YahooFinance.get(symbol));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public StockResponseModel getStockResponse() {
        List<StockModel> stocks = new ArrayList<>();
        String[] symbolArr = {"A", "AA", "AAC", "GOOG", "AMZN", "AAT", "AAN", "T", "TD", "TARO", "TM"};

        for (String s : symbolArr) {
            Stock stock = findStock(s).getStock();
            StockModel stockModel = new StockModel(s, stock.getName(), stock.getQuote().getAsk().toString(), stock.getQuote().getChangeInPercent().toString());
            stocks.add(stockModel);
        }

        StockResponseModel res = new StockResponseModel();
        res.setStock(stocks);
        res.setStockExg("US");

        return res;
    }
}
