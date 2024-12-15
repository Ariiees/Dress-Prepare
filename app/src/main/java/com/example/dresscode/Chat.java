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

    private int flag = 0;

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

        // Handle "Send" button click
        regerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = userInput.getText().toString().trim();
                chatAsk.setText(userMessage);
                chatAsk.setVisibility(View.VISIBLE);
                chatAnswer1.setVisibility(View.GONE);
                chatAnswer2.setVisibility(View.VISIBLE);
                flag = 1;
            }
        });

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

            if (flag == 0) {
                imageView.setImageResource(R.drawable.style1);
            } else {
                imageView.setImageResource(R.drawable.style2);
            }

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Set click listeners for buttons
            applyButton.setOnClickListener(applyView -> {
                // Handle apply action
                Toast.makeText(this, "Apply clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // Close the dialog
                // TOD0: Link with Home
            });

            cancelButton.setOnClickListener(cancelView -> {
                // Handle cancel action
                dialog.dismiss(); // Simply close the dialog
            });
        });




    }
}
