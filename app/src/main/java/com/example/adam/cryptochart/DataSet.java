package com.example.adam.cryptochart;

import java.util.ArrayList;

//The DataSet class contains data and the title of a data query such as
//the transaction fees of bitcoin.
public class DataSet {
    private String title;
    private ArrayList<DataPoint> data;

    public DataSet(String title, ArrayList<DataPoint> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<DataPoint> getData() {
        return data;
    }
}
