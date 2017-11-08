package com.wetime.fanc.shopcenter.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterListView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopCenterListPresenter {
    private IGetShopCenterListView iView;

    public GetShopCenterListPresenter(IGetShopCenterListView iView) {
        this.iView = iView;
    }

    public void getShopCenterList() {
        OkHttpUtils
                .post()
                .url(Const.MALL_LIST)
                .addParams("mall_id", iView.getMailId())
                .addParams("floor_id", iView.getFloaId())
                .build()
                .execute(new DataStringCallback(iView) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CenterListPageBean bean = GsonUtils.getGsonInstance().fromJson(s, CenterListPageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetShopCenterListBean(bean);
                    }
                });
    }


}
