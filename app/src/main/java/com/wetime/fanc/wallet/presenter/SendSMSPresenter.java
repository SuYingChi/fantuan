package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.login.iviews.ISendSMSView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.fan.http.okhttp.OkHttpUtils;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class SendSMSPresenter {
    private ISendSMSView iView;

    public SendSMSPresenter(ISendSMSView iView) {
        this.iView = iView;
    }

    public void sendSMS(String phone) {

        OkHttpUtils
                .post()
                .url(Const.SMS_SEND)
                .addParams("phone", phone)
                .build()
                .execute(new DataStringCallback(iView, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean msg = getGsonInstance().fromJson(s, BaseBean.class);
                        if (msg.getError() == 0)
                            iView.onSendResult(msg);
                    }
                });
    }


}
