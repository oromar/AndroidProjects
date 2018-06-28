package com.example.android.miwok.com.example.android.miwok.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;

import java.util.List;

public class TranslatableAdapter extends ArrayAdapter<Translatable> {

    private String mBackgroundColor;

    public TranslatableAdapter(@NonNull Context context, @NonNull List<Translatable> items, String backgroundColor) {

        super(context, R.layout.list_item, items);

        this.mBackgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Translatable currentWord = getItem(position);

        listItemView.setBackgroundColor(Color.parseColor(this.mBackgroundColor));

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokText);

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishText);

        ImageView icon = (ImageView) listItemView.findViewById(R.id.image);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        englishTextView.setText(currentWord.getDefaultTranslation());

        if (currentWord.getIcon() > 0) {
            icon.setBackgroundColor(Color.parseColor("#FFF7DA"));
            icon.setImageResource(currentWord.getIcon());
        } else {
            icon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
