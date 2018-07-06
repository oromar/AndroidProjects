package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {

    private PetContract() { }

    public static final String CONTENT_AUTHORITY = "com.example.android.pets";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String TEXT_COLUMN_TYPE = "TEXT";

    public static final String INTEGER_COLUMN_TYPE = "INTEGER";

    public static final class PetEntry implements BaseColumns {

        public static final String TABLE_NAME = "pets";

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);

        public static final String NAME_COLUMN_NAME = "name";

        public static final String BREED_COLUMN_NAME = "breed";

        public static final String GENDER_COLUMN_NAME = "gender";

        public static final String WEIGHT_COLUMN_NAME = "weight";

        public static final int GENDER_MALE = 1;

        public static final int GENDER_FEMALE = 2;

        public static final int GENDER_UNKNOWN = 3;

        public static final String[] PROJECTION_COLUMNS =
                new String [] { _ID, NAME_COLUMN_NAME, BREED_COLUMN_NAME, GENDER_COLUMN_NAME, WEIGHT_COLUMN_NAME };

    }
}
