package com.wetime.fanc.circle.presenter;


import android.content.Context;
import android.widget.Toast;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class FocusPresenter {

    public void focusUser(Context context, String token, String follow, String following_id) {
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
                        ErrorBean errorBean = GsonUtils.getGsonInstance().fromJson(response, ErrorBean.class);
                        Toast.makeText(context, errorBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
