package com.wetime.fanc.my.presenter;


import com.wetime.fanc.my.bean.RebPackageBean;
import com.wetime.fanc.my.iviews.IGetRedPackageView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetRedPackagePresenter {
    private IGetRedPackageView iView;

    public GetRedPackagePresenter(IGetRedPackageView iView) {
        this.iView = iView;
    }

    public void getRedpackage() {
        OkHttpUtils
                .post()
                .url(Const.user_inviter_redbag)
                .addParams("token", iView.getToken())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        RebPackageBean bean = GsonUtils.getGsonInstance().fromJson(s, RebPackageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetRedPackage(bean);
                    }
                });
    }


}
