package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SharedPreferences mSharedPreferences = null;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_screen);
        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        PreferenceScreen preferenceScreen = getPreferenceScreen();

        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (p instanceof EditTextPreference) {
                p.setSummary(((EditTextPreference) p).getText());
            } else if (p instanceof ListPreference) {
                int index = ((ListPreference) p).findIndexOfValue(((ListPreference) p).getValue());
                if (index >= 0) {
                    p.setSummary(((ListPreference) p).getEntries()[index]);
                }
            }
        }


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getActivity().getString(R.string.pref_location_key))) {
            
        } else if (key.equals(getActivity().getString(R.string.pref_units_key))) {

        }
    }

    @Override
    public void onStart() {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }
}
