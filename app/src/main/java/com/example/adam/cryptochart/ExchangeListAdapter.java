package com.example.adam.cryptochart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ExchangeListAdapter extends ArrayAdapter<Exchange> {
    Context context;
    public ExchangeListAdapter(Context context, int resource, List<Exchange> items) {
        super(context, resource, items);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.market_list_row, null);
        }

        Exchange e = getItem(position);
        if(e != null) {
            //populate the view
            TextView nameTv = (TextView) v.findViewById(R.id.name_tv);
            TextView priceTv = (TextView) v.findViewById(R.id.price_tv);

            nameTv.setText(e.getCode());
            priceTv.setText("24 hr: " + Double.toString(e.getAvg24hr()));
        }


        return v;
    }
}
