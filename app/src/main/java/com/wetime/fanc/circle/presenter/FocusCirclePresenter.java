package com.wetime.fanc.circle.presenter;


import android.content.Context;
import android.widget.Toast;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class FocusCirclePresenter {
    /**
     *
     * @param context
     * @param token
     * @param follow id=圈子id follow：1=关注 0=取关
     * @param following_id
     */

    public void focusCircle(Context context, String token, String follow, String following_id) {
        OkHttpUtils
                .post()
                .url(Const.ATTENTION_CIRLCLE)
                .addHeader("token", token)
                .addParams("follow", follow)
                .addParams("id", following_id)
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
