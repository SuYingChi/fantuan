package com.wetime.fanc.shop.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.shop.iviews.IFocusShopView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class UnFocusShopPresenter {
    private IFocusShopView iView;

    public UnFocusShopPresenter(IFocusShopView iView) {
        this.iView = iView;
    }

    public void unfocusShop() {
        OkHttpUtils
                .post()
                .url(Const.SHOP_FOCUS_CANCEL)
                .addParams("token", iView.getToken())
                .addParams("mid", iView.getMId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onFocusShop(bean);
                    }
                });
    }


}
