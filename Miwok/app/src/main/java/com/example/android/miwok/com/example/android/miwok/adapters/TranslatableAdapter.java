package com.example.android.miwok.com.example.android.miwok.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.com.example.android.miwok.models.Translatable;

import java.io.IOException;
import java.util.List;

public class TranslatableAdapter extends ArrayAdapter<Translatable> {

    private String mBackgroundColor;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    public TranslatableAdapter(@NonNull Context context, @NonNull List<Translatable> items, String backgroundColor) {

        super(context, R.layout.list_item, items);

        this.mBackgroundColor = backgroundColor;

        this.mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Translatable currentWord = getItem(position);

        listItemView.setBackgroundColor(Color.parseColor(this.mBackgroundColor));

        listItemView.setClickable(true);

        final View self = listItemView;

        final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {

                switch (focusChange) {

                    case AudioManager.AUDIOFOCUS_GAIN:

                        if (mMediaPlayer == null) {

                            mMediaPlayer = MediaPlayer.create(getContext(), currentWord.getAudio());

                            mMediaPlayer.start();

                        } else {

                            if (!mMediaPlayer.isPlaying()) {

                                mMediaPlayer.start();
                            }
                        }
                        break;

                    case AudioManager.AUDIOFOCUS_LOSS:

                        if ((mMediaPlayer != null) && (mMediaPlayer.isPlaying())) {

                            mMediaPlayer.stop();
                        }
                        break;

                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:

                        if ((mMediaPlayer != null) && (mMediaPlayer.isPlaying())) {

                            mMediaPlayer.pause();
                        }
                        break;

                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:

                        if ((mMediaPlayer != null) && (mMediaPlayer.isPlaying())) {

                            mMediaPlayer.setVolume(0.2f, 0.2f);
                        }
                        break;
                }
            }
        };

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMediaPlayer = MediaPlayer.create(getContext(), currentWord.getAudio());

                self.setClickable(false);

                int resultFocus = mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (resultFocus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer.start();
                }

                mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);

                self.setClickable(true);
            }
        });

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokText);

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishText);

        ImageView icon = (ImageView) listItemView.findViewById(R.id.image);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        englishTextView.setText(currentWord.getDefaultTranslation());

        if (currentWord.getIcon() > 0) {

            icon.setBackgroundColor(Color.parseColor("#FFF7DA"));

            icon.setImageResource(currentWord.getIcon());

        } else {

            icon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
