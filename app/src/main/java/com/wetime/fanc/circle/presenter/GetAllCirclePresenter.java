package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.AllCircleListBean;
import com.wetime.fanc.circle.iviews.IGetAllCircleView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetAllCirclePresenter {
    private IGetAllCircleView iView;

    public GetAllCirclePresenter(IGetAllCircleView iView) {
        this.iView = iView;
    }

    public void getDefaultCircle() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_PUBLISH_LIST)
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        AllCircleListBean bean = GsonUtils.getGsonInstance().fromJson(s, AllCircleListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetAllCircle(bean);
                    }
                });
    }


}
