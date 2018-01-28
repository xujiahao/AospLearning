package com.skyworthdigital.download.utils;

import android.util.Log;

/**
 * Created by SDT13716 on 2018/1/28.
 */

public class Logger {
    public static final boolean DEBUG = true;

    public static void d(String tag, String message){
        if (DEBUG) Log.d(tag, message);
    }

    public static void i(String tag, String message){
        if (DEBUG) Log.i(tag, message);
    }

    public static void e(String tag, String message){
        if (DEBUG) Log.e(tag, message);
    }
}
