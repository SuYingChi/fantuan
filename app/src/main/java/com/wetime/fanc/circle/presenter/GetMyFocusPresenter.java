package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.bean.FocusListBean;
import com.wetime.fanc.circle.iviews.IGetCircleHomeView;
import com.wetime.fanc.circle.iviews.IGetMyFocusView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetMyFocusPresenter {
    private IGetMyFocusView iView;

    public GetMyFocusPresenter(IGetMyFocusView iView) {
        this.iView = iView;
    }

    public void getMyFocus() {
        OkHttpUtils
                .post()
                .url(Const.CIRCLE_FOLLOW)
                .addParams("token", iView.getToken())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        FocusListBean bean = GsonUtils.getGsonInstance().fromJson(s, FocusListBean.class);
                        if (bean.getError() == 0)
                            iView.onMyFocus(bean);
                    }
                });
    }


}
