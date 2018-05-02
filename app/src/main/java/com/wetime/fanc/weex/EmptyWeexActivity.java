package com.wetime.fanc.weex;

import android.os.Bundle;

public class EmptyWeexActivity extends WeexBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        loadWeexPageFromUrl("http://h5.m.taobao.com/js/src/weexlist.js");
//        weex compile src/foo.vue 目录
        loadWeexPageFromUrl("http://192.168.11.139:8081/dist/fan.js");
    }
}
