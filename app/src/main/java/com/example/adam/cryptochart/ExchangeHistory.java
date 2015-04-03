package com.example.adam.cryptochart;

public class ExchangeHistory {
        public String date;
        public double avg24hr;
        public double ask;
        public double bid;
        public double last;
        public double vol;
        public String url;

    public ExchangeHistory(String date, double avg24hr, double ask,
                           double bid, double last, double vol, String url) {
        this.date = date;
        this.avg24hr = avg24hr;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.vol = vol;
        this.url = url;
    }

    public String getDate() {
        return date;
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
}
