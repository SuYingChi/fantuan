package com.wetime.fanc.home.presenter;


import android.util.Log;

import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.MyStringCallback;
import com.wetime.fanc.home.iviews.IBindPushView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class BindPushPresenter {
    private IBindPushView iView;

    public BindPushPresenter(IBindPushView iView) {
        this.iView = iView;
    }

    public void bindPush(String xgtoken) {
        Log.d("TPush", "xg token= " + xgtoken);
        Log.d("TPush", "token = " + iView.getToken());
        OkHttpUtils
                .post()
                .url(Const.PUSH_BIND)
                .addParams("token", iView.getToken())
                .addParams("push_token", xgtoken)
                .addParams("platform", "1")
                .build()
                .execute(new MyStringCallback(iView, false, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        Log.d("TPush", "bind success = " + s);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0) {
                            Log.d("TPush", "bind success");
                        }
                    }
                });
    }


}
