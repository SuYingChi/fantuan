package com.wetime.fanc.home.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.home.iviews.IGetHomeHotSearchView;
import com.wetime.fanc.home.iviews.IGetHomePageView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomePagePresenter {
    private IGetHomePageView iView;

    public GetHomePagePresenter(IGetHomePageView iView) {
        this.iView = iView;
    }

    public void getHomePage() {
        OkHttpUtils
                .post()
                .url(Const.HOMEPAGE)
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomePageBean bean = GsonUtils.getGsonInstance().fromJson(s, HomePageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetHomePage(bean);
                    }
                });
    }


}
