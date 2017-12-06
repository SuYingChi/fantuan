package com.king.batterytest.fbaselib.main;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.setting.event.LogoutEvent;
import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.utils.LogUtils;
import com.king.batterytest.fbaselib.utils.SharePreferenceUtil;
import com.king.batterytest.fbaselib.utils.Tools;
import com.umeng.analytics.MobclickAgent;
import com.wetime.fanc.R;

import org.greenrobot.eventbus.EventBus;

//import com.wetime.fanc.FApp;


public class BaseActivity extends AppCompatActivity implements IBaseVIew {
    public SharePreferenceUtil spu;
    private boolean wihteBar = true;

    public void setWihteBar(boolean wihteBar) {
        this.wihteBar = wihteBar;
    }

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        spu = Tools.getSpu(this);
        initStateBar();
//        FApp.getInstance().addActivity(this);
//        getTheme()
        mContext = this;
//        Tools.MIUISetStatusBarLightMode(getWindow(),wihteBar);
        LogUtils.d("color=" + getDarkColorPrimary());
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());

        return res;
    }

    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.resourceId;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    public void initStateBar() {
        ImmersionBar.with(this).statusBarColor(R.color.white_lib).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();

    }

    @Override
    public void showLoading() {
        Tools.showWaitDialog(this);
    }

    @Override
    public void showLoading(boolean can) {
        Tools.showWaitDialog(this, can);
    }

    @Override
    public void onTimeOut() {
//        Tools.toastInBottom(this, getString(R.string.tips_timeout));
//        Tools.logout(this);
//        FApp.getInstance().removeALLActivity();
//        Intent go = new Intent(this, LoginActivity.class);
//        startActivity(go);
        EventBus.getDefault().post(new LogoutEvent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void dismissLoading() {
        Tools.hideWaitDialog();
    }

    @Override
    public void onNetError() {
        Tools.toastInBottom(this, getString(R.string.tips_net_error));
    }

    @Override
    public void onFormJsonError() {
        Tools.toastInBottom(this, getString(R.string.tips_form_json_error));
    }

    @Override
    public void onNoPermission() {

    }

    @Override
    public String getToken() {
        return spu.getToken();
    }

    @Override
    public String getJd() {
        return spu.getValue("jd");
    }

    @Override
    public String getWd() {
        return spu.getValue("wd");
    }

    @Override
    public void onError(String s) {
        Tools.toastInBottom(this, s);
    }
}
