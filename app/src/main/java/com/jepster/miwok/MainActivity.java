package com.jepster.miwok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView TempView = findViewById(R.id.colors);
        TempView.setOnClickListener(view -> {
            Intent openColors = new Intent(view.getContext(), ColorsActivity.class);
            startActivity(openColors);

        });
        TempView = findViewById(R.id.family);
        TempView.setOnClickListener(view -> {
            Intent openFamily = new Intent(view.getContext(), FamilyActivity.class);
            startActivity(openFamily);
        });
        TempView = findViewById(R.id.numbers);
        TempView.setOnClickListener(view -> {
            Intent openNumbers = new Intent(view.getContext(), NumbersActivity.class);
            startActivity(openNumbers);
        });
        TempView = findViewById(R.id.phrases);
        TempView.setOnClickListener(view -> {
            Intent openPhrases = new Intent(view.getContext(), PhrasesActivity.class);
            startActivity(openPhrases);
        });
    }
}