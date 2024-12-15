package com.example.dresscode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use the layout for the home screen

        new Handler().postDelayed(() -> {
            // Navigate to HomeActivity
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish(); // Close MainActivity so it's not in the back stack
        }, 2000); // 2000 milliseconds = 2 seconds

        Util.setFlag(MainActivity.this, 0);

    }
}