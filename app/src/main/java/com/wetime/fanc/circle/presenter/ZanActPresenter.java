package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class ZanActPresenter {

    public void zanAct(String id, String token, String like) {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_LIKE)
                .addParams("token", token)
                .addParams("like", like)
                .addParams("dy_id", id)
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
