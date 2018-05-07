package com.wetime.fanc.home.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HeadRecommendBean;
import com.wetime.fanc.home.iviews.IRecommendView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by admin on 2018/5/6.
 */

public class RecommendPresenter {
    private  IRecommendView iRecommendView;

    public RecommendPresenter(IRecommendView iRecommendView){
        this.iRecommendView = iRecommendView;

    }

    public void getRecommend() {
        OkHttpUtils
                .post()
                .url(Const.RECOMMEDN)
                .addHeader("token", iRecommendView.getToken())
                .addParams("limit", 20+"")
                .addParams("pn", iRecommendView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iRecommendView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HeadRecommendBean bean = GsonUtils.getGsonInstance().fromJson(s, HeadRecommendBean.class);
                        if (bean.getError() == 0)
                            iRecommendView.onGetRecommend(bean);
                    }
                });
    }
}
