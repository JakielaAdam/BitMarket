package com.example.adam.cryptochart;

import java.util.ArrayList;

/**
 * Created by Adam on 4/3/2015.
 */
public class Exchange {

    public String code;
    public String name;
    public String updatedAt;
    public double avg24hr;
    public double ask;
    public double bid;
    public double last;
    public double vol;
    public String url;

    public ArrayList<ExchangeHistory> history;

    public Exchange(String code, String name, String updatedAt, double avg24hr, double ask, double bid, double last, double vol, String url) {
        this.code = code;
        this.name = name;
        this.updatedAt = updatedAt;
        this.avg24hr = avg24hr;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.vol = vol;
        this.url = url;

        history = new ArrayList<>();
    }

    public void populateHistory() {

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public double getAvg24hr() {
        return avg24hr;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public double getLast() {
        return last;
    }

    public double getVol() {
        return vol;
    }

    public ArrayList<ExchangeHistory> getHistory() {
        return history;
    }

    public String getUrl() {
        return url;
    }
}
