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

import java.sql.SQLData;
import java.sql.SQLInput;

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

        Cursor cursor = null;

        SQLiteDatabase db = mPetDbHelper.getReadableDatabase();

        if (sortOrder == null || sortOrder.isEmpty()) {

            sortOrder = "_id ASC";
        }

        switch (mUriMatcher.match(uri)) {

            case PET_ID:

                projection = PetContract.PetEntry.PROJECTION_COLUMNS;

                selection = PetContract.PetEntry._ID + "=?";

                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            case PETS:

                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

                break;

            default:

                throw new IllegalArgumentException("Cannot query unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (mUriMatcher.match(uri)) {

            case PET_ID:

                return PetContract.PetEntry.CONTENT_ITEM_TYPE;

            case PETS:

                return PetContract.PetEntry.CONTENT_LIST_TYPE;

            default:

                throw new IllegalArgumentException("Cannot query unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        String name = contentValues.getAsString(PetContract.PetEntry.NAME_COLUMN_NAME);

        if ( name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Name cannot be null!");
        }

        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        long id = 0;

        switch (mUriMatcher.match(uri)) {

            case PETS:

                id = (long) db.insert(PetContract.PetEntry.TABLE_NAME, null, contentValues);

                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        int deletedRows = 0;

        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        switch (mUriMatcher.match(uri)) {

            case PET_ID:

                String selection = PetContract.PetEntry._ID + "=?";

                String[] selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                deletedRows = db.delete(PetContract.PetEntry.TABLE_NAME, selection, selectionArgs);

                break;

            case PETS:

                deletedRows = db.delete(PetContract.PetEntry.TABLE_NAME, null, null);

                break;

            default:

                throw new IllegalArgumentException("Cannot query unknown URI: " + uri);
        }

        if (deletedRows > 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        int updatedRows = 0;

        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        switch (mUriMatcher.match(uri)) {

            case PET_ID:

                String selection = PetContract.PetEntry._ID + "=?";

                String[] selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                updatedRows = db.update(PetContract.PetEntry.TABLE_NAME, contentValues, selection, selectionArgs);

                break;

            case PETS:

                updatedRows = db.update(PetContract.PetEntry.TABLE_NAME, contentValues, null, null);

                break;

            default:

                throw new IllegalArgumentException("Cannot query unknown URI: " + uri);
        }

        if (updatedRows > 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updatedRows;
    }
}

