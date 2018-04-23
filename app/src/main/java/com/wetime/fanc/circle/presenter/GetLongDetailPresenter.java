package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.circle.iviews.IGetLongDetailView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetLongDetailPresenter {
    private IGetLongDetailView iView;

    public GetLongDetailPresenter(IGetLongDetailView iView) {
        this.iView = iView;
    }

    public void getLongDetail() {
        OkHttpUtils
                .post()
                .url(Const.LONG_DETAIL)
//                .addParams("pn", iView.getPage() + "")
                .addParams("pn",  "1")
//                .addParams("id", iView.getId())
                .addParams("id", "406")
//                .addHeader("token", iView.getToken())
                .addHeader("token", "lcaKiq5GIC_FHqubOBcI6FUKaL8N171U")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        LongBean bean = GsonUtils.getGsonInstance().fromJson(s, LongBean.class);
                        iView.onGetLongDetail(bean);
                    }
                });
    }


}
