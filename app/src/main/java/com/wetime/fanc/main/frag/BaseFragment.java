package com.wetime.fanc.main.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.wetime.fanc.R;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.utils.SharePreferenceUtil;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;


public class BaseFragment extends Fragment implements IBaseVIew {
    public SharePreferenceUtil spu;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void showLoading(boolean can) {
        Tools.showWaitDialog(getContext(), can);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spu = new SharePreferenceUtil(getActivity(), "wetime");
    }

    @Override
    public String getToken() {
        return spu.getToken();
    }

    @Override
    public void showLoading() {
        Tools.showWaitDialog(getActivity());
    }

    @Override
    public void onTimeOut() {
//        Tools.toastInBottom(getActivity(), getString(R.string.tips_timeout));
        Tools.logout(getActivity());
//        FApp.getInstance().removeALLActivity();
//        Intent go = new Intent(getActivity(), LoginActivity.class);
//        startActivity(go);
        EventBus.getDefault().post(new LogoutEvent());
    }

    @Override
    public void dismissLoading() {
        Tools.hideWaitDialog();
    }

    @Override
    public void onNetError() {
        Context mContext = getActivity();
        if (mContext == null)
            return;
        if (Tools.isNetworkAvailable(mContext)) {
            Tools.toastInBottom(getActivity(), getString(R.string.tips_net_error));
        } else {
            Tools.toastInBottom(getActivity(), getString(R.string.tips_no_net_available));
        }

    }

    @Override
    public void onError(String s) {
        Tools.toastInBottom(getActivity(), s);
    }

    @Override
    public void onFormJsonError() {
        Tools.toastInBottom(getActivity(), getString(R.string.tips_form_json_error));
    }

    @Override
    public void onNoPermission() {

    }

    @Override
    public String getJd() {
        return spu.getValue("jd");
    }

    @Override
    public String getWd() {
        return spu.getValue("wd");
    }

    protected void refresh(){

    }

}
