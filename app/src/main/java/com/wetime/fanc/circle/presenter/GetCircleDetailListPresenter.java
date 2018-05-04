package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.CircleDetailListBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailListView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetCircleDetailListPresenter {
    private IGetCircleDetailListView iView;

    public GetCircleDetailListPresenter(IGetCircleDetailListView iView) {
        this.iView = iView;
    }

    public void getCircleDetailList() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_LIST)
                .addParams("cid", iView.getCircleId())
                .addParams("limit", "20")
                .addHeader("token", iView.getToken())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CircleDetailListBean bean = GsonUtils.getGsonInstance().fromJson(s, CircleDetailListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetCircleList(bean);
                    }
                });
    }


}
