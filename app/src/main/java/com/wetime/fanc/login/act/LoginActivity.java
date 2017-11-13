package com.wetime.fanc.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.FApp;
import com.king.batterytest.fbaselib.utils.Tools;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.R;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.iviews.IWXLoginView;
import com.wetime.fanc.login.presenter.WXLoginPresenter;
import com.wetime.fanc.wxapi.WXLoginCodeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IWXLoginView {

    private WXLoginPresenter wxLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        wxLoginPresenter = new WXLoginPresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_close, R.id.ll_weixin, R.id.ll_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                onBackPressed();
                break;
            case R.id.ll_weixin:
//                Intent goweixin = new Intent(this,)
                wxLogin();
//                UMShareAPI mShareAPI = UMShareAPI.get(this);
//                mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);

                break;
            case R.id.ll_phone:
                Intent gophone = new Intent(this, CodeLoginActivity.class);
                startActivity(gophone);
                break;
        }
    }
//    UMAuthListener authListener = new UMAuthListener() {
//        /**
//         * @desc 授权开始的回调
//         * @param platform 平台名称
//         */
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//
//        }
//
//        /**
//         * @desc 授权成功的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         * @param data 用户资料返回
//         */
//        @Override
//        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//
//            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
//            String openid = data.get()
//        }
//
//        /**
//         * @desc 授权失败的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         * @param t 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//
//            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        /**
//         * @desc 授权取消的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         */
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//
//            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
//        }
//    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WXLoginCodeEvent event) {
        wxLoginPresenter.getloginResult(event.getCode());
    }

    @Override
    public void onLoginResult(LoginResultBean bean) {
        if (bean.getError() == 0) {
            spu.setValue("token", bean.getData().getToken());
            finish();
            EventBus.getDefault().post(new LoginEvent());
        }
    }

    public void wxLogin() {
        if (!FApp.mWxApi.isWXAppInstalled()) {
            Tools.toastInBottom(this, "meiyouanz");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        FApp.mWxApi.sendReq(req);
    }

}
