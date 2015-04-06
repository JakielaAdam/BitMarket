package com.example.adam.cryptochart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static ArrayList<Exchange> exchangeList;

    //URLs used for the Market Activity fragment.
    private static final String[] ACTIVITY_URLS = {
            "http://www.quandl.com/api/v1/datasets/BCHAIN/ETRAV.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
            "http://www.quandl.com/api/v1/datasets/BCHAIN/TRVOU.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows="
    };

    //URLs used for the transaction fees fragment.
    private static final String[] TRANSACTION_FEES_URLS = {
            "http://www.quandl.com/api/v1/datasets/BCHAIN/TRFEE.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
            "http://www.quandl.com/api/v1/datasets/BCHAIN/TRFUS.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
            "http://www.quandl.com/api/v1/datasets/BCHAIN/CPTRA.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows="
    };

    //URLs used for the mining information fragment.
    private static final String[] MINING_INFO_URLS = {
            "http://www.quandl.com/api/v1/datasets/BCHAIN/BLCHS.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows=",
            "http://www.quandl.com/api/v1/datasets/BCHAIN/TOUTV.json?auth_token=AEVNyGaVAWU4swfqp5zk&rows="
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        Bundle extras = getIntent().getExtras();
        exchangeList  = extras.getParcelableArrayList("exchangeList");

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(position + 1))
                    .commit();
                break;
            case 1:
                //activity
                Log.d("MAIN", "ActivityFragment");
                fragmentManager.beginTransaction()
                    .replace(R.id.container, ActivityFragment.newInstance(position + 1))
                    .commit();
                break;
            case 2:
                //transaction fees
                Log.d("MAIN", "TransactionsFragment");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, TransactionsFragment.newInstance(position + 1))
                        .commit();
                break;
            case 3:
                //mining info
                Log.d("MAIN", "MiningInfoFragment");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MiningInfoFragment.newInstance(position + 1))
                        .commit();
                break;
            case 4:
                //about
                Log.d("MAIN", "AboutFragment");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AboutFragment.newInstance(position + 1))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //main fragment displayed at application start.
    public static class MainFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ListView currencyLv;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MainFragment newInstance(int sectionNumber) {
            MainFragment fragment = new MainFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public MainFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            currencyLv = (ListView) rootView.findViewById(R.id.quote_lv);
            final Context localContext = getActivity().getApplicationContext();



            ListAdapter adapter = new ExchangeListAdapter(
                    localContext,
                    R.layout.market_list_row,
                    exchangeList);

            currencyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(localContext, ExchangeActivity.class);
                    intent.putExtra("exchange", exchangeList.get(position));
                    startActivity(intent);
                }
            });


            currencyLv.setAdapter(adapter);


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

    //fragment which displays Bitcoin market activity.
    public static class ActivityFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private ListView lv;
        private ProgressBar pb;
        private TextView tv;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ActivityFragment newInstance(int sectionNumber) {
            ActivityFragment fragment = new ActivityFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ActivityFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bitcoin, container, false);
            lv = (ListView) rootView.findViewById(R.id.data_set_list_view);

            //display the progress bar and hides the list view.
            pb = (ProgressBar) rootView.findViewById(R.id.activity_pb);
            lv.setVisibility(View.INVISIBLE);

            //gets the market activity data.
            new GetActivityData().execute();

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        private class GetActivityData extends AsyncTask<Void, Void, Void> {
            private ArrayList<DataSet> dataSets;

            @Override
            protected Void doInBackground(Void... arg0) {
                dataSets = DataServiceHandler.getDataSets(ACTIVITY_URLS);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                final Context localContext = getActivity().getApplicationContext();
                ListAdapter adapter = new DataSetListAdapter(
                        localContext,
                        R.layout.data_list_row,
                        dataSets);

                //populate and show the list view upon data load.
                lv.setAdapter(adapter);
                pb.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
            }
        }
    }

    //fragment which displays transaction fee information.
    public static class TransactionsFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        private ListView lv;
        private ProgressBar pb;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TransactionsFragment newInstance(int sectionNumber) {
            TransactionsFragment fragment = new TransactionsFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public TransactionsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_transaction_fees, container, false);
            lv = (ListView) rootView.findViewById(R.id.transaction_fees_list_view);
            pb = (ProgressBar) rootView.findViewById(R.id.transaction_fees__pb);
            lv.setVisibility(View.INVISIBLE);
            new GetTransactionData().execute();

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        private class GetTransactionData extends AsyncTask<Void, Void, Void> {
            private ArrayList<DataSet> dataSets;

            @Override
            protected Void doInBackground(Void... arg0) {
                dataSets = DataServiceHandler.getDataSets(TRANSACTION_FEES_URLS);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                final Context localContext = getActivity().getApplicationContext();
                ListAdapter adapter = new DataSetListAdapter(
                        localContext,
                        R.layout.data_list_row,
                        dataSets);

                lv.setAdapter(adapter);
                pb.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
            }
        }

    }

    //fragment which displayes mining information.
    public static class MiningInfoFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private ListView lv;
        private ProgressBar pb;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MiningInfoFragment newInstance(int sectionNumber) {
            MiningInfoFragment fragment = new MiningInfoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public MiningInfoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_mining_info, container, false);
            lv = (ListView) rootView.findViewById(R.id.mining_info_list_view);
            pb = (ProgressBar) rootView.findViewById(R.id.mining_info_pb);
            lv.setVisibility(View.INVISIBLE);
            new GetMiningData().execute();

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        private class GetMiningData extends AsyncTask<Void, Void, Void> {
            private ArrayList<DataSet> dataSets;

            @Override
            protected Void doInBackground(Void... arg0) {
                dataSets = DataServiceHandler.getDataSets(MINING_INFO_URLS);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                final Context localContext = getActivity().getApplicationContext();
                ListAdapter adapter = new DataSetListAdapter(
                        localContext,
                        R.layout.data_list_row,
                        dataSets);

                lv.setAdapter(adapter);
                pb.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
            }
        }

    }

    public static class AboutFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static AboutFragment newInstance(int sectionNumber) {
            AboutFragment fragment = new AboutFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public AboutFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_about, container, false);


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }
}
