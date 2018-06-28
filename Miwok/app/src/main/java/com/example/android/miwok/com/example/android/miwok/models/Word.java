package com.example.android.miwok.com.example.android.miwok.models;

public class Word implements Translatable {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mIconPath;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int iconPath) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mIconPath = iconPath;
    }

    @Override
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    @Override
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    @Override
    public int getIcon() {
        return mIconPath;
    }
}
