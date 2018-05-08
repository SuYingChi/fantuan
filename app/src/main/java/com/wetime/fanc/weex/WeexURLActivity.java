package com.wetime.fanc.weex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXRenderStrategy;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.weex.module.JumpNativeModule;
import com.wetime.fanc.weex.module.MyModule;

import java.util.HashMap;
import java.util.Map;


public class WeexURLActivity extends BaseActivity implements IWXRenderListener {
    private static final String KEY = "url";
    private WXSDKInstance mWXSDKInstance;
    private FrameLayout mContainer;
    public static void startWeexURLActivity(Context context, @NonNull String url) {
        Intent intent = new Intent(context, WeexURLActivity.class);
        intent.putExtra(KEY, url);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        String url = getIntent().getStringExtra(KEY);
        if (TextUtils.isEmpty(url)) {
            Tools.toastInBottom(this, "参数错误");
            finish();
        }

        mContainer =  findViewById(R.id.container);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * pageName:自定义，一个标示符号。
         * url:远程bundle JS的下载地址
         * options:初始化时传入WEEX的参数，比如 bundle JS地址
         * flag:渲染策略。WXRenderStrategy.APPEND_ASYNC:异步策略先返回外层View，其他View渲染完成后调用onRenderSuccess。WXRenderStrategy.APPEND_ONCE 所有控件渲染完后后一次性返回。
         */
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);
        mWXSDKInstance.renderByUrl("WXSample",url,options,null, WXRenderStrategy.APPEND_ONCE);
        try {
            boolean isok = WXSDKEngine.registerModule("JumpNativePage", JumpNativeModule.class);
            Log.e("zk app", "onCreate: " + isok);
            isok = WXSDKEngine.registerModule("MyModule", MyModule.class);
            Log.e("zk app", "onCreate: " + isok);
        } catch (WXException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        mContainer.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
