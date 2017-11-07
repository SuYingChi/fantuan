package com.wetime.fanc.login.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.wetime.fanc.login.iviews.ISendSMSView;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.List;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;

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
