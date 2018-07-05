package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pets.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLES = "CREATE TABLE IF NOT EXISTS " +
            PetContract.PetEntry.TABLE_NAME + " ( " +
            PetContract.PetEntry._ID + " " + PetContract.INTEGER_COLUMN_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
            PetContract.PetEntry.NAME_COLUMN_NAME + " " + PetContract.TEXT_COLUMN_TYPE +  " NOT NULL, " +
            PetContract.PetEntry.BREED_COLUMN_NAME + " " + PetContract.TEXT_COLUMN_TYPE + ", " +
            PetContract.PetEntry.GENDER_COLUMN_NAME + " " + PetContract.INTEGER_COLUMN_TYPE + " NOT NULL, " +
            PetContract.PetEntry.WEIGHT_COLUMN_NAME + " " + PetContract.INTEGER_COLUMN_TYPE + " NOT NULL DEFAULT 0);";

    private static final String SQL_DELETE_ENTRIES = "DELETE FROM " + PetContract.PetEntry.TABLE_NAME + ";";

    public PetDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);

        onCreate(sqLiteDatabase);
    }
}
