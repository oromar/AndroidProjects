package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    private static final String BACKGROUND_COLOR = "#16AFCA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phrases);

        List<Translatable> words = Arrays.asList(new Translatable[] {
                new Word("Where are you going?", "minto wuksus"),
                new Word("What is your name?", "tinnә oyaase'nә"),
                new Word("My name is...", "oyaaset..."),
                new Word("How are you feeling?", "michәksәs?"),
                new Word("I’m feeling good.", "kuchi achit"),
                new Word("Are you coming?", "әәnәs'aa?"),
                new Word("Yes, I’m coming.", "hәә’ әәnәm"),
                new Word("I’m coming.", "әәnәm"),
                new Word("Let’s go.", "yoowutis"),
                new Word("Come here.", "әnni'nem")
        });


        TranslatableAdapter adapter = new TranslatableAdapter(this, words, BACKGROUND_COLOR);

        ListView listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
