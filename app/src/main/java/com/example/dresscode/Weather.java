package com.example.dresscode;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Weather extends AppCompatActivity implements LocationHelper.LocationUpdateListener {

    private TextView locationText;
    private LocationHelper locationHelper;
    // TODO implement weather function here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        // Initialize Bottom Navigation Buttons
        // Initialize Bottom Navigation Buttons
        ImageView homeButton = findViewById(R.id.homeIcon);
        ImageView chatButton = findViewById(R.id.aiChatIcon);
        ImageView weatherButton = findViewById(R.id.weatherIcon);
        ImageView eventButton = findViewById(R.id.eventAttireIcon);
        ImageView settingButton = findViewById(R.id.profileIcon);
        Util.bottomBarJump(this, homeButton, chatButton, weatherButton, eventButton, settingButton, 3);

        // Initialize locationText (Make sure you add a TextView in your weather layout to show location)
        locationText = findViewById(R.id.titleText);

        // Initialize LocationHelper
        locationHelper = new LocationHelper(this);

        // Request location permission and fetch location
        locationHelper.requestLocationPermission();
    }

    @Override
    public void updateLocationText(String location) {
        locationText.setText(location); // Update the locationText with location
    }
}
