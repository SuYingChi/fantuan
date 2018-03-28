package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.LocStrBean;
import com.wetime.fanc.circle.iviews.IGetLocStrView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetLocStrPresenter {
    private IGetLocStrView iView;

    public GetLocStrPresenter(IGetLocStrView iView) {
        this.iView = iView;
    }

    public void getLocStr() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_ADDRESS)
                .addParams("token", iView.getToken())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        LocStrBean bean = GsonUtils.getGsonInstance().fromJson(s, LocStrBean.class);
                        if (bean.getError() == 0)
                            iView.onGetLocStr(bean);
                    }
                });
    }


}
