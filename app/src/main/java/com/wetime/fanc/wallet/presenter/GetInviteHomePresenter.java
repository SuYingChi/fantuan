package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.wallet.bean.InviteHomeBean;
import com.wetime.fanc.wallet.iviews.IGetInviteHomeView;
import com.wetime.fanc.wallet.iviews.ISetPayPwdView;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetInviteHomePresenter {
    private IGetInviteHomeView iView;

    public GetInviteHomePresenter(IGetInviteHomeView iView) {
        this.iView = iView;
    }

    public void getInviteHome() {
        OkHttpUtils
                .post()
                .url(Const.USER_INVITER)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        InviteHomeBean bean = GsonUtils.getGsonInstance().fromJson(s, InviteHomeBean.class);
                        if (bean.getError() == 0)
                            iView.onGetInvite(bean);
                    }
                });
    }


}
