package com.wetime.fanc.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.login.presenter.ValidateCodeLoginPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.iviews.IValidateCodeView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeLoginActivity extends BaseActivity implements /*ISendSMSView,*/ IValidateCodeView {


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
  /*  private boolean runningThree = false;
    private SendLoginSMSPresenter sendLoginSMSPresenter;*/
    private ValidateCodeLoginPresenter validateCodeLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codelogin);
        ButterKnife.bind(this);
        tvTitle.setText("短信验证码登录");
     /*   sendLoginSMSPresenter = new SendLoginSMSPresenter(this);*/
        validateCodeLoginPresenter = new ValidateCodeLoginPresenter(this);
    }

    @Override
    public void onBackPressed() {
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }

    @OnClick({R.id.tv_not_receive, R.id.tv_ok, R.id.iv_back, R.id.tv_send, R.id.tv_pswlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_not_receive:
                Tools.showTipsDialog(mContext,"请联系客服处理","客服电话：400-3663-2552","","确定",null,null);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_ok:
                if (etCode.getText().length() != 6) {
                    Tools.toastInBottom(this, "请输入正确验证码");
                    return;
                }
                validateCodeLoginPresenter.validate(etPhone.getText().toString(), etCode.getText().toString());
                break;
            /*case R.id.tv_send:
                if (etPhone.getText().length() != 11) {
                    Tools.toastInBottom(this, "请输入正确手机号");
                    return;
                }
                etCode.requestFocus();
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(etCode,InputMethodManager.SHOW_FORCED);

                if (!runningThree)
                    sendLoginSMSPresenter.sendSMS(etPhone.getText().toString());
                break;*/
            case R.id.tv_pswlogin:
                Intent gopsw = new Intent(this, PSWLoginActivity.class);
                startActivity(gopsw);
                break;
        }
    }

/*    private CountDownTimer downTimer = new CountDownTimer(60 * 1000 + 200, 1000) {
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
    };*/

/*    @Override
    protected void onDestroy() {
        super.onDestroy();
        downTimer.cancel();
    }

    @Override
    public void onSendResult(BaseBean bean) {
        if (bean.getError() == 0 && !runningThree)
            downTimer.start();
    }*/

    @Override
    public void onResult(LoginResultBean bean) {
        if (bean.getError() == 0) {
            spu.setValue("token", bean.getData().getToken());
//            Intent goMain = new Intent(this, MainActivity.class);
//            startActivity(goMain);

            EventBus.getDefault().post(new LoginEvent(GsonUtils.getGsonInstance().toJson(bean)));
            onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        finish();
    }

}
