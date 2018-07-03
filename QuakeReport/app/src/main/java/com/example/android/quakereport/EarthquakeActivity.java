/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.ConnectionService;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.quakereport.adapters.EarthQuakeAdapter;
import com.example.android.quakereport.loaders.EarthquakeLoader;
import com.example.android.quakereport.model.IEarthquake;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<IEarthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    private ArrayAdapter<IEarthquake> mAdapter;

    private ListView mEarthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mEarthquakeListView = (ListView) findViewById(R.id.list);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        mEarthquakeListView.setEmptyView((TextView)findViewById(R.id.empty_list));

        if (networkInfo != null && networkInfo.isConnected()) {

            mAdapter = new EarthQuakeAdapter(
                    this, new ArrayList<IEarthquake>());

            mEarthquakeListView.setAdapter(mAdapter);

            getLoaderManager().initLoader(EarthquakeLoader.LOADER_ID, null, this).forceLoad();

        } else {

            ((ProgressBar)findViewById(R.id.progress_bar)).setVisibility(View.GONE);

            ((TextView)mEarthquakeListView.getEmptyView()).setText(R.string.no_internet);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = preferences.getString(
                                getString(R.string.settings_min_magnitude_key),
                                getString(R.string.settings_min_magnitude_default));

        String minLimit = preferences.getString(
                                getString(R.string.settings_limit_key),
                                getString(R.string.settings_limit_default));

        String orderBy = preferences.getString(
                                getString(R.string.settings_order_by_key),
                                getString(R.string.settings_order_by_default));

        Uri baseUri =  Uri.parse(USGS_REQUEST_URL);

        Uri.Builder builder = baseUri.buildUpon();

        builder.appendQueryParameter("format", "geojson");
        builder.appendQueryParameter("limit", minLimit);
        builder.appendQueryParameter("minmag", minMagnitude);
        builder.appendQueryParameter("orderby", orderBy);

        return new EarthquakeLoader(EarthquakeActivity.this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<IEarthquake>> loader, List<IEarthquake> data) {

        ((ProgressBar)findViewById(R.id.progress_bar)).setVisibility(View.GONE);

        ((TextView)mEarthquakeListView.getEmptyView()).setText(R.string.no_earthquakes_found_msg);

        mAdapter.clear();

        mAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {

        mAdapter.clear();
    }

    private class EarthquakeTask extends AsyncTask<Void, Void, List<IEarthquake>> {

        @Override
        protected List<IEarthquake> doInBackground(Void... voids) {

            return QueryUtils.extractEarthquakes("");
        }

        @Override
        protected void onPostExecute(List<IEarthquake> iEarthquakes) {

            mAdapter.clear();

            mAdapter.addAll(iEarthquakes);
        }
    }

}
