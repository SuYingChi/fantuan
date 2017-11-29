package com.wetime.fanc.push;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.wetime.fanc.home.act.LoadingActivity;
import com.wetime.fanc.home.act.MainActivity;
import com.wetime.fanc.home.iviews.IBindPushView;
import com.wetime.fanc.home.presenter.BindPushPresenter;
import com.wetime.fanc.push.event.RegistPushSuccessEvent;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhoukang on 2017/11/29.
 */

public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PUSH";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
//        Log.e(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.e(TAG, "JPush用户注册成功");
            EventBus.getDefault().post(new RegistPushSuccessEvent(JPushInterface.getRegistrationID(context)));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.e(TAG, "用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            Log.e(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.e(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.e(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG, "extras : " + extras);
    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String type = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            type = extrasJson.optString("type");
            Log.e(TAG, type);
            // 目前只有一种 推送类型  打开 url
            if (type.equals("1")) {
                String url = extrasJson.optString("url");
                if (isOpen(context) && !TextUtils.isEmpty(url)) {
                    Intent mIntent = new Intent(context, WebActivity.class);
                    mIntent.putExtra("url", url);
                    mIntent.putExtras(bundle);
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                } else {
                    Intent mIntent = new Intent(context, LoadingActivity.class);
                    mIntent.putExtra("url", url);
                    mIntent.putExtras(bundle);
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mIntent);
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }

//        if (TYPE_THIS.equals(myValue)) {
//            Intent mIntent = new Intent(context, ThisActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        } else if (TYPE_ANOTHER.equals(myValue)){
//            Intent mIntent = new Intent(context, AnotherActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        }
    }

    /**
     * 获取栈顶activity
     *
     * @param context
     * @return
     */
    private String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null)
            return (runningTaskInfos.get(0).topActivity).getClassName().toString();
        else
            return null;
    }

    /**
     * 判断APP是否开启
     *
     * @param context
     * @return
     */
    private boolean isOpen(Context context) {
        if (getTopActivity(context) != null
                && !getTopActivity(context).equals("")) {
            if (getTopActivity(context).contains("com.wetime.fanc")) {
                return true;
            }
        }
        return false;
    }
}
