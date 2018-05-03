package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.iviews.IValidateCodeView;
import com.wetime.fanc.login.iviews.ISendSMSView;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.presenter.InvalidCodePresenter;
import com.wetime.fanc.wallet.presenter.SendSMSPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerfyPhoneNumActivity extends BaseActivity implements ISendSMSView,  IValidateCodeView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_nocode)
    TextView tvNocode;

    private boolean runningThree = false;
    private SendSMSPresenter sendSMSPresenter;
    private InvalidCodePresenter invalidCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phonenum);
        ButterKnife.bind(this);
        tvTitle.setText("验证手机号");
        String phone = getIntent().getStringExtra("phone");
        phone = phone.substring(0, 3) + " **** " + phone.substring(7, phone.length());
        tvNum.setText(phone);
        sendSMSPresenter = new SendSMSPresenter(this);
        invalidCodePresenter = new InvalidCodePresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_send, R.id.tv_ok,R.id.tv_nocode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_send:
                if (!runningThree)
                    sendSMSPresenter.sendSMS(getIntent().getStringExtra("phone"));
                break;
            case R.id.tv_ok:
                if (etCode.getText().length() != 6) {
                    Tools.toastInBottom(this, "请输入正确验证码");
                    return;
                }
                invalidCodePresenter.invalidCode(getIntent().getStringExtra("phone"),
                        etCode.getText().toString());
                break;
            case R.id.tv_nocode:
                Tools.showTipsDialog(mContext, "请联系客服处理",
                        "客服电话：400-3663-2552", "", "确定", null, null);
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
    public void onSendResult(BaseBean bean) {
        if (bean.getError() == 0 && !runningThree)
            downTimer.start();
    }

    @Override
    public void onResult(LoginResultBean bean) {
        Intent go = new Intent(mContext, SetPayPwdActivity.class);
        go.putExtra("title", "设置新密码");
        startActivity(go);
        finish();
    }

}
