package com.example.adam.cryptochart;

public class DataPoint {
    private String date;
    private long value;

    public DataPoint(String date, long value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public long getValue() {
        return value;
    }
}
