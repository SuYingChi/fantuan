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
//        "lng":"110.3196",
//                "lat":"20.02862",
//                "location":"百方"
        String content = iView.getContent();
        content=content.replace("\n"," ");
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_PUBLISH_SAVE)
                .addParams("token", iView.getToken())
                .addParams("circle_id", iView.getCircleId())
                .addParams("content", content)
                .addParams("image_ids", ids)
                .addParams("lng", iView.getCJd())
                .addParams("lat", iView.getCWd())
                .addParams("location", iView.getLoc())
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
