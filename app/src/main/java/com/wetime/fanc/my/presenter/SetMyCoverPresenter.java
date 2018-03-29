package com.wetime.fanc.my.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.iviews.IGetMyNewsView;
import com.wetime.fanc.my.iviews.ISetMyCoverView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class SetMyCoverPresenter {
    private ISetMyCoverView iView;

    public SetMyCoverPresenter(ISetMyCoverView iView) {
        this.iView = iView;
    }

    public void setMyCover(String id) {
        OkHttpUtils
                .post()
                .url(Const.USER_PROFILE_UPDATECOVER)
                .addParams("token", iView.getToken())
                .addParams("cover", id)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onSetCoverResult(bean);
                    }
                });
    }


}
