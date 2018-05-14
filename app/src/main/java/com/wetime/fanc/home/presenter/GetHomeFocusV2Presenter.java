package com.wetime.fanc.home.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomeListBeanV2;
import com.wetime.fanc.home.iviews.IGetHomeFocusV2View;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomeFocusV2Presenter {
    private IGetHomeFocusV2View iView;

    public GetHomeFocusV2Presenter(IGetHomeFocusV2View iView) {
        this.iView = iView;
    }

    public void getHomeFocusList() {
        OkHttpUtils
                .post()
                .url(Const.QZ_FOLLOWINGDYNAMICS)
                .addHeader("token", iView.getToken())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeListBeanV2 bean = GsonUtils.getGsonInstance().fromJson(s, HomeListBeanV2.class);
                        if (bean.getError() == 0) {
                            iView.onGetHomeFocus(bean);
                        }

                    }
                });
    }


}
