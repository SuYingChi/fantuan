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

    public void e(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.e(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.e(tag, msg);
    }

}
