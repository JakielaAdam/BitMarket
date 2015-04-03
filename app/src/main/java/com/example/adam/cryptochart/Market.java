package com.example.adam.cryptochart;

public class Market {
    private String volume;
    private String latest_trade;
    private String bid;
    private String high;
    private String currency;
    private String currencty_vol;
    private String ask;
    private String close;
    private String symbol;
    private String low;


    public Market(String volume, String latest_trade, String bid, String high, String currency, String currencty_vol, String ask, String close, String symbol, String low) {
        this.volume = volume;
        this.latest_trade = latest_trade;
        this.bid = bid;
        this.high = high;
        this.currency = currency;
        this.currencty_vol = currencty_vol;
        this.ask = ask;
        this.close = close;
        this.symbol = symbol;
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public String getLatest_trade() {
        return latest_trade;
    }

    public String getBid() {
        return bid;
    }

    public String getHigh() {
        return high;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencty_vol() {
        return currencty_vol;
    }

    public String getAsk() {
        return ask;
    }

    public String getClose() {
        return close;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLow() {
        return low;
    }
}
