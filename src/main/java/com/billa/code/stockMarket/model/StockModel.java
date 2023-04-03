package com.billa.code.stockMarket.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StockModel {
    private String symbol;
    private String name;
    private String price;
    private String chg;

    public StockModel(String symbol, String name, String price, String chg) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.chg = chg;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChg() {
        return chg;
    }

    public void setChg(String chg) {
        this.chg = chg;
    }

    public StockModel() {
    }
}
