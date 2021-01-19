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
    private MediaPlayer mediaPlayer;
    private final AudioManager audioManager;
    private final AudioManager.OnAudioFocusChangeListener afListener = v -> {
        switch (v) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
                break;
            case AudioManager.AUDIOFOCUS_GAIN:
                mediaPlayer.start();
                break;
        }
    };
    public WordAdapter(Context context, ArrayList<Word> wordList, @ColorRes int backgroundInt) {
        super(context, 0, wordList);
        mBackgroundColorRes = backgroundInt;
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word wordItem = getItem(position);
        View listItemView;
        int imageRes;
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
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick (View view){
                int AudioRes = wordItem.getAudioResource();
                int result = AudioManager.AUDIOFOCUS_REQUEST_FAILED;
                if (AudioRes >= 0) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        result = audioManager.requestAudioFocus(afListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        result = audioManager.requestAudioFocus(new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build());
                    }
                }
                Log.v(getContext().getClass().getSimpleName(), "Current word: " + wordItem);
                mediaPlayer = MediaPlayer.create(getContext(), AudioRes);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                            audioManager.abandonAudioFocus(afListener);
                        } else {
                            audioManager.abandonAudioFocusRequest(new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT).build());
                        }
                    }
                });
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer.start();
                } else {
                    mediaPlayer.release();
                }
            }
        });
        engWord.setText(wordItem.getEnglishTranslation());
        miwWord.setText(wordItem.getMiwokTranslation());
        imageRes = wordItem.getDrawableResource();
        if (imageRes < 0)
        {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(imageRes);
        }
        return listItemView;
    }

}
