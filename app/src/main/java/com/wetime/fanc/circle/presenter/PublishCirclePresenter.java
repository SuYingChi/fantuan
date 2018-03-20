package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.DefaultCircleBean;
import com.wetime.fanc.circle.bean.PublishResultBean;
import com.wetime.fanc.circle.iviews.IGetDefaultCircleView;
import com.wetime.fanc.circle.iviews.IPublishCircleView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class PublishCirclePresenter {
    private IPublishCircleView iView;

    public PublishCirclePresenter(IPublishCircleView iView) {
        this.iView = iView;
    }

    public void publishCircle(String ids) {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_PUBLISH_SAVE)
                .addParams("token", iView.getToken())
                .addParams("circle_id", iView.getCircleId())
                .addParams("content", iView.getContent())
                .addParams("image_ids", ids)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        PublishResultBean bean = GsonUtils.getGsonInstance().fromJson(s, PublishResultBean.class);
                        if (bean.getError() == 0)
                            iView.onPublisResultCircle(bean);
                    }
                });
    }


}
