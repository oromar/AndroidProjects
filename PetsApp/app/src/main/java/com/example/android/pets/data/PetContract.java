package com.example.android.pets.data;

import android.provider.BaseColumns;

public final class PetContract {

    private PetContract() { }

    public static final String TEXT_COLUMN_TYPE = "TEXT";

    public static final String INTEGER_COLUMN_TYPE = "INTEGER";

    public static final class PetEntry implements BaseColumns {

        public static final String TABLE_NAME = "pets";

        public static final String NAME_COLUMN_NAME = "name";

        public static final String BREED_COLUMN_NAME = "breed";

        public static final String GENDER_COLUMN_NAME = "gender";

        public static final String WEIGHT_COLUMN_NAME = "weight";

        public static final int GENDER_MALE = 1;

        public static final int GENDER_FEMALE = 2;

        public static final int GENDER_UNKNOWN = 3;

    }
}
