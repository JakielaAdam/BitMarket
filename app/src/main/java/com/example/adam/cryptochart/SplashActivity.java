package com.example.adam.cryptochart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        TextView splashTv = (TextView) findViewById(R.id.titleTv);
        Typeface starAvenue = Typeface.createFromAsset(getAssets(), "fonts/staravenue.ttf");
        splashTv.setTypeface(starAvenue);
        new GetExchanges().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    private class GetExchanges extends AsyncTask<Void, Void, Void> {
        private ArrayList<Exchange> exchangeList;

        @Override
        protected Void doInBackground(Void... arg0) {
            DataServiceHandler tsh = new DataServiceHandler();
            exchangeList = tsh.getExchageList();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putParcelableArrayListExtra("exchangeList", exchangeList);
            startActivity(i);
        }
    }
}
