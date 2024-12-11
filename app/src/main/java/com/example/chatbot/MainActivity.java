package com.example.chatbot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView chatDisplay;
    private EditText userInput;
    private Button sendButton, regenerateButton, goBackButton;

    // Hardcoded responses categorized by weather type
    private final HashMap<String, String[]> responsesByWeather = new HashMap<String, String[]>() {{
        put("cold", new String[] {
                "Weather: 16/2°C | clear sky\nSuggested Clothing:\n- Warm sweater or long-sleeve shirt\n- Light to medium jacket\n- Comfortable jeans or pants\n- Closed-toe shoes",
                "Weather: 15/1°C | overcast\nSuggested Clothing:\n- Thick sweater\n- Heavy coat\n- Warm pants\n- Boots",
                "Weather: 17/3°C | windy\nSuggested Clothing:\n- Windbreaker\n- Layers\n- Jeans\n- Sneakers"
        });
        put("mild", new String[] {
                "Weather: 22/10°C | partly cloudy\nSuggested Clothing:\n- Light jacket or hoodie\n- T-shirt\n- Casual pants or shorts\n- Sneakers",
                "Weather: 23/11°C | sunny\nSuggested Clothing:\n- Long-sleeve shirt\n- Light jeans\n- Comfortable shoes",
                "Weather: 21/9°C | breezy\nSuggested Clothing:\n- Hoodie or sweater\n- Jeans\n- Sneakers"
        });
        put("hot", new String[] {
                "Weather: 30/18°C | sunny\nSuggested Clothing:\n- Short-sleeve shirt\n- Shorts\n- Sandals or comfortable shoes\n- Sunglasses",
                "Weather: 31/19°C | clear sky\nSuggested Clothing:\n- Tank top\n- Light shorts\n- Flip-flops\n- Sun hat",
                "Weather: 29/17°C | humid\nSuggested Clothing:\n- Loose clothing\n- Breathable fabrics\n- Sneakers\n- Cap"
        });
    }};

    private Stack<String> previousResponses = new Stack<>(); // Stack to store previous responses
    private String currentWeatherType = "cold"; // Default weather type
    private Random random = new Random();
    private boolean isNewSession = true; // Track if the app is newly opened

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        chatDisplay = findViewById(R.id.chat_display);
        userInput = findViewById(R.id.user_input);
        sendButton = findViewById(R.id.send_button);
        regenerateButton = findViewById(R.id.regenerate_button);
        goBackButton = findViewById(R.id.go_back_button);

        // Clear previous responses and set up the initial chatbot response
        if (isNewSession) {
            previousResponses.clear();
            chatDisplay.setText("Bot: How can I help you?");
            isNewSession = false;
        } else {
            if (previousResponses.isEmpty()) {
                String initialResponse = getRandomResponse(currentWeatherType);
                chatDisplay.setText("Bot: " + initialResponse);
                previousResponses.push(initialResponse);
            } else {
                chatDisplay.setText("Bot: " + previousResponses.peek());
            }
        }

        // Send button functionality
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userText = userInput.getText().toString();
                if (!userText.isEmpty()) {
                    chatDisplay.append("\nYou: " + userText);
                    if (!previousResponses.isEmpty()) {
                        chatDisplay.append("\nBot: " + previousResponses.peek());
                    } else {
                        String newResponse = getRandomResponse(currentWeatherType);
                        previousResponses.push(newResponse);
                        chatDisplay.append("\nBot: " + newResponse);
                    }
                    userInput.setText("");
                }
            }
        });

        // Regenerate button functionality
        regenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newResponse = getRandomResponse(currentWeatherType);
                if (previousResponses.isEmpty() || !newResponse.equals(previousResponses.peek())) {
                    previousResponses.push(newResponse);
                    chatDisplay.setText("Bot: " + newResponse);
                }
            }
        });

        // Go back button functionality
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousResponses.size() > 1) {
                    previousResponses.pop();
                    chatDisplay.setText("Bot: " + previousResponses.peek());
                }
            }
        });
    }

    private String getRandomResponse(String weatherType) {
        String[] responses = responsesByWeather.get(weatherType);
        return responses[random.nextInt(responses.length)];
    }
}
