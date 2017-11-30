package com.wetime.fanc.wxapi;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.king.batterytest.fbaselib.utils.Tools;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

import static com.wetime.fanc.application.FApp.mWxApi;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果没回调onResp，八成是这句没有写
//        mWxApi = WXAPIFactory.createWXAPI(this, "wx2fbcb61b6e5b1384", false);
//        // 将该app注册到微信
//        mWxApi.registerApp("wx2fbcb61b6e5b1384");
        mWxApi.handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
//        Log.e("zk wx", resp.errStr);
//        Log.e("zk wx", "错误码 : " + resp.errCode + "");
        switch (resp.errCode) {

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Tools.toastInBottom(this, "用户取消");
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        Log.e("zk wx", "code = " + code);
//                        Tools.toastInBottom(this, "code = " + code);
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注
                        // 意是get请求
                        EventBus.getDefault().post(new WXLoginCodeEvent(code));
                        break;

                    case RETURN_MSG_TYPE_SHARE:
//                        UIUtils.showToast("微信分享成功");
//                        finish();
                        break;
                }
                break;
        }
        finish();
    }
}

