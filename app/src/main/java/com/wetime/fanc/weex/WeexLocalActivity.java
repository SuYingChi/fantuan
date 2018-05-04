package com.wetime.fanc.weex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.wetime.fanc.utils.Tools;

public class WeexLocalActivity extends WeexBaseActivity {
    /**
     * @param context
     * @param jsname  fan.js 完整名字
     */
    public static void startWeexLocalActivity(Context context, @NonNull String jsname) {
        Intent intent = new Intent(context, WeexLocalActivity.class);
        intent.putExtra("jsname", jsname);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadWeexPageFromUrl("http://h5.m.taobao.com/js/src/weexlist.js");
//        weex compile src/foo.vue 目录
        String jsName = getIntent().getStringExtra("jsname");
        if (TextUtils.isEmpty(jsName)) {
            Tools.toastInBottom(this, "参数错误");
            finish();
        } else {
            loadWeexPage(jsName);
        }

    }
}
