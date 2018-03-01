package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.iviews.IGetCircleHomeView;
import com.wetime.fanc.my.bean.MyInfoBean;
import com.wetime.fanc.my.iviews.IGetMyInfoView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetCircleHomePresenter {
    private IGetCircleHomeView iView;

    public GetCircleHomePresenter(IGetCircleHomeView iView) {
        this.iView = iView;
    }

    public void getCircleHome() {
        OkHttpUtils
                .post()
                .url(Const.CIRCLE)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CircleHomeListBean bean = GsonUtils.getGsonInstance().fromJson(s, CircleHomeListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetCircleHome(bean);
                    }
                });
    }


}
