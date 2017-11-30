package com.wetime.fanc.setting.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.setting.iviews.ILogoutView;
import com.wetime.fanc.setting.iviews.ISetUsernameView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class SetUserNamePresenter {
    private ISetUsernameView iView;

    public SetUserNamePresenter(ISetUsernameView iView) {
        this.iView = iView;
    }

    public void setUserName(String name) {
        OkHttpUtils
                .post()
                .url(Const.USER_PROFILE_UPDATENAME)
                .addParams("token", iView.getToken())
                .addParams("username", name)
                .build()
                .execute(new DataStringCallback(iView) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onSetUsername(bean);
                    }
                });
    }


}
