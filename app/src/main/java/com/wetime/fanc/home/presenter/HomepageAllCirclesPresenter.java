package com.wetime.fanc.home.presenter;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomepageAllCirclesBean;
import com.wetime.fanc.home.iviews.IHomePageAllCirclesView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by yuxun on 2018/5/9.
 */

public class HomepageAllCirclesPresenter {
    private IHomePageAllCirclesView iView;

    public HomepageAllCirclesPresenter(IHomePageAllCirclesView iView) {
        this.iView = iView;
    }

    public void getAllCircle() {
        OkHttpUtils
                .post()
                .url(Const.HOMEPAGE_ALL_CIRCLE)
                .addHeader("token",iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomepageAllCirclesBean bean = GsonUtils.getGsonInstance().fromJson(s, HomepageAllCirclesBean.class);
                        if (bean.getError() == 0)
                            iView.onGetAllCircle(bean);
                    }
                });
    }
}
