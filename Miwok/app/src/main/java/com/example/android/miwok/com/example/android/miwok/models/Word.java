package com.example.android.miwok.com.example.android.miwok.models;

public class Word implements Translatable {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mIconRes;
    private int mAudioRes;

    public Word(String defaultTranslation, String miwokTranslation, int iconRes, int audioRes) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mIconRes = iconRes;
        this.mAudioRes = audioRes;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioRes) {
        this(defaultTranslation, miwokTranslation, 0, audioRes);
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
        return mIconRes;
    }

    @Override
    public int getAudio() { return mAudioRes; }
}
