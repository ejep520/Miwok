package com.jepster.miwok;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;

public class Word {
    private final String mEnglishTranslation, mMiwokTranslation;
    private int mDrawableResource = -1, mAudioResource = -1;

    /**
     * Constructor for the class.
     *
     * @param EngWord The chosen word in English.
     * @param MiwWord The chosen word in Miwok.
     */
    Word(@NonNull String EngWord, @NonNull String MiwWord) {
        mEnglishTranslation = EngWord;
        mMiwokTranslation = MiwWord;
    }

    Word(@NonNull String EngWord, @NonNull String MinWord, @RawRes int AudioRes) {
        mEnglishTranslation = EngWord;
        mMiwokTranslation = MinWord;
        mAudioResource = AudioRes;
    }

    Word(@NonNull String EngWord, @NonNull String MinWord, @DrawableRes int DrawableResource, @RawRes int AudioRes) {
        mEnglishTranslation = EngWord;
        mMiwokTranslation = MinWord;
        mDrawableResource = DrawableResource;
        mAudioResource = AudioRes;
    }

    /**
     * @return The English word for the chosen idea.
     */
    public CharSequence getEnglishTranslation() {
        return mEnglishTranslation;
    }

    /**
     * @return The Miwok word for the chosen idea.
     */
    public CharSequence getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * @return The resource ID of the drawable of the word or idea chosen.
     */
    @DrawableRes
    public int getDrawableResource() {
        return mDrawableResource;
    }

    /**
     * @return The resource ID of the raw MP3 file for the word or idea chosen.
     */
    @RawRes
    public int getAudioResource() {
        return mAudioResource;
    }
    @NonNull
    @Override
    public String toString() {
        return "Word{ mEnglishTranslation='" + mEnglishTranslation + "', mMiwokTranslation='" +
                mMiwokTranslation + "', mAudioResource=" + mAudioResource + ", mDrawableResource=" +
                mDrawableResource + " }";
    }
}
