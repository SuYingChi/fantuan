package com.wetime.fanc.home.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.home.iviews.IHomePageRecommendView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by admin on 2018/5/6.
 */

public class HomePageRecommendFragmentPresenter {
    private IHomePageRecommendView iHomePageRecommendView;

    public HomePageRecommendFragmentPresenter(IHomePageRecommendView iHomePageRecommendView){
        this.iHomePageRecommendView = iHomePageRecommendView;

    }

    public void getRecommend() {
        OkHttpUtils
                .post()
                .url(Const.RECOMMEDN)
                .addHeader("token", iHomePageRecommendView.getToken())
                .addParams("limit", 20+"")
                .addParams("pn", iHomePageRecommendView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iHomePageRecommendView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomePageRecommendBean bean = GsonUtils.getGsonInstance().fromJson(s, HomePageRecommendBean.class);
                        if (bean.getError() == 0)
                            iHomePageRecommendView.onGetRecommend(bean);
                    }
                });
    }
}
