package com.wetime.fanc.home.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomePageCircleBean;
import com.wetime.fanc.home.iviews.IHomePageCircleView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by admin on 2018/5/7.
 */

public class HomePageCircleFragmentPresenter {
    private IHomePageCircleView iView;

    public HomePageCircleFragmentPresenter(IHomePageCircleView iView) {
        this.iView = iView;
    }

    public void getCircles() {
        OkHttpUtils
                .post()
                .url(Const.ALL_CIRCLE_HOME_PAGE_RECOMMEND)
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomePageCircleBean bean = GsonUtils.getGsonInstance().fromJson(s, HomePageCircleBean.class);
                        if (bean.getError() == 0) {
                            iView.onGetCircle(bean);
                        }

                    }
                });
    }
}
