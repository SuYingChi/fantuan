package com.wetime.fanc.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/4/9.
 */

public class DialogUtils {

    public static AlertDialog show;

    public static void showNormalDialog(Context context, String title, String message, DialogInterface.OnClickListener onPosiListener) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */

        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        if (title != null && !title.isEmpty()) normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton("确定", onPosiListener);
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        show.dismiss();
                    }
                });
        // 显示
        show = normalDialog.show();
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(show);
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextColor(Color.parseColor("#030303"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#0076ff"));
        show.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#0076ff"));
    }

}
