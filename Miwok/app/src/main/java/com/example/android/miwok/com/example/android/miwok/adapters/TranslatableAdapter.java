package com.example.android.miwok.com.example.android.miwok.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

    public TranslatableAdapter(@NonNull Context context, @NonNull List<Translatable> items) {

        super(context, R.layout.list_item, items);
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

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokText);

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishText);

        ImageView icon = (ImageView) listItemView.findViewById(R.id.image);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        englishTextView.setText(currentWord.getDefaultTranslation());

        if (currentWord.getIcon() > 0) {
            icon.setImageResource(currentWord.getIcon());
        }

        return listItemView;
    }
}
