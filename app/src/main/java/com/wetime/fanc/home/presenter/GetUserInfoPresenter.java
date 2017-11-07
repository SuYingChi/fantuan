package com.wetime.fanc.home.presenter;


import android.util.Log;

import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.king.batterytest.fbaselib.utils.MyStringCallback;
import com.wetime.fanc.home.bean.MyInfoBean;
import com.wetime.fanc.home.iviews.IBindPushView;
import com.wetime.fanc.home.iviews.IGetMyInfoView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetUserInfoPresenter {
    private IGetMyInfoView iView;

    public GetUserInfoPresenter(IGetMyInfoView iView) {
        this.iView = iView;
    }

    public void getUserInfo() {
        OkHttpUtils
                .post()
                .url(Const.USER_PROFILE)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView,false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyInfoBean bean = GsonUtils.getGsonInstance().fromJson(s, MyInfoBean.class);
                        if(bean.getError()==0)
                            iView.onGetUserInfo(bean);
                    }
                });
    }


}
