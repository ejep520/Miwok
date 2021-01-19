package com.jepster.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private ArrayList<Word> wordArray = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        WordAdapter itemsAdapter = new WordAdapter(this, wordArray, R.color.category_numbers);
        initArray();
        ListView rootLayout = findViewById(R.id.list_view);
        rootLayout.setAdapter(itemsAdapter);
    }
    private void initArray() {
        wordArray.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        wordArray.add(new Word("two", "otiiko", R.drawable.number_two,
                R.raw.number_two));
        wordArray.add(new Word("three", "tolookosu", R.drawable.number_three,
                R.raw.number_three));
        wordArray.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        wordArray.add(new Word("five", "massokka", R.drawable.number_five,
                R.raw.number_five));
        wordArray.add(new Word("six", "temmokka", R.drawable.number_six,
                R.raw.number_six));
        wordArray.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        wordArray.add(new Word("eight", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        wordArray.add(new Word("nine", "wo'e", R.drawable.number_nine,
                R.raw.number_nine));
        wordArray.add(new Word("ten", "na'aacha", R.drawable.number_ten,
                R.raw.number_ten));
    }
}
