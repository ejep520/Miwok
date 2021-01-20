package com.jepster.miwok;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    @ColorRes
    private final int mBackgroundColorRes;
    private MediaPlayer mMediaPlayer;
    private final AudioManager mAudioManager;
    @RequiresApi(api=Build.VERSION_CODES.O)
    private final AudioFocusRequest mAudioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build();
    private final AudioManager.OnAudioFocusChangeListener mAfListener = v -> {
        switch (v) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                mMediaPlayer.start();
                break;
            default:
                break;
        }
    };

    public final MediaPlayer.OnCompletionListener OnCompletion = new MediaPlayer.OnCompletionListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                mAudioManager.abandonAudioFocus(mAfListener);
            } else {
                mAudioManager.abandonAudioFocusRequest(mAudioFocusRequest);
            }
        }
    };

    public WordAdapter(Context context, ArrayList<Word> wordList, @ColorRes int backgroundInt) {
        super(context, 0, wordList);
        mBackgroundColorRes = backgroundInt;
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Word wordItem = getItem(position);
        final View listItemView;
        final int imageRes = wordItem.getDrawableResource();
        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        } else {
            listItemView = convertView;
        }
        RelativeLayout linearLayout = listItemView.findViewById(R.id.list_item_layout);
        ImageView imageView = listItemView.findViewById(R.id.list_image);
        TextView engWord = listItemView.findViewById(R.id.list_english);
        TextView miwWord = listItemView.findViewById(R.id.list_miwok);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mBackgroundColorRes));
        linearLayout.setOnClickListener(v -> {
            int AudioRes = wordItem.getAudioResource();
            int result;
            if (AudioRes >= 0) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    result = mAudioManager.requestAudioFocus(mAfListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                } else {
                    result = mAudioManager.requestAudioFocus(mAudioFocusRequest);
                }
            } else {
                return;
            }
            Log.v(getContext().getClass().getSimpleName(), "Current word: " + wordItem);
            mMediaPlayer = MediaPlayer.create(getContext(), AudioRes);
            mMediaPlayer.setOnCompletionListener(OnCompletion);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer.start();
            } else {
                mMediaPlayer.release();
            }
        });
        engWord.setText(wordItem.getEnglishTranslation());
        miwWord.setText(wordItem.getMiwokTranslation());
        if (imageRes < 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(imageRes);
        }
        return listItemView;
    }

}
