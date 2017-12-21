package com.wetime.fanc.login.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.iviews.ILoginView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class LoginPresenter {
    private ILoginView iView;

    public LoginPresenter(ILoginView iView) {
        this.iView = iView;
    }

    public void getloginResult() {

        OkHttpUtils
                .post()
                .url(Const.LOGIN_PSW)
                .addParams("phone", iView.getPhone())
                .addParams("password", iView.getPSW())
                .build()
                .execute(new DataStringCallback(iView, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        LoginResultBean msg = getGsonInstance().fromJson(s, LoginResultBean.class);
                        if (msg.getError() == 0)
                            iView.onLoginResult(msg);
                    }
                });
    }


}
