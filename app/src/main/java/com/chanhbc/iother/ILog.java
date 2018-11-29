package com.chanhbc.iother;

import android.util.Log;


public class ILog {
    private static boolean isLog = true;
    private static String MY_TAG = "bc";

    public static void setIsLog(boolean isLog) {
        ILog.isLog = isLog;
    }

    public static void d(Object obj) {
        d(MY_TAG, obj);
    }

    public static void d(String TAG, Object object) {
        if (isLog) {
            Log.d(TAG, String.valueOf(object));
        }
    }

    public static void e(Object obj) {
        e(MY_TAG, obj);
    }

    public static void e(String TAG, Object object) {
        if (isLog) {
            Log.e(TAG, String.valueOf(object));
        }
    }

    public static void v(Object obj) {
        v(MY_TAG, obj);
    }

    public static void v(String TAG, Object object) {
        if (isLog) {
            Log.v(TAG, String.valueOf(object));
        }
    }

    public static void i(Object obj) {
        i(MY_TAG, obj);
    }

    public static void i(String TAG, Object object) {
        if (isLog) {
            Log.i(TAG, String.valueOf(object));
        }
    }

    public static void w(Object obj) {
        w(MY_TAG, obj);
    }

    public static void w(String TAG, Object object) {
        if (isLog) {
            Log.w(TAG, String.valueOf(object));
        }
    }

    public static void wtf(Object obj) {
        wtf(MY_TAG, obj);
    }

    public static void wtf(String TAG, Object object) {
        if (isLog) {
            Log.wtf(TAG, String.valueOf(object));
        }
    }
}
