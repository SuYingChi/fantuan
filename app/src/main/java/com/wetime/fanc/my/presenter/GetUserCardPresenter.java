package com.wetime.fanc.my.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.my.bean.UserCardBean;
import com.wetime.fanc.my.iviews.IGetUserCardView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetUserCardPresenter {
    private IGetUserCardView iView;

    public GetUserCardPresenter(IGetUserCardView iView) {
        this.iView = iView;
    }

    public void getUserInfo() {
        OkHttpUtils
                .post()
                .url(Const.SOCIAL)
                .addParams("token", iView.getToken())
                .addParams("uid", iView.getUid())
                .addParams("type", iView.getType())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        UserCardBean bean = GsonUtils.getGsonInstance().fromJson(s, UserCardBean.class);
                        if (bean.getError() == 0)
                            iView.onGetUserCard(bean);
                    }
                });
    }

    public void reportComment(String type,String type_id,String content) {
        OkHttpUtils
                .post()
                .url(Const.REPORT)
                .addHeader("token", iView.getToken())
                .addParams("type", type)
                .addParams("type_id", type_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onReportResult(bean);
                    }
                });
    }


}
