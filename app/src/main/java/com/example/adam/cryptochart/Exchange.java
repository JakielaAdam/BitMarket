package com.example.adam.cryptochart;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Stores information about exchange rates of a market.
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
    public double oneDayChng;

    public ArrayList<ExchangeHistory> history;

    public Exchange(String code, String name, String updatedAt, double avg24hr,
                    double ask, double bid, double last, double vol, String url,
                    double oneDayChng) {
        this.code = code;
        this.name = name;
        this.updatedAt = updatedAt;
        this.avg24hr = avg24hr;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.vol = vol;
        this.url = url;
        this.oneDayChng = oneDayChng;

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

    public double getOneDayChng() {
        return oneDayChng;
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


    public int describeContents() {
        return 0;
    }

    //methods below are responsible for making the class parcelable.
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
        out.writeDouble(oneDayChng);
        out.writeDouble(avg24hr);
    }

    public static final Parcelable.Creator<Exchange> CREATOR = new Parcelable.Creator<Exchange>() {
        public Exchange createFromParcel(Parcel in) {
            return new Exchange(in);
        }

        public Exchange[] newArray(int size) {
            return new Exchange[size];
        }
    };

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
        oneDayChng = in.readDouble();
        avg24hr = in.readDouble();

    }
}
