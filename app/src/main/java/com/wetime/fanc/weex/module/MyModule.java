package com.wetime.fanc.weex.module;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

public class MyModule extends WXModule {
    //run ui thread
    @WXModuleAnno
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(),msg, Toast.LENGTH_SHORT).show();
    }

    //run JS thread
    @JSMethod(uiThread = false)
    public void fireEventSyncCall(){
        //implement your module logic here
    }
}
