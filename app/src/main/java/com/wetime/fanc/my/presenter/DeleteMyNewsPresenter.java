package com.wetime.fanc.my.presenter;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteMyNewsPresenter {
    private IBaseVIew iView;



    public void detDleteMyNews(String id, String token) {
        OkHttpUtils
                .post()
                .url(Const.NEWS_COLLECT_CANCEL)
                .addParams("token", token)
                .addParams("article_id", id)
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
