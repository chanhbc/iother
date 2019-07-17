package com.chanhbc.iother;

import android.util.Log;

import java.util.Arrays;

public class ILog {
	private ILog() {
	}

	private static boolean enabled = true;
	private static String MY_TAG = "bc";

	public enum SUB_TYPE {LOG, TAG_LOG, T_LOG, TAG_T_LOG}

	public enum LOG_TYPE {D, E, I, V, W, WTF}

	public static void setEnabled(boolean enabled) {
		ILog.enabled = enabled;
	}

	public static void setTag(String myTag) {
		MY_TAG = myTag;
	}

	public static void d(Object... objLog) {
		d(SUB_TYPE.LOG, objLog);
	}

	public static void d(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.D, subType, objects);
	}

	public static void e(Object... objLog) {
		e(SUB_TYPE.LOG, objLog);
	}

	public static void e(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.E, subType, objects);
	}

	public static void i(Object... objLog) {
		i(SUB_TYPE.LOG, objLog);
	}

	public static void i(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.I, subType, objects);
	}

	public static void v(Object... objLog) {
		v(SUB_TYPE.LOG, objLog);
	}

	public static void v(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.V, subType, objects);
	}

	public static void w(Object... objLog) {
		w(SUB_TYPE.LOG, objLog);
	}

	public static void w(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.W, subType, objects);
	}

	public static void wtf(Object... objLog) {
		wtf(SUB_TYPE.LOG, objLog);
	}

	public static void wtf(SUB_TYPE subType, Object... objects) {
		log(LOG_TYPE.WTF, subType, objects);
	}

	private static String arrayToString(Object... objects) {
		if (objects == null || objects.length == 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; ; i++) {
			Object object = objects[i];
			if (i == objects.length - 1) {
				return result.append(object).toString();
			}
			result.append(object).append(" ");
		}
	}

	private static void log(LOG_TYPE logType, SUB_TYPE subType, Object... objects) {
		if (objects == null || objects.length == 0) {
			log(logType, MY_TAG, "<no log>");
			return;
		}
		switch (subType) {
			case LOG:
				log(logType, MY_TAG, arrayToString(objects));
				break;

			case T_LOG:
				if (objects.length > 1) {
					log(logType, MY_TAG, objects[0].getClass().getSimpleName() + "-" + arrayToString(Arrays.copyOfRange(objects, 1, objects.length)));
				} else {
					log(logType, MY_TAG, arrayToString(objects));
				}
				break;

			case TAG_LOG:
				if (objects.length > 1) {
					log(logType, objects[0].toString(), arrayToString(Arrays.copyOfRange(objects, 1, objects.length)));
				} else {
					log(logType, MY_TAG, arrayToString(objects));
				}
				break;

			case TAG_T_LOG:
				if (objects.length > 2) {
					log(logType, objects[0].toString(), objects[1].getClass().getSimpleName() + "-" + arrayToString(Arrays.copyOfRange(objects, 2, objects.length)));
				} else {
					log(logType, MY_TAG, arrayToString(objects));
				}
				break;

			default:
				log(logType, MY_TAG, arrayToString(objects));
				break;
		}
	}

	private static void log(LOG_TYPE logType, String TAG, Object objLog) {
		if (!enabled) {
			return;
		}
		switch (logType) {
			case D:
				Log.d(TAG, String.valueOf(objLog));
				break;

			case E:
				Log.e(TAG, String.valueOf(objLog));
				break;

			case I:
				Log.i(TAG, String.valueOf(objLog));
				break;

			case V:
				Log.v(TAG, String.valueOf(objLog));
				break;

			case W:
				Log.w(TAG, String.valueOf(objLog));
				break;

			case WTF:
				Log.wtf(TAG, String.valueOf(objLog));
				break;

			default:
				Log.e(TAG, String.valueOf(objLog));
				break;
		}
	}

}
