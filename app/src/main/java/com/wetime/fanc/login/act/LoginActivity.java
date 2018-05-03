package com.wetime.fanc.login.act;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.home.act.MainActivity;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.WXBindPhoneEvent;
import com.wetime.fanc.login.iviews.IValidateCodeView;
import com.wetime.fanc.login.iviews.ISendSMSView;
import com.wetime.fanc.login.iviews.IWXLoginView;
import com.wetime.fanc.login.presenter.SendLoginSMSPresenter;
import com.wetime.fanc.login.presenter.ValidateCodeLoginPresenter;
import com.wetime.fanc.login.presenter.WXLoginPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
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

public class LoginActivity extends BaseActivity implements IWXLoginView, ISendSMSView, IValidateCodeView {

  /*  @BindView(R.id.iv_close)
    ImageView ivClose;*/
    @BindView(R.id.ll_weixin)
    ImageButton llWeixin;
    @BindView(R.id.tv_ok)
    Button tvOk;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.mobile_login)
    TextView mobileLogin;
    @BindView(R.id.et_code)
    TextView etCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_psw_login)
    TextView tvPswLogin;
    private WXLoginPresenter wxLoginPresenter;
    private String temptoken = "";
    private LoginResultBean bean;
    private boolean runningThree = false;
    private SendLoginSMSPresenter sendLoginSMSPresenter;
    private ValidateCodeLoginPresenter validateCodeLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        wxLoginPresenter = new WXLoginPresenter(this);

        tvProtocol.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvProtocol.getPaint().setAntiAlias(true);//抗锯齿*/
        sendLoginSMSPresenter = new SendLoginSMSPresenter(this);
        validateCodeLoginPresenter = new ValidateCodeLoginPresenter(this);
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
    }

    @OnClick({R.id.ll_weixin, R.id.tv_ok,R.id.tv_send,R.id.tv_psw_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_weixin:
                wxLogin();
                break;
            case R.id.tv_protocol:
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", Const.PROTOCOL);
                mContext.startActivity(goweb);
                break;
            case R.id.tv_send:
                if (mobileLogin.getText().length() != 11) {
                    Tools.toastInBottom(this, "请输入正确手机号");
                    return;
                }
                etCode.requestFocus();

                if (!runningThree)
                    sendLoginSMSPresenter.sendSMS(mobileLogin.getText().toString());
                break;

            case R.id.tv_ok:
                if (etCode.getText().length() != 6) {
                    Tools.toastInBottom(this, "请输入正确验证码");
                    return;
                }
                validateCodeLoginPresenter.validate(mobileLogin.getText().toString(), etCode.getText().toString());

                break;
            case R.id.tv_psw_login:
                Intent gopsw = new Intent(this, PSWLoginActivity.class);
                startActivity(gopsw);
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

    private CountDownTimer downTimer = new CountDownTimer(60 * 1000 + 200, 1000) {
        @Override
        public void onTick(long l) {
            runningThree = true;
            tvSend.setText("重新获取(" + (l / 1000) + ")");
            tvSend.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
        }

        @Override
        public void onFinish() {
            runningThree = false;
            tvSend.setText("重新获取");
            tvSend.setBackgroundResource(R.drawable.bg_btn_red_corner);
        }
    };

    @Override
    public void onSendResult(BaseBean bean) {
        if (bean.getError() == 0 && !runningThree)
            downTimer.start();
    }

    @Override
    public void onResult(LoginResultBean bean) {
        if (bean.getError() == 0) {
            spu.setValue("token", bean.getData().getToken());
            Intent goMain = new Intent(this, MainActivity.class);
            startActivity(goMain);
            EventBus.getDefault().post(new LoginEvent(GsonUtils.getGsonInstance().toJson(bean)));
            onBackPressed();
        }
    }


}
