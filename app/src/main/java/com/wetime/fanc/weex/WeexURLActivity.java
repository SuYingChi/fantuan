package com.wetime.fanc.weex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.wetime.fanc.utils.Tools;


public class WeexURLActivity extends WeexBaseActivity {
    private static final String KEY = "url";

    public static void startWeexURLActivity(Context context, @NonNull String url) {
        Intent intent = new Intent(context, WeexURLActivity.class);
        intent.putExtra(KEY, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadWeexPageFromUrl("http://h5.m.taobao.com/js/src/weexlist.js");
//        weex compile src/foo.vue 目录
        String url = getIntent().getStringExtra(KEY);
        if (TextUtils.isEmpty(url)) {
            Tools.toastInBottom(this, "参数错误");
            finish();
        } else {
            loadWeexPageFromUrl(url);
        }
    }
}
