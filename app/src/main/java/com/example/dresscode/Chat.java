package com.example.dresscode;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    // TODO implement chat function here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        // Initialize Bottom Navigation Buttons
        ImageView homeButton = findViewById(R.id.homeIcon);
        ImageView chatButton = findViewById(R.id.aiChatIcon);
        ImageView weatherButton = findViewById(R.id.weatherIcon);
        ImageView eventButton = findViewById(R.id.eventAttireIcon);
        Util.bottomBarJump(this, homeButton, chatButton, weatherButton, eventButton, 2);
    }
}
