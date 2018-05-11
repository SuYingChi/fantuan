package com.wetime.fanc.my.presenter;


import com.wetime.fanc.my.bean.MyCollectionListBean;
import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.iviews.IGetMyNewsView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetMyNewsPresenter {
    private IGetMyNewsView iView;

    public GetMyNewsPresenter(IGetMyNewsView iView) {
        this.iView = iView;
    }

    public void getMyNews() {
        OkHttpUtils
                .post()
                .url(Const.NEWSARTICLE_COLLECTS)
                .addHeader("token", iView.getToken())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyCollectionListBean bean = GsonUtils.getGsonInstance().fromJson(s, MyCollectionListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetMyNews(bean);
                    }
                });
    }


}
