package com.jepster.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Word> wordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        initArray();
        ListView listView = findViewById(R.id.phrases_list);
        WordAdapter wordAdapter = new WordAdapter(this, wordList, R.color.category_phrases);
        listView.setAdapter(wordAdapter);
    }

    private void initArray() {
        wordList.add(new Word("Where are you going?", "minto wuksus",
                R.raw.phrase_where_are_you_going));
        wordList.add(new Word("What is your name?", "tinne oyaase'ne",
                R.raw.phrase_what_is_your_name));
        wordList.add(new Word("My name is...", "oyaaset...",
                R.raw.phrase_my_name_is));
        wordList.add(new Word("How are you feeling?", "michekses?",
                R.raw.phrase_how_are_you_feeling));
        wordList.add(new Word("I'm feeling good.", "kuchi achit",
                R.raw.phrase_im_feeling_good));
        wordList.add(new Word("Are you coming?", "eenes'aa",
                R.raw.phrase_are_you_coming));
        wordList.add(new Word("Yes, I'm coming.", "hee'eenem",
                R.raw.phrase_yes_im_coming));
        wordList.add(new Word("I'm coming.", "eenem", R.raw.phrase_im_coming));
        wordList.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        wordList.add(new Word("Come here.", "enni'nem", R.raw.phrase_come_here));
    }
}