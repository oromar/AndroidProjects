package com.example.android.miwok;

import android.os.BadParcelableException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import 	android.support.v4.app.NavUtils;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    private static final String BACKGROUND_COLOR = "#379237";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_family);

        List<Translatable> words = Arrays.asList(new Translatable[] {
                new Word("father", "әpә" , R.drawable.family_father, R.raw.family_mother),
                new Word("mother", "әṭa" , R.drawable.family_mother, R.raw.family_father),
                new Word("son", "angsi" , R.drawable.family_son, R.raw.family_son),
                new Word("daughter", "tune" , R.drawable.family_daughter, R.raw.family_daughter),
                new Word("older brother", "taachi" , R.drawable.family_older_brother, R.raw.family_older_brother),
                new Word("younger brother", "chalitti" , R.drawable.family_younger_brother, R.raw.family_younger_brother),
                new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister),
                new Word("younger sister", "kolliti" , R.drawable.family_younger_sister, R.raw.family_younger_sister),
                new Word("grandmother", "ama" , R.drawable.family_grandmother, R.raw.family_grandmother),
                new Word("grandfather", "paapa" , R.drawable.family_grandfather, R.raw.family_grandfather)
        });

        TranslatableAdapter adapter = new TranslatableAdapter(this, words, BACKGROUND_COLOR);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
