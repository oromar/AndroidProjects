package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static final String BACKGROUND_COLOR = "#FD8E09";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_numbers);

        List<Translatable> wordList = Arrays.asList(new Translatable[] {
                new Word("one", "lutti", R.drawable.number_one),
                new Word("two", "otiiko", R.drawable.number_two),
                new Word("three", "tolookosu", R.drawable.number_three),
                new Word("four", "oyyisa", R.drawable.number_four),
                new Word("five", "massokka", R.drawable.number_five),
                new Word("six", "temmokka", R.drawable.number_six),
                new Word("seven", "kenekaku", R.drawable.number_seven),
                new Word("eight", "kawinta", R.drawable.number_eight),
                new Word("nine", "wo’e", R.drawable.number_nine),
                new Word("ten", "na’aacha", R.drawable.number_ten)
        });

        TranslatableAdapter adapter = new TranslatableAdapter(this, wordList, BACKGROUND_COLOR);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
