package com.example.adam.cryptochart;

import android.os.Parcel;
import android.os.Parcelable;

public class ExchangeHistory  implements Parcelable {
        public String date;
        public double avg24hr;
        public double ask;
        public double bid;
        public double last;
        public double vol;
    public ExchangeHistory(String date, double avg24hr, double ask,
                           double bid, double last, double vol) {
        this.date = date;
        this.avg24hr = avg24hr;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.vol = vol;

    }

    //for cloning
    public ExchangeHistory(ExchangeHistory eh) {
        this.date = eh.getDate();
        this.avg24hr = eh.getAvg24hr();
        this.ask = eh.getAsk();
        this.bid = eh.getBid();
        this.last = eh.getLast();
        this.vol = eh.getVol();
    }

    public String getDate() {
        return date;
    }

    public double getAvg24hr() {
        return avg24hr;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public double getLast() {
        return last;
    }

    public double getVol() {
        return vol;
    }

    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(date);
        out.writeDouble(avg24hr);
        out.writeDouble(ask);
        out.writeDouble(bid);
        out.writeDouble(last);
        out.writeDouble(vol);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ExchangeHistory> CREATOR = new Parcelable.Creator<ExchangeHistory>() {
        public ExchangeHistory createFromParcel(Parcel in) {
            return new ExchangeHistory(in);
        }

        public ExchangeHistory[] newArray(int size) {
            return new ExchangeHistory[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ExchangeHistory(Parcel in) {
        date = in.readString();
        avg24hr = in.readDouble();
        ask = in.readDouble();
        bid = in.readDouble();
        last = in.readDouble();
        vol = in.readDouble();

    }
}
