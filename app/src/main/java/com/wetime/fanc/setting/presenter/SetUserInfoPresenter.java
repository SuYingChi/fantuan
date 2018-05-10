package com.wetime.fanc.setting.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.setting.iviews.ISetUserInfoView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class SetUserInfoPresenter {
    private ISetUserInfoView iview;

    public SetUserInfoPresenter(ISetUserInfoView iview) {
        this.iview = iview;
    }

    public void setUserInfo(String key,String value) {

        OkHttpUtils
                .post()
                .url(Const.USER_SAVEUSERINFO)
                .addHeader("token", iview.getToken())
                .addParams(key, value)
                .build()
                .execute(new DataStringCallback(iview) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean msg = getGsonInstance().fromJson(s, BaseBean.class);
                        if (msg.getError() == 0)
                            iview.onSetUserInfoResult(msg);
                    }
                });
    }
}
