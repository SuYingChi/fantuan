package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.LogUtils;

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
        tk = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                gotoActivity();
                finish();
            }
        };
        time.schedule(tk, timelong);
        spu.setValue("citem", "0");
    }

    @Override
    protected void onDestroy() {
        time.cancel();
        tk.cancel();
        super.onDestroy();
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
