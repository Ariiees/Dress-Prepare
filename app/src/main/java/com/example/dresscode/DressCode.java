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

        // Initialize Views
        Spinner spinner = findViewById(R.id.dressCodeDropdown);
        ImageView imageview = findViewById(R.id.imageview);
        LinearLayout buttonMoreInfo = findViewById(R.id.moreInfoLayout);

        // Define Dropdown Items
        String[] dressCodes = {"Pick an Option", "White Tie", "Semi-Formal", "Cocktail"};

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
                    case 1: // White Tie
                        imageview.setImageResource(R.drawable.white_tie);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "White Tie",
                                "• Full-length evening gown (floor-length)\n" +
                                        "• Luxurious fabrics (silk, satin, velvet)\n" +
                                        "• Optional long gloves\n" +
                                        "• Elegant jewelry (diamonds, pearls)\n\n" +
                                        "Occasions:\n" +
                                        "Royal events, state dinners, formal galas"
                        });
                        break;
                    case 2: // Semi-Formal
                        imageview.setImageResource(R.drawable.semi);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Semi-Formal",
                                "• Cocktail dress or dressy skirt and blouse\n" +
                                        "• Elegant jumpsuit as an option\n" +
                                        "• Heels or dressy flats\n\n" +
                                        "Occasions:\n" +
                                        "Evening weddings, holiday parties, business dinners"
                        });
                        break;
                    case 3: // Cocktail
                        imageview.setImageResource(R.drawable.cock);
                        imageview.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setVisibility(View.VISIBLE);
                        buttonMoreInfo.setTag(new String[]{
                                "Cocktail",
                                "• Cocktail dress (typically knee-length)\n" +
                                        "• Elegant accessories (jewelry, clutch bag)\n" +
                                        "• Heels or dressy sandals \n\n" +
                                        "Occasions:\n" +
                                        "Cocktail parties, evening events, receptions"
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
