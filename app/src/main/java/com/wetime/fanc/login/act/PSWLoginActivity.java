package com.wetime.fanc.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.home.act.MainActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.iviews.ILoginView;
import com.wetime.fanc.login.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PSWLoginActivity extends BaseActivity implements ILoginView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswlogin);
        ButterKnife.bind(this);
        tvTitle.setText("账号密码登录");
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onBackPressed() {
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }


    @OnClick({R.id.iv_back, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_ok:
                if (etPhone.getText().length() != 11) {
                    Tools.toastInBottom(this, "请输入正确手机号");
                    return;
                }
                Tools.hideSoftInput(this);
                if (etCode.getText().length() == 0) {
                    Tools.toastInBottom(this, "请输入正确密码");
                    return;
                }
                loginPresenter.getloginResult();
                break;
        }
    }

    @Override
    public void onLoginResult(LoginResultBean bean) {
        if (bean.getError() == 0) {
            spu.setValue("token", bean.getData().getToken());
            Intent goMain = new Intent(this, MainActivity.class);
            startActivity(goMain);
            EventBus.getDefault().post(new LoginEvent(GsonUtils.getGsonInstance().toJson(bean)));
            onBackPressed();
        }
    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString();
    }

    @Override
    public String getPSW() {
        return etCode.getText().toString();
    }
}
