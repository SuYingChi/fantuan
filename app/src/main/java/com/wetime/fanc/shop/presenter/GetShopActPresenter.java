package com.wetime.fanc.shop.presenter;


import com.wetime.fanc.shop.bean.ShopActListBean;
import com.wetime.fanc.shop.iviews.IGetShopActTypeView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopActPresenter {
    private IGetShopActTypeView iView;

    public GetShopActPresenter(IGetShopActTypeView iView) {
        this.iView = iView;
    }

    public void getShopAct() {
        OkHttpUtils
                .post()
                .url(Const.SHOP_POST_LIST)
                .addParams("pn", iView.getPage())
                .addParams("mid", iView.getMid())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ShopActListBean bean = GsonUtils.getGsonInstance().fromJson(s, ShopActListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetACt(bean);
                    }
                });
    }


}
