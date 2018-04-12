package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.news.iviews.IGetSpecialNewsTypeView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetSpecialNewsTypePresenter {
    private IGetSpecialNewsTypeView iView;

    public GetSpecialNewsTypePresenter(IGetSpecialNewsTypeView iView) {
        this.iView = iView;
    }

    public void getNews() {
        OkHttpUtils
                .post()
                .url(Const.NEWS)
                .addParams("cid", "0")
                .addParams("token", "ipm4y0993nEM_XoLeefFw3gxio2A1cBu")
                .addParams("total", "10")
                .addParams("pn", "1")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        NewsListBean bean = GsonUtils.getGsonInstance().fromJson(s, NewsListBean.class);
                        if (bean.getError() == 0) iView.onGetSpecialNews(bean);
                    }
                });
    }


}
