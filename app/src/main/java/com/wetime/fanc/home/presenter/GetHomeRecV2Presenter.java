package com.wetime.fanc.home.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.home.bean.HomeListBeanV2;
import com.wetime.fanc.home.bean.HomeRecListBeanV2;
import com.wetime.fanc.home.iviews.IGetHomeFocusV2View;
import com.wetime.fanc.home.iviews.IGetHomeRecV2View;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomeRecV2Presenter {
    private IGetHomeRecV2View iView;

    public GetHomeRecV2Presenter(IGetHomeRecV2View iView) {
        this.iView = iView;
    }

    public void getHomeRecList() {
        OkHttpUtils
                .post()
                .url(Const.QZ_DYNAMICS)
                .addHeader("token", iView.getToken())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeRecListBeanV2 bean = GsonUtils.getGsonInstance().fromJson(s, HomeRecListBeanV2.class);
                        if (bean.getError() == 0) {
                            iView.onGetHomeRec(bean);
                        }

                    }
                });
    }


}
