package com.wetime.fanc.shop.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.shop.iviews.IGetShopYouhuiquanView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopYouhuiquanPresenter {
    private IGetShopYouhuiquanView iView;

    public GetShopYouhuiquanPresenter(IGetShopYouhuiquanView iView) {
        this.iView = iView;
    }

    public void getShopYouhuiquan(String pid) {
        OkHttpUtils
                .post()
                .url(Const.SHOP_COUPON_GET)
                .addParams("token", iView.getToken())
                .addParams("mid", iView.getMId())
                .addParams("pid", pid)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onGetShopYouhuiquan(bean);
                    }
                });
    }


}
