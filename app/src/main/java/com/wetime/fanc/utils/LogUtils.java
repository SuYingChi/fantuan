package com.wetime.fanc.utils;

import android.util.Log;

/**
 * Created by zhoukang on 2017/5/16.
 */

public class LogUtils {
    private static final String TAG = "zk";

    public static void d(String msg) {
//        if (DEBUG){
        Log.e(TAG, msg);
//        }

    }

    public static void d(String tag, String msg) {
//        if (DEBUG){
        Log.e(tag, msg);
//        }

    }

}