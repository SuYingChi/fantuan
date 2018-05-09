package com.wetime.fanc.my.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.my.bean.MyInfoBean;
import com.wetime.fanc.my.bean.MyProfileBean;
import com.wetime.fanc.my.iviews.IGetMyInfoView;
import com.wetime.fanc.my.iviews.IGetMyprofileView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetMyProfilePresenter {
    private IGetMyprofileView iView;

    public GetMyProfilePresenter(IGetMyprofileView iView) {
        this.iView = iView;
    }

    public void getUserInfo() {
        OkHttpUtils
                .post()
                .url(Const.JV_USER_PROFILE)
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyProfileBean bean = GsonUtils.getGsonInstance().fromJson(s, MyProfileBean.class);
                        if (bean.getError() == 0)
                            iView.onGetMyProfile(bean);
                    }
                });
    }


}
