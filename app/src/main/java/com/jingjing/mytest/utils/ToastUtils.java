package com.jingjing.mytest.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/9 14:56
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class ToastUtils {

    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


}
