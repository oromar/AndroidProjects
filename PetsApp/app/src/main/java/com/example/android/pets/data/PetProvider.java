package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class PetProvider extends ContentProvider {

    private static final String LOG_TAG = PetProvider.class.getName();

    private PetDBHelper mPetDbHelper;

    private static UriMatcher mUriMatcher;

    private static final int PETS = 100;

    private static final int PET_ID = 101;

    static {

        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    }

    @Override
    public boolean onCreate() {

        mPetDbHelper = new PetDBHelper(getContext());

        mUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, "pets", PETS);

        mUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, "pets/#", PET_ID);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        SQLiteDatabase db = mPetDbHelper.getReadableDatabase();

        Cursor cursor = null;

        if (sortOrder == null || sortOrder.isEmpty()) {

            sortOrder = "_id ASC";
        }

        switch (mUriMatcher.match(uri)) {

            case PET_ID:

                projection = PetContract.PetEntry.PROJECTION_COLUMNS;

                selection = PetContract.PetEntry._ID + "=?";

                selectionArgs = new String [] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            case PETS:

                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            default:

                throw new IllegalArgumentException("Cannot query unknown URI: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        return 0;
    }
}

