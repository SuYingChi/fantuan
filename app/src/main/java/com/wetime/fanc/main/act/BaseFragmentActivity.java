package com.wetime.fanc.main.act;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.wetime.fanc.utils.SharePreferenceUtil;


public class BaseFragmentActivity extends FragmentActivity {
    public SharePreferenceUtil spu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spu = new SharePreferenceUtil(this, "wetime");
    }
}
