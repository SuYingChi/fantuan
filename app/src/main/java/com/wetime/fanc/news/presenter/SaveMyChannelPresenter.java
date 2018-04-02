package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.news.bean.AllChannelListBean;
import com.wetime.fanc.news.iviews.IGetMyChannelView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;

import okhttp3.Call;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class SaveMyChannelPresenter {

    public void saveMyChannel(String token,String channel) {

        OkHttpUtils
                .post()
                .url(Const.NEWS_CATEGORY_SAVE)
                .addParams("token", token)
                .addParams("news_category", channel)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {

                    }
                });
    }
}
