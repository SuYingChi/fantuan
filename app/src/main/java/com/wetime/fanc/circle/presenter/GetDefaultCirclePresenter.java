package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.DefaultCircleBean;
import com.wetime.fanc.circle.iviews.IGetDefaultCircleView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetDefaultCirclePresenter {
    private IGetDefaultCircleView iView;

    public GetDefaultCirclePresenter(IGetDefaultCircleView iView) {
        this.iView = iView;
    }

    public void getDefaultCircle() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_PUBLISH_INITFORM)
                .addParams("circle_id", iView.getCircleId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        DefaultCircleBean bean = GsonUtils.getGsonInstance().fromJson(s, DefaultCircleBean.class);
                        if (bean.getError() == 0)
                            iView.onGetDefaultCircle(bean);
                    }
                });
    }


}
