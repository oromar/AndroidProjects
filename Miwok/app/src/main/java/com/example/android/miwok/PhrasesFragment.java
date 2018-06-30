package com.example.android.miwok;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;


public class PhrasesFragment extends Fragment {

    private static final String BACKGROUND_COLOR = "#16AFCA";

    public PhrasesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_phrases, container, false);
    }


    @Override
    public void onResume() {

        super.onResume();

        List<Translatable> words = Arrays.asList(new Translatable[] {
                new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going),
                new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name),
                new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is),
                new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling),
                new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good),
                new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming),
                new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming),
                new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming),
                new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go),
                new Word("Come here.", "әnni'nem", R.raw.phrase_come_here)
        });

        TranslatableAdapter adapter = new TranslatableAdapter(getActivity(), words, BACKGROUND_COLOR);

        ListView listView = (ListView)getActivity().findViewById(R.id.listPhrases);

        listView.setAdapter(adapter);
    }
}
