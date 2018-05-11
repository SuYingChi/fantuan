package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.CircleHeadBean;
import com.wetime.fanc.circle.iviews.IGetCircleHeadView;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetCircleHeadPresenter {
    private IGetCircleHeadView iView;

    public GetCircleHeadPresenter(IGetCircleHeadView iView) {
        this.iView = iView;
    }

    public void getDefaultCircle() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_LIST_CIRCLE)
                .addParams("token", iView.getToken())
                .addParams("circle_id", iView.getCircleId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CircleHeadBean bean = GsonUtils.getGsonInstance().fromJson(s, CircleHeadBean.class);
                        iView.onGetCircleHead(bean);
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
                        if (bean.getError() == 0) iView.onSetCircleAttention(bean);
                    }
                });
    }


}
