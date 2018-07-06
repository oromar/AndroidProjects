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
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.android.pets.adapters.PetCursorAdapter;
import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetContract.PetEntry;
import com.example.android.pets.data.PetDBHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setEmptyView(findViewById(R.id.empty_view));

        mCursorAdapter = new PetCursorAdapter(this, null);

        listView.setAdapter(mCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

                intent.setData(ContentUris.withAppendedId(PetEntry.CONTENT_URI, id));

                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    protected void onStart() {

        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    public void insertData() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(PetEntry.NAME_COLUMN_NAME, "Toto");

        contentValues.put(PetEntry.BREED_COLUMN_NAME, "Terrier");

        contentValues.put(PetEntry.GENDER_COLUMN_NAME, PetEntry.GENDER_MALE);

        contentValues.put(PetEntry.WEIGHT_COLUMN_NAME, 7);

        getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, contentValues);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                insertData();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:

                AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getContentResolver().delete(PetEntry.CONTENT_URI, null, null);
                    }
                };

                showConfirmDeleteAllDialog(listener);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this, PetEntry.CONTENT_URI, PetEntry.PROJECTION_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mCursorAdapter.swapCursor(null);
    }

    private void showConfirmDeleteAllDialog(AlertDialog.OnClickListener positiveButtonListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.confirm_delete_pet);

        builder.setCancelable(false);

        builder.setPositiveButton(R.string.delete, positiveButtonListener);

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
