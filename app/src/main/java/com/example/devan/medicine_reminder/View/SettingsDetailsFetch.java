package com.example.devan.medicine_reminder.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsDetailsFetch {
    private static SharedPreferences sharedPreferences;

    private static void getSharedPreferencesInstance(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean splashScreenOnOff(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getBoolean("splashScreen", true);

    }



}
