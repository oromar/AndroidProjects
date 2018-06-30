package com.example.android.miwok.com.example.android.miwok.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.android.miwok.ColorsFragment;
import com.example.android.miwok.FamilyFragment;
import com.example.android.miwok.NumbersFragment;
import com.example.android.miwok.PhrasesFragment;
import com.example.android.miwok.R;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Log.v(this.getClass().getName(), "Numbers Fragment");
                return new NumbersFragment();

            case 1:
                Log.v(this.getClass().getName(), "Colors Fragment");
                return new ColorsFragment();

            case 2:
                Log.v(this.getClass().getName(), "Family Fragment");
                return new FamilyFragment();

            case 3:
                Log.v(this.getClass().getName(), "Phrases Fragment");
                return new PhrasesFragment();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Numbers";

            case 1:
                return "Colors";

            case 2:
                return "Family";

            case 3:
                return "Phrases";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
