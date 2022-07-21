package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.trivia.databinding.ActivityMainBinding;

public class Score extends AppCompatActivity {
    private TextView scoreFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreFinal=findViewById(R.id.textViewScore);
        String sc=getIntent().getStringExtra("Score");
        scoreFinal.setText("Score: "+sc);
    }
}