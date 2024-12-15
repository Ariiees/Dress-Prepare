package com.example.dresscode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;

public class Util {

    private static final String PREFS_NAME = "AppPrefs"; // SharedPreferences name
    private static final String FLAG_KEY = "flag"; // Key for flag value
    private static final String TEMP_KEY = "temperature"; // Key for temperature data
    private static final String FEEL_KEY = "apparentTemperature"; // Key for apparent temperature data

    public static void bottomBarJump(Context context, ImageView home, ImageView chat, ImageView weather,
                                     ImageView event, ImageView setting, int stage) {
        /*
        This function adds onclick events to all four buttons of the bottom bar.

        Context context: The current activity context.
        ImageView home, chat, weather, event: ImageView objects passed as buttons.
        stage: The current page (1 = home, 2 = chat, 3 = weather, 4 = event).
               Prevents redundant clicks from triggering navigation to the same page.
         */

        if (stage != 1) {
            home.setOnClickListener(v -> {
                Intent intent = new Intent(context, Home.class);
                context.startActivity(intent);
            });
        }

        if (stage != 2) {
            chat.setOnClickListener(v -> {
                Intent intent = new Intent(context, Chat.class);
                context.startActivity(intent);
            });
        }

        if (stage != 3) {
            weather.setOnClickListener(v -> {
                Intent intent = new Intent(context, Weather.class);
                context.startActivity(intent);
            });
        }

        if (stage != 4) {
            event.setOnClickListener(v -> {
                Intent intent = new Intent(context, DressCode.class);
                context.startActivity(intent);
            });
        }

        if (stage != 5) {
            setting.setOnClickListener(v -> {
                Intent intent = new Intent(context, Setting.class);
                context.startActivity(intent);
            });
        }
    }

    // Method to set the flag value using SharedPreferences
    public static void setFlag(Context context, int flag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(FLAG_KEY, flag); // Store the flag value
        editor.apply(); // Commit the changes
    }

    // Method to get the flag value using SharedPreferences
    public static int getFlag(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(FLAG_KEY, 0); // Default value is 0 if not set
    }

    // Method to store temperature in SharedPreferences
    public static void setTemperature(Context context, String temperature) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEMP_KEY, temperature); // Store the temperature
        editor.apply(); // Commit the changes
    }

    // Method to retrieve temperature from SharedPreferences
    public static String getTemperature(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TEMP_KEY, "7°C"); // Default value if not set
    }

    // Method to store apparent temperature (feels like) in SharedPreferences
    public static void setApparentTemperature(Context context, String apparentTemperature) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FEEL_KEY, apparentTemperature); // Store the apparent temperature
        editor.apply(); // Commit the changes
    }

    // Method to retrieve apparent temperature from SharedPreferences
    public static String getApparentTemperature(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FEEL_KEY, "Feels like 4°C"); // Default value if not set
    }

}
