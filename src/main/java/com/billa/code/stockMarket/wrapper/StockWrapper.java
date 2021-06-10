package com.billa.code.stockMarket.wrapper;

import yahoofinance.Stock;

import java.time.LocalDateTime;

public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime lastAccess;

    public StockWrapper(Stock stock) {
        this.stock = stock;
        this.lastAccess = LocalDateTime.now();
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public Stock getStock() {
        return stock;
    }
}
