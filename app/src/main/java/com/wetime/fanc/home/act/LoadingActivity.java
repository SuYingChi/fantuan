package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;

import com.wetime.fanc.R;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.LogUtils;
import com.wetime.fanc.utils.Tools;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends BaseActivity {

    private long timelong = 1000;
    private Timer time;
    private TimerTask tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        LogUtils.d("loading");
        time = new Timer();

        AndPermission.with(this)
                .permission(Permission.Group.LOCATION,Permission.Group.STORAGE)
                .onGranted(permissions -> {
                    // TODO what to do.
                    waitGo();
                }).onDenied(permissions -> {
            // TODO what to do
            Tools.toastInBottom(mContext,"为了更好使用范团，请赋予权限");
            waitGo();
        })
                .start();


        spu.setValue("citem", "0");
    }
    private void waitGo(){
        tk = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                gotoActivity();
                finish();
            }
        };
        time.schedule(tk, timelong);
    }
    @Override
    protected void onDestroy() {

        time.cancel();
        tk.cancel();
        super.onDestroy();
    }

    private void gotoActivity() {
        Intent go;
        go = new Intent(this, LoginActivity.class);
        if (getIntent().getExtras() != null) {
            go.putExtras(getIntent().getExtras());
        }
        if (spu.getToken().equals("")) {
            Intent goLogin = new Intent(this, LoginActivity.class);
            startActivity(goLogin);
        }else {
            Intent goMain = new Intent(this, MainActivity.class);
            startActivity(goMain);
        }
        finish();

    }

}
