package com.example.adam.cryptochart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            ImageView arrowView = (ImageView) v.findViewById(R.id.arrow_image_view);
            if(e.getOneDayChng() > 0) {
                arrowView.setImageResource(R.drawable.arrow_up);
            } else {
                arrowView.setImageResource(R.drawable.arrow_down);
            }
            TextView nameTv = (TextView) v.findViewById(R.id.name_tv);
            TextView priceTv = (TextView) v.findViewById(R.id.price_tv);
            TextView volTv = (TextView) v.findViewById(R.id.vol_tv);

            nameTv.setText(e.getCode());
            priceTv.setText("24h Average: " + Double.toString(e.getAsk()));
            volTv.setText("Volume: " + Double.toString(e.getVol()));
        }


        return v;
    }
}
