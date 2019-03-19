package com.chanhbc.iother;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class IShared {
	private static IShared instance;
	private Context context;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor mEditor;

	@SuppressLint ("CommitPrefEdits")
	public IShared(Context context) {
		this.context = context;
		this.sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
		this.mEditor = this.sharedPreferences.edit();
	}

	public static IShared getIShare(Context context) {
		if (instance == null) {
			instance = new IShared(context);
		}
		return instance;
	}

	/**
	 * defaultValue: false
	 * */
	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return this.sharedPreferences.getBoolean(key, defaultValue);
	}

	public void putBoolean(String key, boolean value) {
		this.mEditor.putBoolean(key, value).commit();
	}

	/**
	 * defaultValue: 0
	 * */
	public int getInt(String key) {
		return getInt(key, 0);
	}

	public int getInt(String key, int defaultValue) {
		return this.sharedPreferences.getInt(key, defaultValue);
	}

	public void putInt(String key, int value) {
		this.mEditor.putInt(key, value).commit();
	}


	/**
	 * defaultValue: 0
	 * */
	public long getLong(String key) {
		return getLong(key, 0);
	}

	public long getLong(String key, long defaultValue) {
		return this.sharedPreferences.getLong(key, defaultValue);
	}

	public void putLong(String key, long value) {
		this.mEditor.putLong(key, value).commit();
	}

	/**
	 * defaultValue: 0.0f
	 * */
	public float getFloat(String key) {
		return getFloat(key, 0.0f);
	}

	public float getFloat(String key, float defaultValue) {
		return this.sharedPreferences.getFloat(key, defaultValue);
	}

	public void putFloat(String key, float value) {
		this.mEditor.putFloat(key, value).commit();
	}

	/**
	 * defaultValue: ""
	 * */
	public String getString(String key) {
		return getString(key, "");
	}

	public String getString(String key, String defaultValue) {
		return this.sharedPreferences.getString(key, defaultValue);
	}

	public void putString(String key, String value) {
		this.mEditor.putString(key, value).commit();
	}
}
