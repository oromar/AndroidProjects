package android.example.com.visualizerpreferences;

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

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;


public class SettingsFragment extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);

        Preference sizePreference = findPreference(getString(R.string.pref_size_key));
        sizePreference.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setupSummary(p, value);
            }
        }
    }

    private void setupSummary(Preference preference, String value) {
        if (preference instanceof EditTextPreference) {
            ((EditTextPreference) preference).setSummary(value);
        } else if (preference instanceof ListPreference) {
            int index = ((ListPreference) preference).findIndexOfValue(value);
            if (index >= 0) {
                ((ListPreference) preference).setSummary(((ListPreference) preference).getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference p = findPreference(s);
        if (p != null) {
            if (!(p instanceof CheckBoxPreference)) {
                setupSummary(p, sharedPreferences.getString(p.getKey(), ""));
            }
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        Toast error = Toast.makeText(getContext(), "Please select a number between 0.1 and 3", Toast.LENGTH_SHORT);
        if (preference.getKey().equals(getString(R.string.pref_size_key))) {
            try {
                float value = Float.valueOf(String.valueOf(o));
                if (value < 0 || value > 3) {
                    error.show();
                    return false;
                }
            } catch (NumberFormatException e) {
                error.show();
                return false;
            }
        }
        return true;
    }
}