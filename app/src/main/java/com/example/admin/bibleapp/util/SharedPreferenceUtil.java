package com.example.admin.bibleapp.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.util.Set;

public class SharedPreferenceUtil {
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";

    public static void putSharedPreferencesInt(Context context, String key, int value) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void putSharedPreferencesBoolean(Context context, String key, boolean val) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesString(Context context, String key, String val) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesStringSet(Context context, String key, Set<String> val) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putStringSet(key, val);
        editor.commit();
    }

    public static void putSharedPreferencesFloat(Context context, String key, float val) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putFloat(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesLong(Context context, String key, long val) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putLong(key, val);
        edit.commit();
    }

    public static long getSharedPreferencesLong(Context context, String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defaultValue);
    }

    public static float getSharedPreferencesFloat(Context context, String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static String getSharedPreferencesString(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    public static Set<String> getSharedPreferencesStringSet(Context context, String key, Set<String> defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, defaultValue);
    }

    public static int getSharedPreferencesInt(Context context, String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean getSharedPreferencesBoolean(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean removeSharedPreferencesKey(Context context, String key) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.remove(key);
        return editor.commit();
    }
}
