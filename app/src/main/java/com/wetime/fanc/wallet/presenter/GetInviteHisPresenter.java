package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;
import com.wetime.fanc.wallet.bean.InviteHisBean;
import com.wetime.fanc.wallet.iviews.IGetBalanceDetailListView;
import com.wetime.fanc.wallet.iviews.IGetInviteHisView;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetInviteHisPresenter {
    private IGetInviteHisView iView;

    public GetInviteHisPresenter(IGetInviteHisView iView) {
        this.iView = iView;
    }

    public void getbalancelist() {
        OkHttpUtils
                .post()
                .url(Const.USER_INVITER_HISTORY)
                .addParams("token", iView.getToken())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        InviteHisBean bean = GsonUtils.getGsonInstance().fromJson(s, InviteHisBean.class);
                        if (bean.getError() == 0)
                            iView.onGetList(bean);
                    }
                });
    }


}
