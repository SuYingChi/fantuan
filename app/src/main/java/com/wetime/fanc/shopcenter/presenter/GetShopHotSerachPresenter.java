package com.wetime.fanc.shopcenter.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopHotSearchView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopHotSerachPresenter {
    private IGetShopHotSearchView iView;

    public GetShopHotSerachPresenter(IGetShopHotSearchView iView) {
        this.iView = iView;
    }

    public void getHotSearchPage() {
        OkHttpUtils
                .post()
                .url(Const.MALL_SEARCH)
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .addParams("mall_id", iView.getMailId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeHotSearchBean bean = GsonUtils.getGsonInstance().fromJson(s, HomeHotSearchBean.class);
                        if (bean.getError() == 0)
                            iView.onGetHotSearchPage(bean);
                    }
                });
    }


}
