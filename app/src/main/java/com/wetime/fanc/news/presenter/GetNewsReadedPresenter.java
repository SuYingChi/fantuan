package com.wetime.fanc.news.presenter;


import android.util.Log;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.utils.Const;

import okhttp3.Call;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetNewsReadedPresenter {

    public void getNewReaded(String id, String token) {

        OkHttpUtils.post().url(Const.NEWSARTICLE_READED)
                .addHeader("token", token)
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("zk", "onResponse: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("zk", "onResponse: " + response);
                    }
                });
    }

}
