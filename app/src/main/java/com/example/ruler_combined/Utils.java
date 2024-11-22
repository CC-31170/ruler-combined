package com.example.ruler_combined;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {
    public static int screenWidth_;
    public static int screenHeight_;

    public static void initScreenSize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth_ = displayMetrics.widthPixels;
        screenHeight_ = displayMetrics.heightPixels;
    }
}
