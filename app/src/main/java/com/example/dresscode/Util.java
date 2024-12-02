package com.example.dresscode;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

public class Util {

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
}
