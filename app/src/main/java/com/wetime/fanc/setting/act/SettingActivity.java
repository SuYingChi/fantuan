package com.wetime.fanc.setting.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.my.act.MyCollectActivity;
import com.wetime.fanc.utils.Tools;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.R;
import com.wetime.fanc.about.act.AboutActivity;
import com.wetime.fanc.setting.bean.SettingPageBean;
import com.wetime.fanc.setting.event.UnBindWXEvent;
import com.wetime.fanc.setting.iviews.IGetSettingView;
import com.wetime.fanc.setting.iviews.ILogoutView;
import com.wetime.fanc.setting.iviews.IWXBindView;
import com.wetime.fanc.setting.presenter.GetSettingPresenter;
import com.wetime.fanc.setting.presenter.LogoutPresenter;
import com.wetime.fanc.setting.presenter.WXBindPresenter;
import com.wetime.fanc.web.WebActivity;
import com.wetime.fanc.wxapi.WXLoginCodeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements ILogoutView, IGetSettingView, IWXBindView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_myinfo)
    TextView tvMyinfo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_psw)
    TextView tvPsw;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    private LogoutPresenter logoutPresenter;
    private GetSettingPresenter getSettingPresenter;
    private SettingPageBean bean;
    private WXBindPresenter wxBindPresenter;

    public static void goSettingAct(Context context){
        Intent go =  new Intent(context,SettingActivity.class);
        context.startActivity(go);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitle.setText("设置");
        getSettingPresenter = new GetSettingPresenter(this);

        wxBindPresenter = new WXBindPresenter(this);
    }

    @Override
    public void onResume() {
        getSettingPresenter.getSettinig();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @OnClick({R.id.iv_back, R.id.tv_myinfo, R.id.tv_phone, R.id.tv_wx, R.id.tv_psw, R.id.tv_about, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_myinfo:
                Intent goInfo = new Intent(this, UserinfoActivity.class);
                startActivity(goInfo);
                break;

            case R.id.tv_wx:
                break;

            case R.id.tv_about:
                Intent goAbout = new Intent(this, AboutActivity.class);
                startActivity(goAbout);
                break;
            case R.id.tv_logout:
                Tools.showTipsDialog(this, "", "确定要退出登录吗？", null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logoutPresenter = new LogoutPresenter(SettingActivity.this);
                        logoutPresenter.logout();
                    }
                });

                break;
        }
    }

    @Override
    public void onLogout(BaseBean bean) {
        if (bean.getError() == 0) {
            Tools.logout(this);
            EventBus.getDefault().post(new LogoutEvent());
            Intent goLogin = new Intent(this, LoginActivity.class);
            startActivity(goLogin);
            finish();
        }
    }

    @Override
    public void onGetSetting(SettingPageBean bean) {
        this.bean = bean;
        initView();
    }

    private void initView() {
        if (bean == null)
            return;
        if (bean.getData().getPhone().equals("")) {
            tvPhone.setText("未绑定");
            tvPhone.setTextColor(Color.parseColor("#ff3f53"));
            tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goWeb(bean.getData().getLink().getPhone_bind().getUrl());
                }
            });
        } else {
            tvPhone.setText(bean.getData().getPhone());
            tvPhone.setTextColor(Color.parseColor("#999999"));
            tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tools.showTipsDialog(mContext, "", "是否修改手机号", null, new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            goWeb(bean.getData().getLink().getPhone_change().getUrl());
                        }
                    });
                }
            });
        }
        if (bean.getData().getWeixin() == null) {
            tvWx.setText("未绑定");
            tvWx.setTextColor(Color.parseColor("#ff3f53"));
            tvWx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tools.showTipsDialog(mContext, "提示", "绑定微信号可同步订单信息", "取消", "立即绑定", null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            wxLogin();
                        }
                    });
                }
            });
        } else {
            tvWx.setText(bean.getData().getWeixin().getNickname());
            tvWx.setTextColor(Color.parseColor("#999999"));
            tvWx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //去 解绑页面
                    Intent gobind = new Intent(mContext, BindWeixinActivity.class);
                    gobind.putExtra("weixin", bean.getData().getWeixin());
                    startActivity(gobind);
                }
            });
        }
        if (bean.getData().getPassword().equals("")) {
            tvPsw.setText("设置登录密码");
            tvPsw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.getData().getPhone().equals("")) {
                        Tools.toastInBottom(mContext, "请先绑定手机号");
                        return;
                    }
                    goWeb(bean.getData().getLink().getPassword_create().getUrl());
                }
            });
        } else {
            tvPsw.setText("修改");
            tvPsw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bean.getData().getPhone().equals("")) {
                        Tools.toastInBottom(mContext, "请先绑定手机号");
                        return;
                    }
                    goWeb(bean.getData().getLink().getPassword_change().getUrl());
                }
            });
        }
    }


    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WXLoginCodeEvent event) {
        wxBindPresenter.getBindResult(event.getCode());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UnBindWXEvent event) {
        getSettingPresenter.getSettinig();
    }

    @Override
    public void onBindResult(BaseBean bean) {
        if (bean.getError() == 0) {
            getSettingPresenter.getSettinig();
        }
    }
}
