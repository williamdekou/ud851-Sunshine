package com.example.android.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView weatherTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        weatherTextView = (TextView) findViewById(R.id.tv_weather);
        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            weatherTextView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}
