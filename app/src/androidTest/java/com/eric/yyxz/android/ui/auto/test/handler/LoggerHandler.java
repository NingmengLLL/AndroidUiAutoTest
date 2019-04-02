package com.eric.yyxz.android.ui.auto.test.handler;

import android.util.Log;

public class LoggerHandler {

    private static final String tag = "AndroidUiAutoTest";

    public static void i(String msg){
        Log.i(tag, msg);
    }

    public static void i(String msg, Throwable tr){
        Log.i(tag, msg, tr);
    }

    public static void w(String msg){
        Log.w(tag, msg);
    }

    public static void w(String msg, Throwable tr){
        Log.w(tag, msg, tr);
    }

    public static void e(String msg){
        Log.e(tag, msg);
    }

    public static void e(String msg, Throwable tr){
        Log.e(tag, msg, tr);
    }

    public static void v(String msg){
        Log.v(tag, msg);
    }

    public static void v(String msg, Throwable tr){
        Log.v(tag, msg, tr);
    }

    public static void d(String msg){
        Log.d(tag, msg);
    }

    public static void d(String msg, Throwable tr){
        Log.d(tag, msg, tr);
    }
}
