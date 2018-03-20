package com.wetime.fanc.shop.presenter;


import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.shop.iviews.IGetShopNewsView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopNewsPresenter {
    private IGetShopNewsView iView;

    public GetShopNewsPresenter(IGetShopNewsView iView) {
        this.iView = iView;
    }

    public void getNews() {
        OkHttpUtils
                .post()
                .url(Const.NEWS_MALL)
                .addParams("mall_id", iView.getMid())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        NewsListBean bean = GsonUtils.getGsonInstance().fromJson(s, NewsListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetNews(bean);
                    }
                });
    }


}
