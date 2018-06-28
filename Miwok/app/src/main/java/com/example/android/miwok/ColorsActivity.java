package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_colors);

        List<Translatable> words = Arrays.asList(new Translatable[] {
              new Word("red", "weṭeṭṭi", R.drawable.color_red),
              new Word("green", "chokokki", R.drawable.color_green),
              new Word("brown", "ṭakaakki", R.drawable.color_brown),
              new Word("gray", "ṭopoppi", R.drawable.color_gray),
              new Word("black", "kululli", R.drawable.color_black),
              new Word("white", "kelelli", R.drawable.color_white),
              new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow),
              new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow)
        });

        TranslatableAdapter adapter = new TranslatableAdapter(this, words);

        ListView  listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
