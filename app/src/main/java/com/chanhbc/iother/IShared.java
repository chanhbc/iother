package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class IShared implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static IShared instance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    private IShared(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        this.mEditor = this.mSharedPreferences.edit();
        registerChangeListener();
    }

    public void release(){
        unregisterChangeListener();
        this.mEditor = null;
        this.mSharedPreferences = null;
        instance = null;
    }

    public static IShared getInstance(Context context) {
        if (instance == null) {
            instance = new IShared(context);
        }
        return instance;
    }

    private void registerChangeListener() {
        this.mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void unregisterChangeListener() {
        this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void removeKey(String key) {
        this.mEditor.remove(key).apply();
    }

    public void clear() {
        this.mEditor.clear().apply();
    }

    /**
     * defaultValue: false
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return this.mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        this.mEditor.putBoolean(key, value).commit();
    }

    /**
     * defaultValue: 0
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return this.mSharedPreferences.getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        this.mEditor.putInt(key, value).commit();
    }


    /**
     * defaultValue: 0
     */
    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        return this.mSharedPreferences.getLong(key, defaultValue);
    }

    public void putLong(String key, long value) {
        this.mEditor.putLong(key, value).commit();
    }

    /**
     * defaultValue: 0.0f
     */
    public float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public float getFloat(String key, float defaultValue) {
        return this.mSharedPreferences.getFloat(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        this.mEditor.putFloat(key, value).commit();
    }

    /**
     * defaultValue: ""
     */
    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return this.mSharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        this.mEditor.putString(key, value).commit();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        for (OnISharedListener onISharedListener : onISharedListeners) {
            if (onISharedListener != null) {
                onISharedListener.onKeyChangeListener(sharedPreferences, key);
            }
        }
    }

    private final ArrayList<OnISharedListener> onISharedListeners = new ArrayList<>();

    public void addListener(OnISharedListener onISharedListener) {
        if (onISharedListeners.indexOf(onISharedListener) < 0) {
            onISharedListeners.add(onISharedListener);
        }
    }

    public void removeListener(OnISharedListener onISharedListener) {
        int index = onISharedListeners.indexOf(onISharedListener);
        if (index >= 0) {
            onISharedListeners.remove(index);
        }
    }

    public interface OnISharedListener {
        void onKeyChangeListener(SharedPreferences sharedPreferences, String key);
    }
}
