package com.chanhbc.iother;

import android.util.Log;


public class ILog {
	private static boolean isLog = BuildConfig.DEBUG;
	private static String MY_TAG = "bc";

	public static void setIsLog(boolean isLog) {
		ILog.isLog = isLog;
	}

	public static void d(Object objTag, Object objLog) {
		d(MY_TAG, objTag, objLog);
	}

	public static void d(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.d(TAG, objTag.getClass().getSimpleName() + "-" + String.valueOf(objLog));
		}
	}

	public static void d(Object objLog) {
		d(MY_TAG, objLog);
	}

	public static void d(String TAG, Object objLog) {
		if (isLog) {
			Log.d(TAG, String.valueOf(objLog));
		}
	}

	public static void e(Object objTag, Object objLog) {
		e(MY_TAG, objTag, objLog);
	}

	public static void e(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.e(TAG, objTag.getClass().getSimpleName() + "-" +String.valueOf(objLog));
		}
	}

	public static void e(Object objLog) {
		e(MY_TAG, objLog);
	}

	public static void e(String TAG, Object objLog) {
		if (isLog) {
			Log.e(TAG, String.valueOf(objLog));
		}
	}

	public static void v(Object objTag, Object objLog) {
		v(MY_TAG, objTag, objLog);
	}

	public static void v(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.v(TAG, objTag.getClass().getSimpleName() + "-" +String.valueOf(objLog));
		}
	}

	public static void v(Object objLog) {
		v(MY_TAG, objLog);
	}

	public static void v(String TAG, Object objLog) {
		if (isLog) {
			Log.v(TAG, String.valueOf(objLog));
		}
	}

	public static void i(Object objTag, Object objLog) {
		i(MY_TAG, objTag, objLog);
	}

	public static void i(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.i(TAG, objTag.getClass().getSimpleName() + "-" +String.valueOf(objLog));
		}
	}

	public static void i(Object objLog) {
		i(MY_TAG, objLog);
	}

	public static void i(String TAG, Object objLog) {
		if (isLog) {
			Log.i(TAG, String.valueOf(objLog));
		}
	}

	public static void w(Object objTag, Object objLog) {
		w(MY_TAG, objTag, objLog);
	}

	public static void w(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.w(TAG, objTag.getClass().getSimpleName() + "-" +String.valueOf(objLog));
		}
	}

	public static void w(Object objLog) {
		w(MY_TAG, objLog);
	}

	public static void w(String TAG, Object objLog) {
		if (isLog) {
			Log.w(TAG, String.valueOf(objLog));
		}
	}

	public static void wtf(Object objTag, Object objLog) {
		wtf(MY_TAG, objTag, objLog);
	}

	public static void wtf(String TAG, Object objTag, Object objLog) {
		if (isLog) {
			Log.wtf(TAG, objTag.getClass().getSimpleName() + "-" +String.valueOf(objLog));
		}
	}

	public static void wtf(Object objLog) {
		wtf(MY_TAG, objLog);
	}

	public static void wtf(String TAG, Object objLog) {
		if (isLog) {
			Log.wtf(TAG, String.valueOf(objLog));
		}
	}
}
