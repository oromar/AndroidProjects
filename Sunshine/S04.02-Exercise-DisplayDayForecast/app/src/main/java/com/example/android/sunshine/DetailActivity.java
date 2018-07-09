package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        mWeatherTextView = (TextView) findViewById(R.id.tv_data);

        mWeatherTextView.setText(text);

        // TODO (2) Display the weather forecast that was passed from MainActivity
    }
}