package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        List<String> englishWords = Arrays.asList(new String [] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"});

        for (String s : englishWords) {
            Log.v(NumbersActivity.class.getName(), s);
        }
    }
}
