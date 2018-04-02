package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.news.bean.AllChannelListBean;
import com.wetime.fanc.news.iviews.IGetMyChannelView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetMyChannelPresenter {
    private IGetMyChannelView iview;

    public GetMyChannelPresenter(IGetMyChannelView iview) {
        this.iview = iview;
    }

    public void getMyChannelResult() {

        OkHttpUtils
                .post()
                .url(Const.NEWS_CATEGORY_LOAD)
                .addParams("token", iview.getToken())
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        AllChannelListBean msg = getGsonInstance().fromJson(s, AllChannelListBean.class);
                        if (msg.getError() == 0)
                            iview.onGetMyChannel(msg);
                    }
                });
    }
}
