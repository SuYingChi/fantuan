package com.wetime.fanc.weex.module;

import android.content.Intent;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.weex.WeexLocalActivity;
import com.wetime.fanc.weex.WeexURLActivity;

public class JumpNativeModule extends WXModule {
    @JSMethod(uiThread = true)
    public void goUserCard(String id) {
//        Toast.makeText(mWXSDKInstance.getContext(),msg, Toast.LENGTH_SHORT).show();
        Intent go = new Intent(mWXSDKInstance.getContext(), UserCardActivity.class);
        go.putExtra("num", "3");
        go.putExtra("index", 0);
        go.putExtra("id", id);
        mWXSDKInstance.getContext().startActivity(go);
    }

    @JSMethod(uiThread = true)
    public void goOtherLocalWeex(String name) {
        WeexLocalActivity.startWeexLocalActivity(mWXSDKInstance.getContext(), name);
    }

    @JSMethod(uiThread = true)
    public void goOtherURLWeex(String url) {
        WeexURLActivity.startWeexURLActivity(mWXSDKInstance.getContext(), url);
    }

    @JSMethod(uiThread = true)
    public void showToast(String msg) {
        Tools.toastInBottom(mWXSDKInstance.getContext(), msg);
    }
    @JSMethod(uiThread = false)
    public void getToken(JSCallback callback) {
        callback.invoke(Tools.getSpu(mWXSDKInstance.getContext()).getToken());
//        return Tools.getSpu(mWXSDKInstance.getContext()).getToken();
    }
}
