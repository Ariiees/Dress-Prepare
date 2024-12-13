package com.example.dresscode;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DressCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dress_code);

        // Initialize Bottom Navigation Buttons
        ImageView homeButton = findViewById(R.id.homeIcon);
        ImageView chatButton = findViewById(R.id.aiChatIcon);
        ImageView weatherButton = findViewById(R.id.weatherIcon);
        ImageView eventButton = findViewById(R.id.eventAttireIcon);
        ImageView settingButton = findViewById(R.id.profileIcon);
        Util.bottomBarJump(this, homeButton, chatButton, weatherButton, eventButton, settingButton, 4);

        // Initialize Views
        Spinner spinner = findViewById(R.id.dressCodeDropdown);
        ImageView imageview = findViewById(R.id.imageview);
        LinearLayout buttonMoreInfo = findViewById(R.id.moreInfoLayout);


        // Define Dropdown Items
        String[] dressCodes = {"Pick an Option", "White Tie", "Black Tie", "Semi-Formal", "Cocktail", "Casual"};

        // Set Up Spinner Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dressCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set Spinner Item Selection Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Default option
                        imageview.setVisibility(View.GONE);
                        buttonMoreInfo.setVisibility(View.GONE);
                        break;
                    case 1: // White tie
                        imageview.setImageResource(R.drawable.white_tie);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "White Tie",
                                    "• Formal floor-length gown\n" +
                                    "• Black jackets with tails\n" +
                                    "• White vest \n" +
                                    "• Tuxedo pants \n" +
                                    "• White bowtie \n" +
                                    "• Polished dress shoes \n" +
                                    "• Dazzling jewelry \n" +
                                    "• Tiara \n" +
                                    "• Gloves"
                        });
                        break;
                    case 2: // Black Tie
                        imageview.setImageResource(R.drawable.black_tie);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Black Tie",
                                    "• Formal to semi-formal floor-length dresses made with quality fabric (velvet, chiffon, silk, lace)\n" +
                                    "• Well-fitted black or navy suit jacket with a lapel\n" +
                                    "• Vest\n" +
                                    "• Black bowtie\n" +
                                    "• Elegant heels \n" +
                                    "• Classy dress shoes"
                        });
                        break;
                    case 3: // Semi-Formal
                        imageview.setImageResource(R.drawable.semi);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Semi-Formal",
                                    "• Dress shirt and slacks with an optional tie\n" +
                                    "• Skirt and blouse in high-quality material\n" +
                                    "• A knee-length dress\n" +
                                    "• Tasteful pantsuit or jumpsuit"
                        });
                        break;
                    case 4: // Cocktail
                        imageview.setImageResource(R.drawable.cock);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Cocktail",
                                    "• Short, flirty cocktail dresses that fall to the knee (flowy or formfitting) or a dressy jumpsuit\n" +
                                    "• Suit and tie\n" +
                                    "• Comfortable, dressy shoes\n" +
                                    "• A stylish jacket"
                        });
                        break;
                    case 5: // Casual
                        imageview.setImageResource(R.drawable.casual);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Casual",
                                    "• Sundresses\n" +
                                    "• Collared shirts\n" +
                                    "• Skirts\n" +
                                    "• Khaki pants\n" +
                                    "• Sport jackets\n" +
                                    "• Wedges\n" +
                                    "• Dressy sandals"
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button Click Listener to Show Dialog
        buttonMoreInfo.setOnClickListener(v -> {
            // Retrieve the information from the button's tag
            String[] tagData = (String[]) buttonMoreInfo.getTag();
            if (tagData != null) {
                String title = tagData[0];
                String information = tagData[1];

                // Show an Dialog with the information
                AlertDialog.Builder builder = new AlertDialog.Builder(DressCode.this);
                builder.setTitle(title) // Dynamically set title
                        .setMessage(information) // Dynamically set message
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }
}
