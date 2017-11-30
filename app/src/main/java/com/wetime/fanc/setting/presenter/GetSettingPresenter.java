package com.wetime.fanc.setting.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.setting.bean.SettingPageBean;
import com.wetime.fanc.setting.iviews.IGetSettingView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetSettingPresenter {
    private IGetSettingView iView;

    public GetSettingPresenter(IGetSettingView iView) {
        this.iView = iView;
    }

    public void getSettinig() {
        OkHttpUtils
                .post()
                .url(Const.USER_PROFILE_SETTING)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        SettingPageBean bean = GsonUtils.getGsonInstance().fromJson(s, SettingPageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetSetting(bean);
                    }
                });
    }


}
