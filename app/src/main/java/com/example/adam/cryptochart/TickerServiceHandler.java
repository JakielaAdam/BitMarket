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

public class TickerServiceHandler {
    //urls
    public static final String MARKET_URL = "http://api.bitcoincharts.com/v1/markets.json";

    //JSON nodes
    public static final String TAG_VOLUME = "volume";
    public static final String TAG_LATEST_TRADE = "latest_trade";
    public static final String TAG_BID = "bid";
    public static final String TAG_HIGH = "high";
    public static final String TAG_CURRENCY = "currency";
    public static final String TAG_CURRENCY_VOL = "currency_volume";
    public static final String TAG_ASK = "ask";
    public static final String TAG_CLOSE = "close";
    public static final String TAG_SYMBOL = "symbol";
    public static final String TAG_LOW = "low";



    public TickerServiceHandler() {

    }

    public ArrayList<Market> getCurrencyList() {
        ArrayList<Market> marketList = new ArrayList<>();
        String currenciesJSON = requestCurrencies(MARKET_URL);
        JSONArray rawCurrencyList;

        //parse currency JSON here
        if(currenciesJSON != null) {
            try {
                rawCurrencyList = new JSONArray(currenciesJSON);

                for(int i = 0; i < rawCurrencyList.length(); i++) {
                    JSONObject j = rawCurrencyList.getJSONObject(i);
                    String volume = j.getString(TAG_VOLUME);
                    String latestTrade = j.getString(TAG_LATEST_TRADE);
                    String bid = j.getString(TAG_BID);
                    String high = j.getString(TAG_HIGH);
                    String currency = j.getString(TAG_CURRENCY);
                    String currencyVol = j.getString(TAG_CURRENCY_VOL);
                    String ask = j.getString(TAG_ASK);
                    String close = j.getString(TAG_CLOSE);
                    String symbol = j.getString(TAG_SYMBOL);
                    String low = j.getString(TAG_LOW);

                    if(Double.parseDouble(currencyVol) != 0) {
                        marketList.add(new Market(
                                volume, latestTrade,
                                bid, high,
                                currency, currencyVol,
                                ask, close,
                                symbol, low));

                        Log.e("hello", symbol);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("TicketServiceHandle", "No currencies found from url.");
        }

        return marketList;
    }

    private String requestCurrencies(String url) {
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
}


