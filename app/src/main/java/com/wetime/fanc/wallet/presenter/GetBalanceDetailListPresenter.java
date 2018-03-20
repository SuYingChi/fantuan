package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;
import com.wetime.fanc.wallet.iviews.IGetBalanceDetailListView;
import com.wetime.fanc.wallet.iviews.ISetPayPwdView;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetBalanceDetailListPresenter {
    private IGetBalanceDetailListView iView;

    public GetBalanceDetailListPresenter(IGetBalanceDetailListView iView) {
        this.iView = iView;
    }

    public void getbalancelist() {
        OkHttpUtils
                .post()
                .url(Const.WALLET_BALANCE_LIST)
                .addParams("token", iView.getToken())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BalanceDetailListBean bean = GsonUtils.getGsonInstance().fromJson(s, BalanceDetailListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetList(bean);
                    }
                });
    }


}
