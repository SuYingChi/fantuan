package com.wetime.fanc.home.presenter;


import com.wetime.fanc.home.bean.RedNumBean;
import com.wetime.fanc.home.iviews.IGetRedNumView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetRedNumPresenter {
    private IGetRedNumView iView;

    public GetRedNumPresenter(IGetRedNumView iView) {
        this.iView = iView;
    }

    public void getRedNum() {
        OkHttpUtils
                .post()
                .url(Const.USER_NOTICE_UNREAD)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        RedNumBean bean = GsonUtils.getGsonInstance().fromJson(s, RedNumBean.class);
                        if (bean.getError() == 0) {
                            iView.onGetRedNum(bean);
                        }

                    }
                });
    }


}
