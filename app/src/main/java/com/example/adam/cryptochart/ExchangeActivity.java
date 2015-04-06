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
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class ExchangeActivity extends ActionBarActivity {

    private Exchange exchange;
    private LineChart chart;
    private ArrayList<ExchangeHistory> history;
    private TextView nameTv, hr24Tv, bidTv, askTv, lastTv, volTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        nameTv = (TextView) findViewById(R.id.name_tv);
        hr24Tv = (TextView) findViewById(R.id.hr_24_avg_tv);
        bidTv = (TextView) findViewById(R.id.bid_tv);
        askTv = (TextView) findViewById(R.id.ask_tv);
        lastTv = (TextView) findViewById(R.id.last_tv);
        volTv = (TextView) findViewById(R.id.vol_tv);



        Bundle extras = getIntent().getExtras();
        exchange = extras.getParcelable("exchange");

        nameTv.setText(exchange.getName());
        hr24Tv.setText("24 hr average: " + exchange.getAvg24hr());
        bidTv.setText("Bid: " + exchange.getBid());
        askTv.setText("Ask: " + exchange.getAsk());
        lastTv.setText("Last: " + exchange.getLast());
        volTv.setText("Volume: " + exchange.getVol());
        chart = (LineChart) findViewById(R.id.chart);

        YAxis y = chart.getAxisRight();
        y.setEnabled(false);

        chart.setHighlightEnabled(true);
        chart.setBackgroundColor(Color.rgb(0, 92, 155));
        chart.setNoDataTextDescription("Loading data");
        chart.setBorderColor(Color.rgb(77, 208, 225));
        chart.setDescription(exchange.getName());

        new GetHistory().execute();

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
            setComp1.setDrawCubic(true);
            setComp1.setDrawCircles(false);
            setComp1.setLineWidth(3f);
            setComp1.setHighLightColor(Color.rgb(244, 117, 117));
            setComp1.setColor(Color.rgb(104, 241, 175));
            setComp1.setFillColor(ColorTemplate.getHoloBlue());
            setComp1.setDrawFilled(true);
            ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
            dataSets.add(setComp1);


            LineData data = new LineData(xVals, dataSets);
            chart.setData(data);
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
    }
}
