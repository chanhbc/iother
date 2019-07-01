package com.chanhbc.iother;

import android.app.Application;
import android.content.Context;

public class IApp extends Application {
	private static IApp instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static Context getContext() {
		return instance.getApplicationContext();
	}
}
