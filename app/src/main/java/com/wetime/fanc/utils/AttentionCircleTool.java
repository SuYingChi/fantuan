package com.wetime.fanc.utils;

import android.app.Activity;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.Callback;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/7.
 */

public class AttentionCircleTool {

    public static void attentionCircle(Activity activity,String id,OnAttentionCircleListener iView)
    {
        OkHttpUtils
                .post()
                .url(Const.ATTENTION_CIRLCLE)
                .addHeader("token", Tools.getSpu(activity).getToken())
                .addParams("id", id)
                .addParams("follow", "1")
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
                            iView.onAttentionCircleResponse(true);
                        }else {
                            iView.onAttentionCircleResponse(false);
                        }
                    }

                });
    }

    public  interface OnAttentionCircleListener {
       void onAttentionCircleResponse(boolean isSuccess);
    }


}
