package com.wetime.fanc.login.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.MainActivity;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.iviews.IInvalidCodeView;
import com.wetime.fanc.login.iviews.ISendSMSView;
import com.wetime.fanc.login.presenter.InvalidCodePresenter;
import com.wetime.fanc.login.presenter.SendSMSPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeLoginActivity extends BaseActivity implements ISendSMSView, IInvalidCodeView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_pswlogin)
    TextView tvPswlogin;
    private boolean runningThree = false;
    private SendSMSPresenter sendSMSPresenter;
    private InvalidCodePresenter invalidCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codelogin);
        ButterKnife.bind(this);
        tvTitle.setText("短信验证码登录");
        sendSMSPresenter = new SendSMSPresenter(this);
        invalidCodePresenter = new InvalidCodePresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.tv_ok, R.id.iv_back, R.id.tv_send, R.id.tv_pswlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_ok:
                if (etCode.getText().length() != 6) {
                    Tools.toastInBottom(this, "请输入正确填写验证码");
                    return;
                }
                invalidCodePresenter.invalidCode(etPhone.getText().toString(), etCode.getText().toString());
                Tools.hideSoftInput(this);
                break;
            case R.id.tv_send:
                if (etPhone.getText().length() != 11) {
                    Tools.toastInBottom(this, "请输入正确手机号");
                    return;
                }
                etCode.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etCode,InputMethodManager.SHOW_FORCED);

                if (!runningThree)
                    sendSMSPresenter.sendSMS(etPhone.getText().toString());
                break;
            case R.id.tv_pswlogin:
                finish();
                Intent gopsw = new Intent(this, PSWLoginActivity.class);
                startActivity(gopsw);
                break;
        }
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
    protected void onDestroy() {
        super.onDestroy();
        downTimer.cancel();
    }

    @Override
    public void onSendResult(BaseBean bean) {
        if (bean.getError() == 0 && !runningThree)
            downTimer.start();
    }

    @Override
    public void onInvalidResult(LoginResultBean bean) {
        if (bean.getError() == 0) {
            spu.setValue("token", bean.getData().getToken());
//            Intent goMain = new Intent(this, MainActivity.class);
//            startActivity(goMain);
            finish();
            EventBus.getDefault().post(new LoginEvent());
        }
    }
}
