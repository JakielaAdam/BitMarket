package com.example.adam.cryptochart;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Adam on 4/3/2015.
 */
public class Exchange implements Parcelable{

    public String code;
    public String name;
    public String updatedAt;
    public double avg24hr;
    public double ask;
    public double bid;
    public double last;
    public double vol;
    public String url;

    public ArrayList<ExchangeHistory> history;

    public Exchange(String code, String name, String updatedAt, double avg24hr, double ask, double bid, double last, double vol, String url) {
        this.code = code;
        this.name = name;
        this.updatedAt = updatedAt;
        this.avg24hr = avg24hr;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.vol = vol;
        this.url = url;

        history = new ArrayList<>();
    }


    public void populateHistory(ArrayList<ExchangeHistory> history) {
        for(ExchangeHistory eh: history)
            this.history.add(new ExchangeHistory(eh));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUpdatedAt() {
        return updatedAt;
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

    public ArrayList<ExchangeHistory> getHistory() {
        return history;
    }

    public String getUrl() {
        return url;
    }

    // 99.9% of the time you can just ignore this
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(code);
        out.writeString(name);
        out.writeString(updatedAt);
        out.writeString(url);
        out.writeDouble(ask);
        out.writeDouble(bid);
        out.writeDouble(last);
        out.writeDouble(vol);
        out.writeList(history);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Exchange> CREATOR = new Parcelable.Creator<Exchange>() {
        public Exchange createFromParcel(Parcel in) {
            return new Exchange(in);
        }

        public Exchange[] newArray(int size) {
            return new Exchange[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Exchange(Parcel in) {
        code = in.readString();
        name = in.readString();
        updatedAt = in.readString();
        url = in.readString();
        ask = in.readDouble();
        bid = in.readDouble();
        last = in.readDouble();
        vol = in.readDouble();
        history = in.readArrayList(null);

    }
}
