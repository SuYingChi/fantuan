package com.wetime.fanc.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.application.FApp;
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
    public void initStateBar() {
//        super.initStateBar();
        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarDarkFont(false)
                .fitsSystemWindows(false).init();
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
                wxLogin();
                break;
            case R.id.ll_phone:
                Intent gophone = new Intent(this, CodeLoginActivity.class);
                startActivity(gophone);
                break;
        }
    }

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
            Tools.toastInBottom(this, "您没有安装微信");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        FApp.mWxApi.sendReq(req);
    }

}
