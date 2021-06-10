package com.billa.code.stockMarket.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class StockResponseModel {
    private String stockExg;
    private List<StockModel> stock;

    public String getStockExg() {
        return stockExg;
    }

    public void setStockExg(String stockExg) {
        this.stockExg = stockExg;
    }

    public List<StockModel> getStock() {
        return stock;
    }

    public void setStock(List<StockModel> stock) {
        this.stock = stock;
    }
}
