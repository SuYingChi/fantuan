package com.wetime.fanc.home.presenter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.Callback;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.ToastUtils;
import com.wetime.fanc.utils.Tools;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yuxun on 2018/5/9.
 */

public class AttentionClickZanPresenter {


    public static void clickZan(Context context, String id, boolean like,AttentionClickZanPresenter.OnLikeAttentionListener iView)
    {
        OkHttpUtils
                .post()
                .url(Const.LIKE_ATTENTION)
                .addHeader("token", Tools.getSpu(context).getToken())
                .addParams("id", id)
                .addParams("type", 0+"")
                .addParams("like",String.valueOf(like))
                .build()
                .execute(new Callback<ErrorBean>() {
                    @Override
                    public ErrorBean parseNetworkResponse(Response response, int i) throws IOException {
                        String s = response.body().string();
                        return   GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);

                    }
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }
                    @Override
                    public void onResponse(ErrorBean bean, int i) {
                        if (bean.getError() == 0){
                            iView.onLikeAttentionListener(true);
                            Log.d("suyingchi", "dianzan onResponse: true"+bean.getMsg());
                        }else {
                            iView.onLikeAttentionListener(false);
                            Log.d("suyingchi", "dianzan onResponse: fail"+bean.getMsg());

                        }
                    }

                });
    }

    public  interface OnLikeAttentionListener {
        void onLikeAttentionListener(boolean isSuccess);
    }
}

