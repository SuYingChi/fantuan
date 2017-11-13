package com.wetime.fanc.home.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.home.iviews.IGetHomeHotSearchView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomeHotSerachPresenter {
    private IGetHomeHotSearchView iView;

    public GetHomeHotSerachPresenter(IGetHomeHotSearchView iView) {
        this.iView = iView;
    }

    public void getHotSearchPage() {
        OkHttpUtils
                .post()
                .url(Const.HOME_SEARCH)
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeHotSearchBean bean = GsonUtils.getGsonInstance().fromJson(s, HomeHotSearchBean.class);
                        if (bean.getError() == 0)
                            iView.onGetHotSearchPage(bean);
                    }
                });
    }


}
