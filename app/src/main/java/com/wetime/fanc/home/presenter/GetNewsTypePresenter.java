package com.wetime.fanc.home.presenter;


import android.util.Log;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.iviews.IGetNewsTypeView;
import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetNewsTypePresenter {
    private IGetNewsTypeView iView;

    public GetNewsTypePresenter(IGetNewsTypeView iView) {
        this.iView = iView;
    }

    public void getNews() {
        OkHttpUtils
                .post()
                .url(Const.NEWS)
                .addHeader("token", iView.getToken())
                .addParams("total", iView.getTotal())
                .addParams("cid", iView.getCid())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
//                        NewsListBean bean = GsonUtils.getGsonInstance().fromJson(s, NewsListBean.class);
//                        iView.onGetNews(bean);
                        NewsListBean bean = GsonUtils.getGsonInstance().fromJson(s, NewsListBean.class);
                        iView.onGetNews(bean);
                    }
                });
    }


}
