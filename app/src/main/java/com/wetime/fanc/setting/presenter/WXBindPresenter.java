package com.wetime.fanc.setting.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.wetime.fanc.setting.iviews.IWXBindView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class WXBindPresenter {
    private IWXBindView iView;

    public WXBindPresenter(IWXBindView iView) {
        this.iView = iView;
    }

    public void getBindResult(String code) {

        OkHttpUtils
                .post()
                .url(Const.WEIXIN_BIND)
                .addParams("code", code)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean msg = getGsonInstance().fromJson(s, BaseBean.class);
                        if (msg.getError() == 0)
                            iView.onBindResult(msg);
                    }
                });
    }


}
