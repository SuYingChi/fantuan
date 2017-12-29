package com.wetime.fanc.login.act;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.WXBindPhoneEvent;
import com.wetime.fanc.login.iviews.IWXLoginView;
import com.wetime.fanc.login.presenter.WXLoginPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;
import com.wetime.fanc.wxapi.WXLoginCodeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IWXLoginView {

    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    private WXLoginPresenter wxLoginPresenter;
    private String temptoken = "";
    private LoginResultBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        wxLoginPresenter = new WXLoginPresenter(this);

        tvProtocol.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvProtocol.getPaint().setAntiAlias(true);//抗锯齿
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

    @OnClick({R.id.iv_close, R.id.ll_weixin, R.id.ll_phone, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                onBackPressed();
                break;
            case R.id.ll_weixin:
                wxLogin();
                break;
            case R.id.tv_protocol:
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", Const.PROTOCOL);
                mContext.startActivity(goweb);
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
        this.bean = bean;
        if (bean.getError() == 0) {
            temptoken = bean.getData().getToken();
            if (TextUtils.isEmpty(bean.getData().getPhone())) {
                Tools.goWeb(mContext, bean.getData().getBind_phone_url());
                return;
            }
            spu.setValue("token", bean.getData().getToken());
            finish();
            EventBus.getDefault().post(new LoginEvent(GsonUtils.getGsonInstance().toJson(bean)));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WXBindPhoneEvent event) {
        spu.setValue("token", temptoken);
        finish();
        EventBus.getDefault().post(new LoginEvent(GsonUtils.getGsonInstance().toJson(bean)));
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
