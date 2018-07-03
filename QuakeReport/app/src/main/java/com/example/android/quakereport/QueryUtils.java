package com.example.android.quakereport;

import android.util.Log;

import com.example.android.quakereport.model.Earthquake;
import com.example.android.quakereport.model.IEarthquake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public final class QueryUtils {

    private static final int LOCATION_FIELDS_COUNT = 2;

    private QueryUtils() {
    }

    public static ArrayList<IEarthquake> extractEarthquakes(String url) {

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        ArrayList<IEarthquake> earthquakes = new ArrayList<>();

        try {

            HttpsURLConnection urlConnection = null;
            InputStream inputStream = null;
            String json = "";

            try {
                urlConnection = (HttpsURLConnection) new URL(url).openConnection();

                urlConnection.setConnectTimeout(10000);

                urlConnection.setReadTimeout(15000);

                urlConnection.setRequestMethod("GET");

                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {

                    inputStream = urlConnection.getInputStream();

                    json = readFromStream(inputStream);

                } else {

                    return earthquakes;
                }

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }

            JSONObject reader = new JSONObject(json);

            JSONArray features = reader.getJSONArray("features");

            Earthquake quake = null;

            for (int i = 0; i < features.length(); i++) {

                JSONObject earthquake = features.getJSONObject(i);

                JSONObject properties = earthquake.getJSONObject("properties");

                String place = properties.getString("place");

                String nearby = "Near the ";

                String earthquakeURL = properties.getString("url");

                String[] locationValues = place.split("of");

                if (locationValues.length == LOCATION_FIELDS_COUNT) {

                    nearby = locationValues[0].trim();

                    place = locationValues[1].trim();
                }

                quake = new Earthquake(
                        properties.getDouble("mag"),
                        place,
                        new Date(Long.valueOf(properties.getString("time"))),
                        nearby,
                        earthquakeURL);

                earthquakes.add(quake);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return earthquakes;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}