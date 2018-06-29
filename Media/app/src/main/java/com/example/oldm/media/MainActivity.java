package com.example.oldm.media;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mMediaPlayer;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(this);

        findViewById(R.id.pause).setOnClickListener(this);

        findViewById(R.id.stop).setOnClickListener(this);

        mRadioGroup = (RadioGroup)findViewById(R.id.group);
    }

    @Override
    public void onClick(View view) {

        if (mMediaPlayer == null) {

            mMediaPlayer = MediaPlayer.create(this, mRadioGroup.getCheckedRadioButtonId() == R.id.sad ?  R.raw.sad : R.raw.love);
        }

        switch(view.getId()) {

            case R.id.play:
                mMediaPlayer.start();
                mRadioGroup.setEnabled(false);
                break;

            case R.id.pause:
                mMediaPlayer.pause();
                break;

            case R.id.stop:
                mRadioGroup.setEnabled(true);
                destroyMediaPlayer();
                break;
        }
    }

    private void destroyMediaPlayer() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}

