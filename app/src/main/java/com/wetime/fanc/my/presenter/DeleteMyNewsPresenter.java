package com.wetime.fanc.my.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.utils.Const;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteMyNewsPresenter {

    public void detDleteMyNews(String id, String token) {
        OkHttpUtils
                .post()
                .url(Const.COLLECT_NEWS)
                .addParams("token", token)
                .addParams("article_id", id)
                .addParams("collect", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }


}
