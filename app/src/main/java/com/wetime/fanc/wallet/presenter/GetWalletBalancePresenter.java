package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.shop.iviews.IFocusShopView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.wallet.bean.WalletBalanceBean;
import com.wetime.fanc.wallet.iviews.IGetWalletBalanceView;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetWalletBalancePresenter {
    private IGetWalletBalanceView iView;

    public GetWalletBalancePresenter(IGetWalletBalanceView iView) {
        this.iView = iView;
    }

    public void getWalletBalance() {
        OkHttpUtils
                .post()
                .url(Const.WALLET_BALANCE)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        WalletBalanceBean bean = GsonUtils.getGsonInstance().fromJson(s, WalletBalanceBean.class);
                        if (bean.getError() == 0)
                            iView.onGetWalletBalance(bean);
                    }
                });
    }


}
