package com.jepster.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> wordArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        initArray();
        ListView listView = findViewById(R.id.family_list);
        WordAdapter wordAdapter = new WordAdapter(this, wordArray, R.color.category_family);
        listView.setAdapter(wordAdapter);
    }
    private void initArray() {
        wordArray.add(new Word("father", "epe", R.drawable.family_father,
                R.raw.family_father));
        wordArray.add(new Word("mother", "eta", R.drawable.family_mother,
                R.raw.family_mother));
        wordArray.add(new Word("son", "angsi", R.drawable.family_son,
                R.raw.family_son));
        wordArray.add(new Word("daughter", "tune", R.drawable.family_daughter,
                R.raw.family_daughter));
        wordArray.add(new Word("older brother", "taachi",
                R.drawable.family_older_brother, R.raw.family_older_brother));
        wordArray.add(new Word("younger brother", "chalitti",
                R.drawable.family_younger_brother, R.raw.family_younger_brother));
        wordArray.add(new Word("older sister", "tete",
                R.drawable.family_older_sister, R.raw.family_older_sister));
        wordArray.add(new Word("younger sister", "kolliti",
                R.drawable.family_younger_sister, R.raw.family_younger_sister));
        wordArray.add(new Word("grandmother", "ama",
                R.drawable.family_grandmother, R.raw.family_grandmother));
        wordArray.add(new Word("grandfather", "paapa",
                R.drawable.family_grandfather, R.raw.family_grandfather));
    }
}