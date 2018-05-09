package com.wetime.fanc.home.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomePageAttentionBean;
import com.wetime.fanc.home.iviews.IHomePageAttentionView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by admin on 2018/5/4.
 */

public class HomePageAttentionFragmentPresenter {

    private IHomePageAttentionView iView;

    public HomePageAttentionFragmentPresenter(IHomePageAttentionView iView) {
        this.iView = iView;
    }

    public void getAttentionPage() {
        OkHttpUtils
                .post()
                .url(Const.FOLLOW_INGDYNAMICS)
                .addHeader("token", iView.getToken())
                .addParams("pn", iView.getPage())
                .addParams("limit", "20")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomePageAttentionBean bean = GsonUtils.getGsonInstance().fromJson(s, HomePageAttentionBean.class);
                        if (bean.getError() == 0) {
                            iView.onGetAttention(bean);
                        }

                    }
                });
    }
}
