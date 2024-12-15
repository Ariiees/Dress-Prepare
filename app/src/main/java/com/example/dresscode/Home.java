package com.example.dresscode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity implements LocationHelper.LocationUpdateListener {

    private TextView titleText;
    private LocationHelper locationHelper;
    private ImageView homeAvatar;
    private TextView homeTemperatureText;
    private TextView homeFeelText;

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

        // Initialize Temperature text
        homeTemperatureText = findViewById(R.id.home_temperature_text);
        homeFeelText = findViewById(R.id.home_feel_text);

        // Initalize ImageView (updatable according to ChatAI)
        homeAvatar = findViewById(R.id.home_avatar);

        // Initialize Location elements
        ImageView locationIcon = findViewById(R.id.locationIcon);
        titleText = findViewById(R.id.titleText);

        // Initialize LocationHelper
        locationHelper = new LocationHelper(this);

        // Request location permission
        locationHelper.requestLocationPermission();

        // Retrieve temperature and apparent temperature from SharedPreferences using Util
        String temperature = Util.getTemperature(this);
        String apparentTemperature = Util.getApparentTemperature(this);

        // Update the TextViews with the retrieved data
        homeTemperatureText.setText(temperature);
        homeFeelText.setText(apparentTemperature);

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
        homeTemperatureText.setOnClickListener(navigateToWeather);

        // Request a location update
        locationIcon.setOnClickListener(view -> {
            locationHelper.fetchLocation();
            Toast.makeText(this, "Location Update!", Toast.LENGTH_SHORT).show();
        });

        updateAvatarBasedOnFlag();
    }

    // Method to update the location text in Home
    @Override
    public void updateLocationText(String location) {
        titleText.setText(location); // Update the titleText with location
    }

    // Method to update the avatar ImageView based on the flag
    private void updateAvatarBasedOnFlag() {
        // Retrieve the flag value from SharedPreferences using Util.getFlag
        int flag = Util.getFlag(this);

        // Update the home_avatar ImageView based on the flag value
        if (flag == 1) {
            homeAvatar.setImageResource(R.drawable.style1); // Set style1 image when flag is 0
        } else if (flag == 2) {
            homeAvatar.setImageResource(R.drawable.style2); // Set style2 image when flag is 1
        }
    }
}
