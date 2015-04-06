package com.example.adam.cryptochart;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

//This adapter is used for displying a DataSet object in a list.
//It shows the name of the DataSet and a graph of its DataPoints.
public class DataSetListAdapter extends ArrayAdapter<DataSet> {
    Context context;
    public DataSetListAdapter(Context context, int resource, List<DataSet> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.data_list_row, null);
        }

        DataSet ds = getItem(position);
        if(ds != null) {
            TextView titleTv = (TextView) v.findViewById(R.id.title_tv);
            titleTv.setText(ds.getTitle());

            //draw graph
            LineChart chart = (LineChart) v.findViewById(R.id.chart);

            YAxis y = chart.getAxisRight();
            y.setEnabled(false);

            chart.setHighlightEnabled(true);
            chart.setBackgroundColor(Color.rgb(0, 92, 155));
            chart.setNoDataTextDescription("Loading data");
            chart.setBorderColor(Color.rgb(77, 208, 225));

            ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
            ArrayList<String> xVals = new ArrayList<String>();

            //get the datapoints from the object
            ArrayList<DataPoint> dataPoints = ds.getData();
            for(int x = 0; x < dataPoints.size(); x++) {
                DataPoint dp = dataPoints.get(x);
                Entry e = new Entry((float) dp.getValue(), x);
                valsComp1.add(e);
                xVals.add(dp.getDate());
            }

            LineDataSet setComp1 = new LineDataSet(valsComp1, ds.getTitle());

            //set other parameters of the graph.
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
            chart.invalidate();
        }
        return v;
    }
}
