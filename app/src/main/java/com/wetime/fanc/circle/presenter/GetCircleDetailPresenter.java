package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.CircleDetailBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailView;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetCircleDetailPresenter {
    private IGetCircleDetailView iView;

    public GetCircleDetailPresenter(IGetCircleDetailView iView) {
        this.iView = iView;
    }

    public void getCircleDetail() {
        OkHttpUtils
                .post()
                .url(Const.CIRCLE_INFO)
                .addParams("circle_id", iView.getCircleId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CircleDetailBean bean = GsonUtils.getGsonInstance().fromJson(s, CircleDetailBean.class);
                        if (bean.getError() == 0)
                            iView.onGetCircleDetail(bean);
                    }
                });
    }

    public void setCircleAttention(String id, String follow) {
        OkHttpUtils
                .post()
                .url(Const.CIRCLE_ATTRNTION)
                .addHeader("token", iView.getToken())
                .addParams("id", id)
                .addParams("follow", follow)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean bean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        if (bean.getError() == 0)
                            iView.onSetCircleAttention(bean);
                    }
                });
    }


}
