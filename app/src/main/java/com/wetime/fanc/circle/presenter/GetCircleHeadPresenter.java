package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.CircleHeadBean;
import com.wetime.fanc.circle.iviews.IGetCircleHeadView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

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
                .addParams("circle_id", iView.getCircleId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CircleHeadBean bean = GsonUtils.getGsonInstance().fromJson(s, CircleHeadBean.class);
                        if (bean.getError() == 0)
                            iView.onGetCircleHead(bean);
                    }
                });
    }


}
