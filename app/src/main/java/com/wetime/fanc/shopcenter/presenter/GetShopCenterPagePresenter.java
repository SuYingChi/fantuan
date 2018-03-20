package com.wetime.fanc.shopcenter.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterPageView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopCenterPagePresenter {
    private IGetShopCenterPageView iView;

    public GetShopCenterPagePresenter(IGetShopCenterPageView iView) {
        this.iView = iView;
    }

    public void getShopCenterPage() {
        OkHttpUtils
                .post()
                .url(Const.MALL)
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .build()
                .execute(new DataStringCallback(iView) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ShopCenterPageBean bean = GsonUtils.getGsonInstance().fromJson(s, ShopCenterPageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetShopCenterPageBean(bean);
                    }
                });
    }


}
