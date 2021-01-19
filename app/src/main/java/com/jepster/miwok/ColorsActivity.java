package com.jepster.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> wordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        initArray();
        ListView listView = findViewById(R.id.colors_list);
        WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_colors);
        listView.setAdapter(wordAdapter);
    }

    private void initArray() {
        wordList.add(new Word("red", "wetetti", R.drawable.color_red,
                R.raw.color_red));
        wordList.add(new Word("green", "chokokki", R.drawable.color_green,
                R.raw.color_green));
        wordList.add(new Word("brown", "takaakki", R.drawable.color_brown,
                R.raw.color_brown));
        wordList.add(new Word("gray", "topoppi", R.drawable.color_gray,
                R.raw.color_gray));
        wordList.add(new Word("black", "kululli", R.drawable.color_black,
                R.raw.color_black));
        wordList.add(new Word("white", "kelelli", R.drawable.color_white,
                R.raw.color_white));
        wordList.add(new Word("dusty yellow", "topiise", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        wordList.add(new Word("mustard yellow", "chiwiite",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
    }
}