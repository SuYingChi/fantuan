package com.wetime.fanc.shop.presenter;


import com.wetime.fanc.shop.bean.ShopDetailBean;
import com.wetime.fanc.shop.iviews.IGetShopDetailView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopDetailPresenter {
    private IGetShopDetailView iView;

    public GetShopDetailPresenter(IGetShopDetailView iView) {
        this.iView = iView;
    }

    public void getShopDetail() {
        OkHttpUtils
                .post()
                .url(Const.SHOP)
                .addParams("token", iView.getToken())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .addParams("mid", iView.getMId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ShopDetailBean bean = GsonUtils.getGsonInstance().fromJson(s, ShopDetailBean.class);
                        if (bean.getError() == 0)
                            iView.onGetShopDetail(bean);
                    }
                });
    }


}
