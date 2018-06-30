package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.miwok.com.example.android.miwok.adapters.TranslatableAdapter;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;
import com.example.android.miwok.com.example.android.miwok.models.Word;

import java.util.Arrays;
import java.util.List;

public class NumbersFragment extends Fragment {

    private static final String BACKGROUND_COLOR = "#FD8E09";

    private ListView mListView;
    private List<Translatable> mWordList;
    private TranslatableAdapter mAdapter;

    public NumbersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_numbers, container, false);
    }

    @Override
    public void onResume() {

        super.onResume();

        mWordList = Arrays.asList(new Translatable[]{
                new Word("one", "lutti", R.drawable.number_one, R.raw.number_one),
                new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two),
                new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three),
                new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four),
                new Word("five", "massokka", R.drawable.number_five, R.raw.number_five),
                new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six),
                new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven),
                new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight),
                new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine),
                new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten)
        });

        mAdapter = new TranslatableAdapter(getActivity(), mWordList, BACKGROUND_COLOR);

        mListView = (ListView) getActivity().findViewById(R.id.listNumbers);

        mListView.setAdapter(mAdapter);
    }
}
