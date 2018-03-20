package com.wetime.fanc.login.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.login.bean.LoginResultBean;
import com.wetime.fanc.login.iviews.IInvalidCodeView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class InvalidLoginCodePresenter {
    private IInvalidCodeView iView;

    public InvalidLoginCodePresenter(IInvalidCodeView iView) {
        this.iView = iView;
    }

    public void invalidCode(String phone,String code) {

        OkHttpUtils
                .post()
                .url(Const.LOGIN_VCODE)
                .addParams("phone", phone)
                .addParams("code", code)
                .build()
                .execute(new DataStringCallback(iView, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        LoginResultBean msg = getGsonInstance().fromJson(s, LoginResultBean.class);
                        if (msg.getError() == 0)
                            iView.onInvalidResult(msg);
                    }
                });
    }


}
