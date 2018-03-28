package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.utils.Const;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class FocusPresenter {

    public void focusUser(String token, String follow,String following_id) {
        OkHttpUtils
                .post()
                .url(Const.USER_FOLLOW)
                .addParams("token", token)
                .addParams("follow", follow)
                .addParams("following_id", following_id)
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
