package com.wetime.fanc.my.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.my.bean.MyInfoBean;
import com.wetime.fanc.my.iviews.IGetMyInfoView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

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
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyInfoBean bean = GsonUtils.getGsonInstance().fromJson(s, MyInfoBean.class);
                        if (bean.getError() == 0)
                            iView.onGetUserInfo(bean);
                    }
                });
    }


}
