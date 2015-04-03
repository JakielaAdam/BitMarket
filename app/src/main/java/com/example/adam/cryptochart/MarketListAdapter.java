package com.example.adam.cryptochart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MarketListAdapter extends ArrayAdapter<Market> {
    Context context;
    public MarketListAdapter(Context context, int resource, List<Market> items) {
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

        Market c = getItem(position);
        if(c != null) {
            //populate the view
            TextView nameTv = (TextView) v.findViewById(R.id.name_tv);
            TextView codeTv = (TextView) v.findViewById(R.id.code_tv);

            nameTv.setText(c.getSymbol());
            codeTv.setText(c.getVolume());
        }


        return v;
    }
}
