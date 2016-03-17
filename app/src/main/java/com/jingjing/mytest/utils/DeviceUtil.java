package com.jingjing.mytest.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/16 15:22
 * Email：fangjing@medlinker.com.
 * Description: 获取手机属性方法类
 */
public class DeviceUtil {

    private static DisplayMetrics displayMetrics;

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (displayMetrics == null) {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (displayMetrics == null) {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        return displayMetrics.heightPixels;
    }
}
