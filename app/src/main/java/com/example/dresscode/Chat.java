package com.example.dresscode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    private TextView chatAsk, chatAnswer1, chatAnswer2;
    private EditText userInput;
    private ImageButton sendButton, regerateButton, visualizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        // Initialize Bottom Navigation Buttons
        ImageView homeButton = findViewById(R.id.homeIcon);
        ImageView chatButton = findViewById(R.id.aiChatIcon);
        ImageView weatherButton = findViewById(R.id.weatherIcon);
        ImageView eventButton = findViewById(R.id.eventAttireIcon);
        ImageView profileButton = findViewById(R.id.profileIcon);
        Util.bottomBarJump(this, homeButton, chatButton, weatherButton, eventButton, profileButton, 2);

        // Initialize UI components
        chatAsk = findViewById(R.id.chatask);
        chatAnswer1 = findViewById(R.id.chatanswer1);
        chatAnswer2 = findViewById(R.id.chatanswer2);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        regerateButton = findViewById(R.id.regenerateButton);
        visualizeButton = findViewById(R.id.visualizeButton);

        // Handle "Send" button click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = userInput.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    chatAsk.setText(userMessage);
                    chatAsk.setVisibility(View.VISIBLE);
                    visualizeButton.setVisibility(View.VISIBLE);
                    regerateButton.setVisibility(View.VISIBLE);
                    chatAnswer1.setVisibility(View.VISIBLE);
                }
            }
        });



    }
}
