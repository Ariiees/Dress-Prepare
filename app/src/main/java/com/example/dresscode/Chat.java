package com.example.dresscode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    private TextView chatAsk, chatAnswer1, chatAnswer2;
    private EditText userInput;
    private ImageButton sendButton, regerateButton, visualizeButton;

    private String userMessage;


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
                userMessage = userInput.getText().toString().trim();
                if (!userMessage.isEmpty()) {
                    chatAsk.setText(userMessage);
                    chatAsk.setVisibility(View.VISIBLE);
                    visualizeButton.setVisibility(View.VISIBLE);
                    regerateButton.setVisibility(View.VISIBLE);
                    chatAnswer1.setVisibility(View.VISIBLE);
                }
            }
        });

        // Handle "Regenerate" button click
        regerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userMessage = userInput.getText().toString().trim();
                chatAsk.setText(userMessage);
                chatAsk.setVisibility(View.VISIBLE);
                chatAnswer1.setVisibility(View.GONE);
                chatAnswer2.setVisibility(View.VISIBLE);
            }

        });

        // Handle "Visialize" button click
        visualizeButton.setOnClickListener(v -> {
            // Create a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Inflate the custom layout for the dialog
            View dialogView = LayoutInflater.from(this).inflate(R.layout.chat_dialog, null);
            builder.setView(dialogView);

            // Find the image view and buttons in the dialog layout
            ImageView imageView = dialogView.findViewById(R.id.dialogImageView);
            Button applyButton = dialogView.findViewById(R.id.applyButton);
            Button cancelButton = dialogView.findViewById(R.id.cancelButton);

            int flag = Util.getFlag(this);
            if (flag == 0) {
                imageView.setImageResource(R.drawable.style1_chat);
            } else if (flag == 1) {
                imageView.setImageResource(R.drawable.style2_chat);
            }

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Set click listeners for buttons
            applyButton.setOnClickListener(applyView -> {
                Toast.makeText(this, "Apply clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // Close the dialog
                // Link with Home
                int current = Util.getFlag(this);
                if (current == 0){
                    Util.setFlag(Chat.this, 1);
                } else if (current == 1){
                    Util.setFlag(Chat.this, 2);
                }
            });

            cancelButton.setOnClickListener(cancelView -> {
                dialog.dismiss();
            });
        });

    }
}
