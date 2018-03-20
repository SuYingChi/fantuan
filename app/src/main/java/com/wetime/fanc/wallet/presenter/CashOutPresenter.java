package com.wetime.fanc.wallet.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.wallet.iviews.ICashOutView;
import com.wetime.fanc.wallet.iviews.ISetPayPwdView;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class CashOutPresenter {
    private ICashOutView iView;

    public CashOutPresenter(ICashOutView iView) {
        this.iView = iView;
    }

    public void cashOut(String pwd) {
        OkHttpUtils
                .post()
                .url(Const.WALLET_PASSWORD_SET)
                .addParams("token", iView.getToken())
                .addParams("pwd", pwd)
                .build()
                .execute(new DataStringCallback(iView, true,true
                        ,true,true,false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onCashOutResult(bean);
                    }
                });
    }


}
