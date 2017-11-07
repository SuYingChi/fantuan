package com.wetime.fanc.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.king.batterytest.fbaselib.main.BaseActivity;
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
//    public void wxLogin() {
//        if (!MyApp.mWxApi.isWXAppInstalled()) {
//            UIUtils.showToast("您还未安装微信客户端");
//            return;
//        }
//        final SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "diandi_wx_login";
//        MyApp.mWxApi.sendReq(req);
//    }

}
