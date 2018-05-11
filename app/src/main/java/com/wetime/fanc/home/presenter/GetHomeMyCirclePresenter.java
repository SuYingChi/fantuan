package com.wetime.fanc.home.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomeMyCircleBean;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.home.iviews.IGetHomeMyCircleView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomeMyCirclePresenter {
    private IGetHomeMyCircleView iView;

    public GetHomeMyCirclePresenter(IGetHomeMyCircleView iView) {
        this.iView = iView;
    }

    public void getHomeMyCircle() {
        OkHttpUtils
                .post()
                .url(Const.QZ_MYCIRCLES)
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeMyCircleBean bean = GsonUtils.getGsonInstance().fromJson(s, HomeMyCircleBean.class);
                        if (bean.getError() == 0) {
                            iView.onGetMyCircle(bean);
                        }

                    }
                });
    }


}
