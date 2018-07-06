/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetDBHelper;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * EditText field to enter the pet's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the pet's breed
     */
    private EditText mBreedEditText;

    /**
     * EditText field to enter the pet's weight
     */
    private EditText mWeightEditText;

    /**
     * EditText field to enter the pet's gender
     */
    private Spinner mGenderSpinner;

    /**
     * Gender of the pet. The possible values are:
     * 0 for unknown gender, 1 for male, 2 for female.
     */
    private int mGender = 0;

    private long mId = 0;

    private boolean mHasChanges = false;

    private View.OnTouchListener mListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            mHasChanges = true;

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Uri uri = getIntent().getData();

        if (uri != null) {

            mId = ContentUris.parseId(uri);

            if (mId > 0) {

                this.setTitle("Edit Pet");

                getLoaderManager().initLoader(1, null, this);

            } else {

                this.setTitle("Add a Pet");

                invalidateOptionsMenu();
            }
        }

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_pet_name);
        mBreedEditText = (EditText) findViewById(R.id.edit_pet_breed);
        mWeightEditText = (EditText) findViewById(R.id.edit_pet_weight);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        mNameEditText.setOnTouchListener(mListener);
        mBreedEditText.setOnTouchListener(mListener);
        mWeightEditText.setOnTouchListener(mListener);
        mGenderSpinner.setOnTouchListener(mListener);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = PetContract.PetEntry.GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = PetContract.PetEntry.GENDER_FEMALE; // Female
                    } else {
                        mGender = PetContract.PetEntry.GENDER_UNKNOWN; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    private void savePet() {

        if (TextUtils.isEmpty(mNameEditText.getText()) &&
                TextUtils.isEmpty(mBreedEditText.getText()) &&
                TextUtils.isEmpty(mWeightEditText.getText())) {

            finish();

        } else {

            ContentValues contentValues = new ContentValues();

            contentValues.put(PetContract.PetEntry.NAME_COLUMN_NAME, mNameEditText.getText().toString());

            contentValues.put(PetContract.PetEntry.BREED_COLUMN_NAME, mBreedEditText.getText().toString());

            contentValues.put(PetContract.PetEntry.GENDER_COLUMN_NAME, mGender);

            contentValues.put(PetContract.PetEntry.WEIGHT_COLUMN_NAME, Integer.valueOf(mWeightEditText.getText().toString()));

            try {

                if (mId > 0) {

                    getContentResolver().update(ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, mId), contentValues, null, null);

                } else {

                    getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, contentValues);
                }

            } catch (Exception e) {

                Toast.makeText(this, "Error with saving pet.", LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            // Respond to a click on the "Save" menu option
            case R.id.action_save:

                savePet();

                NavUtils.navigateUpFromSameTask(this);

                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:

                DialogInterface.OnClickListener l = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getContentResolver().delete(ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, mId), null, null);

                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }
                };

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:

                if (!mHasChanges) {

                    NavUtils.navigateUpFromSameTask(EditorActivity.this);

                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Usuário clidou no botão "Discard", e navegou para a activity pai.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Mostra um diálogo que notifica o usuário de que há alterações não salvas
                showUnsavedChangesDialog(discardButtonClickListener);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (!mHasChanges) {
            super.onBackPressed();

            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicou no botão "Discard", fecha a activity atual.
                        finish();
                    }
                };

        // Mostra o diálogo que diz que há mudanças não salvas
        showUnsavedChangesDialog(discardButtonClickListener);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mId == 0) {

            MenuItem menuItem = menu.findItem(R.id.action_delete);

            menuItem.setVisible(false);
        }

        return true;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this, ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, mId), PetContract.PetEntry.PROJECTION_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {


        if (cursor.moveToFirst()) {

            mNameEditText.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.NAME_COLUMN_NAME)));

            mBreedEditText.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.BREED_COLUMN_NAME)));

            mWeightEditText.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.WEIGHT_COLUMN_NAME)));

            mGenderSpinner.setSelection(cursor.getInt(cursor.getColumnIndex(PetContract.PetEntry.GENDER_COLUMN_NAME)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mNameEditText.setText("");

        mBreedEditText.setText("");

        mWeightEditText.setText("");

        mGenderSpinner.setSelection(0);
    }


    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.unsaved_changes_dialog_msg);

        builder.setPositiveButton(R.string.discard, discardButtonClickListener);

        builder.setCancelable(false);

        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void confirmDeletePet(DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.confirm_delete_pet);

        builder.setPositiveButton(R.string.delete, discardButtonClickListener);

        builder.setCancelable(false);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }
}

