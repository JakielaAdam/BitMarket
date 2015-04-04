package com.example.adam.cryptochart;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        chart = (LineChart) findViewById(R.id.chart);
       // new GetHistory().execute();
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
        private Context localContext = getApplicationContext();

        @Override
        protected Void doInBackground(Void... arg0) {
            exchange.populateHistory(DataServiceHandler.getExchangeHistory(exchange.getUrl()));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //populate the chart
            ArrayList<ExchangeHistory> history = exchange.getHistory();
            ArrayList<Entry> vals = new ArrayList<Entry>();
            ArrayList<String> xVals = new ArrayList<String>();

            int x = 0;
            for(ExchangeHistory eh: history) {
                float bid = (float) eh.getBid();
                vals.add(new Entry(bid, x));
                xVals.add("x");
                x++;
            }

            LineDataSet bidDataSet = new LineDataSet(vals, "Bid");

            LineData data = new LineData(xVals, bidDataSet);
            chart.setData(data);
            chart.invalidate(); // refresh
        }
    }
}
