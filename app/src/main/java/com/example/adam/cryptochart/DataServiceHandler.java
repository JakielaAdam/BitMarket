package com.example.adam.cryptochart;

import android.util.Log;

import java.util.ArrayList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class DataServiceHandler {

    private static String[] URLS = {
        "http://www.quandl.com/api/v1/datasets/BAVERAGE/USD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/BITSTAMPUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/BTCEUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/BITFINEXUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/LAKEUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/HITBTCUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
        "http://www.quandl.com/api/v1/datasets/BCHARTS/ITBITUSD.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows="  };

    public static final String HISTORY_MAX = "200";

    public DataServiceHandler() {

    }

    public ArrayList<Exchange> getExchageList() {
        ArrayList<Exchange> exchangeList = new ArrayList<>();


        for(int i = 0; i < URLS.length; i++) {
            String exchangeJSON = requestJSON(URLS[i] + "1");

            //parse currency JSON here
            if (exchangeJSON != null) {
                try {
                    JSONObject obj = new JSONObject(exchangeJSON);
                    String code = obj.getString("code");
                    String name = obj.getString("name");
                    String date = obj.getString("updated_at");

                    JSONArray data = obj.getJSONArray("data");


                    JSONArray data2 = data.getJSONArray(0);

                    double avg24hr = data2.getDouble(1);
                    double ask = data2.getDouble(2);
                    double bid = data2.getDouble(3);
                    double last = data2.getDouble(4);
                    double vol = data2.getDouble(5);



                    String url = URLS[i];
                    Exchange e = new Exchange(
                            code, name, date, avg24hr, ask,
                            bid, last, vol, url);

                    exchangeList.add(e);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("TicketServiceHandle", "No currencies found from url.");
            }
        }

        return exchangeList;
    }

    public static ArrayList<ExchangeHistory> getExchangeHistory(String url) {
        ArrayList<ExchangeHistory> history = new ArrayList<>();
        String exchangeJSON = requestJSON(url + HISTORY_MAX);
        //parse currency JSON here
        if (exchangeJSON != null) {
            try {
                JSONObject obj = new JSONObject(exchangeJSON);
                JSONArray data = obj.getJSONArray("data");


                for(int i = 0; i < data.length(); i++) {
                    JSONArray data2 = data.getJSONArray(i);
                    String date = data2.getString(0);
                    double avg24hr = data2.getDouble(1);
                    double ask = data2.getDouble(2);
                    double bid = data2.getDouble(3);
                    double last = data2.getDouble(4);
                    double vol = data2.getDouble(5);
                    history.add(new ExchangeHistory(date, avg24hr,
                            ask, bid, last, vol));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("TicketServiceHandle", "No currencies found from url.");
        }
        return history;
    }

    private static String requestJSON(String url) {
        String response = null;
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;


            HttpGet httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static ArrayList<DataSet> getDataSets(String[] urls) {
        ArrayList<DataSet> dataSets = new ArrayList<>();

        for(int x = 0; x < urls.length; x++) {
            ArrayList<DataPoint> dataPoints = new ArrayList<>();
            String jsonResponse = requestJSON(URLS[x] + HISTORY_MAX);

            if (jsonResponse != null) {
                try {
                    JSONObject obj = new JSONObject(jsonResponse);
                    String title = obj.getString("name");
                    JSONArray data = obj.getJSONArray("data");


                    for(int i = 0; i < data.length(); i++) {
                        JSONArray data2 = data.getJSONArray(i);
                        String date = data2.getString(0);
                        long value = data2.getLong(1);
                        dataPoints.add(new DataPoint(date, value));
                    }

                    dataSets.add(new DataSet(title, dataPoints));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("TicketServiceHandle", "No currencies found from url.");
            }
        }
        return dataSets;
    }
}


