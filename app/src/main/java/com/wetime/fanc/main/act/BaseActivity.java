package com.wetime.fanc.main.act;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.umeng.analytics.MobclickAgent;
import com.wetime.fanc.R;
import com.wetime.fanc.application.FApp;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.SharePreferenceUtil;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class BaseActivity extends AppCompatActivity implements IBaseVIew   {
    public SharePreferenceUtil spu;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSoftInPutMode();
        EventBus.getDefault().register(this);
        spu = Tools.getSpu(this);
        initStateBar();
        addToActManager();
        mContext = this;
    }

    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    protected void addToActManager() {
        FApp.getInstance().addActivity(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    protected void initStateBar() {
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
        Tools.logout(this);
//        FApp.getInstance().removeALLActivity();
//        Intent go = new Intent(this, LoginActivity.class);
//        startActivity(go);
        EventBus.getDefault().post(new LogoutEvent());
    }

    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        FApp.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void dismissLoading() {
        Tools.hideWaitDialog();
    }

    @Override
    public void onNetError() {
        if (Tools.isNetworkAvailable(mContext)) {
            Tools.toastInBottom(this, getString(R.string.tips_net_error));
        } else {
            Tools.toastInBottom(this, getString(R.string.tips_no_net_available));
        }
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
//        Tools.toastInBottom(this, s);
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorBean messageEvent) {

    }
}
