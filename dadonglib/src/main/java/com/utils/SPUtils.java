package com.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dadong on 2018/5/14.
 */

public class SPUtils {

    private static SPUtils spUtils;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SPUtils(Context context, String fileName) {
        sharedPreferences = context.getSharedPreferences(fileName, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SPUtils getSpUtils(Context context, String fileName) {

        if (spUtils == null) {
            spUtils = new SPUtils(context, fileName);
        }

        return spUtils;
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

}
