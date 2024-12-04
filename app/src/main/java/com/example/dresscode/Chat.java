package com.example.dresscode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    private TextView chatBotResponse;
    private EditText userInput;
    private Button sendButton, alternateSuggestionButton;
    private String lastQuestion = "";
    private String weatherCondition = "";

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
        chatBotResponse = findViewById(R.id.chatBotResponse);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);
        alternateSuggestionButton = findViewById(R.id.alternateSuggestionButton);

        // Handle "Send" button click
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = userInput.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    userInput.setText(""); // Clear input field
                    handleUserMessage(userMessage);
                }
            }
        });

        // Handle "Get Alternate Suggestion" button click
        alternateSuggestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weatherCondition.isEmpty()) {
                    provideAlternateSuggestion(weatherCondition);
                } else {
                    chatBotResponse.setText("Please tell me the weather condition first to get an alternate suggestion.");
                }
            }
        });

    }
    // Handle the user's message and respond accordingly
    private void handleUserMessage(String message) {
        String response;

        // Main flow based on user input
        if (message.equalsIgnoreCase("what do I wear today?")) {
            lastQuestion = "weather inquiry";
            response = "Can you tell me the weather condition? Is it sunny, cold, or raining?";
        } else if (lastQuestion.equals("weather inquiry")) {
            weatherCondition = message.toLowerCase();
            response = getClothingSuggestion(weatherCondition);
        } else {
            response = "Sorry, I can only help with clothing suggestions based on weather conditions.";
        }

        chatBotResponse.setText(response);
    }

    // Provide clothing suggestions based on weather
    private String getClothingSuggestion(String condition) {
        switch (condition) {
            case "sunny":
                return "It’s sunny today. Wear a light t-shirt, shorts, and sunglasses.";
            case "cold":
                return "It’s cold outside. Wear a sweater, coat, and gloves.";
            case "raining":
                return "It’s raining. Wear a waterproof jacket, boots, and carry an umbrella.";
            default:
                return "I didn’t understand that. Please say sunny, cold, or raining.";
        }
    }

    // Provide alternate suggestions for the weather condition
    private void provideAlternateSuggestion(String condition) {
        String alternateResponse;

        switch (condition) {
            case "sunny":
                alternateResponse = "Alternatively, wear a cotton shirt and linen pants with a hat.";
                break;
            case "cold":
                alternateResponse = "Alternatively, layer up with a thermal jacket and a scarf.";
                break;
            case "raining":
                alternateResponse = "Alternatively, wear a raincoat and water-resistant pants.";
                break;
            default:
                alternateResponse = "I don’t have alternate suggestions for that.";
        }

        chatBotResponse.setText(alternateResponse);
    }
}
