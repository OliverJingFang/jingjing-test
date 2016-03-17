package com.jingjing.mytest.utils;

import android.util.Log;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/16 16:01
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class LogUtil {

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
