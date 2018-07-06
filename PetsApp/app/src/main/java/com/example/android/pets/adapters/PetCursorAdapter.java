package com.example.android.pets.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.R;
import com.example.android.pets.data.PetContract;

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {

        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.petName);

        TextView breedTextView = (TextView) view.findViewById(R.id.petBreed);

        String name = cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.NAME_COLUMN_NAME));

        String breed = cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.BREED_COLUMN_NAME));

        nameTextView.setText(name);

        breedTextView.setText(breed);
    }
}
