package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView weatherTextView;
    private String weatherOfTheDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        weatherTextView = (TextView) findViewById(R.id.tv_weather);
        Intent parentIntent = getIntent();
        if (parentIntent != null) {
            if (parentIntent.hasExtra(Intent.EXTRA_TEXT)){
                weatherOfTheDay = parentIntent.getStringExtra(Intent.EXTRA_TEXT);
                weatherTextView.setText(weatherOfTheDay);
            }

        }
    }
}