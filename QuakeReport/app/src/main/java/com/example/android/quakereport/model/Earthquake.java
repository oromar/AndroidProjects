package com.example.android.quakereport.model;

import java.util.Date;

public class Earthquake implements IEarthquake {

    private double mMagnitude;
    private String mLocation;
    private Date mDate;
    private String mNearby;
    private String mURL;

    public Earthquake(double mMagnitude, String mLocation, Date mDate, String nearby, String url) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mNearby = nearby;
        this.mURL = url;
    }

    @Override
    public double getMagnitude() {
        return mMagnitude;
    }

    @Override
    public String getLocation() {
        return mLocation;
    }

    @Override
    public Date getDate() {
        return mDate;
    }

    @Override
    public String getNearby() {
        return mNearby;
    }

    @Override
    public String getURL() {
        return mURL;
    }
}
