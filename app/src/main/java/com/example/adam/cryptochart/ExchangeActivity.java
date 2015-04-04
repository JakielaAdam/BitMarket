package com.example.adam.cryptochart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class ExchangeActivity extends ActionBarActivity {

    private Exchange exchange;
    private LineChart chart;
    private ArrayList<ExchangeHistory> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        chart = (LineChart) findViewById(R.id.chart);
        //chart.getXAxis().setEnabled(false);
        chart.setBackgroundColor(Color.rgb(1, 87, 155));
        chart.setNoDataTextDescription("Loading data");

        chart.setBorderColor(Color.rgb(77, 208, 225));


        Bundle extras = getIntent().getExtras();
        exchange = extras.getParcelable("exchange");
        new GetHistory().execute();



        //populate other fields

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exchange, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetHistory extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... arg0) {
            history = DataServiceHandler.getExchangeHistory(exchange.getUrl());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //populate the graph
            ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
            ArrayList<String> xVals = new ArrayList<String>();

            for(int x = 0; x < history.size(); x++) {
                ExchangeHistory eh = history.get(x);
                Entry e = new Entry((float) eh.getBid(), x);
                Log.e("ExchangeActivity", Double.toString(eh.getBid()));
                valsComp1.add(e);
                xVals.add(eh.getDate());
            }


            LineDataSet setComp1 = new LineDataSet(valsComp1, exchange.getName());
            ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
            dataSets.add(setComp1);


            LineData data = new LineData(xVals, dataSets);
            chart.setData(data);
            chart.invalidate();
        }
    }
}
