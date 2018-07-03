package com.example.android.quakereport.model;

import java.util.Date;

public interface IEarthquake {

    double getMagnitude();

    String getLocation();

    Date getDate();

    String getNearby();

    String getURL();
}
