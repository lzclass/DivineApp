package com.liuzhao.divineapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.liuzhao.divineapp.base.BaseApplication;


public enum PreferencesManager {


    /**
     * 存储用户相关信息
     */
    USER(BaseApplication.getSelf(), "user"),
    /**
     * 存储通用信息
     */
    COMMON(BaseApplication.getSelf(), "common");

    private Context context;
    private String share_name;
    public static final String PREFERENCES_USER_ID = "userId";
    private SharedPreferences sharedPreferences;

    PreferencesManager(Context context, String share_name) {
        this.context = context;
        this.share_name = share_name;
        getSharedPreferences();
    }

    public SharedPreferences getSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(share_name,
                Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultvaule) {
        String value = sharedPreferences.getString(key, defaultvaule);
        return value;
    }

    public void deleteString(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void setBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key, Boolean defaultvalue) {
        Boolean value = sharedPreferences.getBoolean(key, defaultvalue);
        return value;
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defaultvalue) {
        int value = sharedPreferences.getInt(key, defaultvalue);
        return value;
    }

    public void setFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key, float defaultvalue) {
        float value = sharedPreferences.getFloat(key, defaultvalue);
        return value;
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, long defaultvalue) {
        long value = sharedPreferences.getLong(key, defaultvalue);
        return value;
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCES_USER_ID, userId);
        editor.commit();
    }


    public String getUserId() {
        return sharedPreferences.getString(PREFERENCES_USER_ID, "");
    }

    }
