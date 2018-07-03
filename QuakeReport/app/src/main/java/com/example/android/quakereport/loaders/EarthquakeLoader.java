package com.example.android.quakereport.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.quakereport.QueryUtils;
import com.example.android.quakereport.model.IEarthquake;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<IEarthquake>> {

    public static final int LOADER_ID = 1;
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {

        super(context);

        this.mUrl = url;
    }

    @Override
    public List<IEarthquake> loadInBackground() {

        return QueryUtils.extractEarthquakes(mUrl);
    }
}
