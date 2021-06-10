package com.billa.code.stockMarket.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StockModel {
    private final String symbol;
    private final String name;
    private final String price;
    private final String chg;

    public StockModel(String symbol, String name, String price, String chg) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.chg = chg;
    }
}
