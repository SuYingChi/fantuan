package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.iviews.IGetActDetailView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetActDetailPresenter {
    private IGetActDetailView iView;

    public GetActDetailPresenter(IGetActDetailView iView) {
        this.iView = iView;
    }

    public void getActDetail() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_DETAIL)
                .addParams("pn", iView.getPage() + "")
                .addParams("id", iView.getId())
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ActDetailBean bean = GsonUtils.getGsonInstance().fromJson(s, ActDetailBean.class);
                        if (bean.getError() == 0)
                            iView.onGetActDetail(bean);
                    }
                });
    }


}
