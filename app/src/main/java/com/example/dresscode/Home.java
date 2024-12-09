package com.example.dresscode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home); // Use the layout for the home screen

        // Initialize Bottom Navigation Buttons
        ImageView homeButton = findViewById(R.id.homeIcon);
        ImageView chatButton = findViewById(R.id.aiChatIcon);
        ImageView weatherButton = findViewById(R.id.weatherIcon);
        ImageView eventButton = findViewById(R.id.eventAttireIcon);
        ImageView settingButton = findViewById(R.id.profileIcon);
        Util.bottomBarJump(this, homeButton, chatButton, weatherButton, eventButton, settingButton, 1);

        // Initialize clickable elements
        ImageView moonIcon = findViewById(R.id.home_moon);
        ImageView coatIcon = findViewById(R.id.home_coat);
        ImageView rainIcon = findViewById(R.id.home_rain);
        ImageView warnIcon = findViewById(R.id.home_warn);
        ImageView checkIcon = findViewById(R.id.home_check);
        TextView temperatureText = findViewById(R.id.home_temperature_text);

        // Set click listeners to navigate to Weather activity
        View.OnClickListener navigateToWeather = view -> {
            Intent intent = new Intent(Home.this, Weather.class);
            startActivity(intent);
        };

        // Assign click listeners to the views
        moonIcon.setOnClickListener(navigateToWeather);
        coatIcon.setOnClickListener(navigateToWeather);
        rainIcon.setOnClickListener(navigateToWeather);
        warnIcon.setOnClickListener(navigateToWeather);
        checkIcon.setOnClickListener(navigateToWeather);
        temperatureText.setOnClickListener(navigateToWeather);

    }




}
