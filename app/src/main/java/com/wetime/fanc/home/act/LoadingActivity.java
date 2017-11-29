package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.LogUtils;
import com.wetime.fanc.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends BaseActivity {

    private long timelong = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        LogUtils.d("loading");
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                gotoActivity();
                finish();
            }
        };
        time.schedule(tk, timelong);
    }

    private void gotoActivity() {
        Intent go;
        go = new Intent(this, MainActivity.class);
        if (getIntent().getExtras() != null) {
            go.putExtras(getIntent().getExtras());
        }
        startActivity(go);
        finish();

    }

}
