package com.wetime.fanc.shopcenter.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.shopcenter.bean.OrderPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetOrderListView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetOrderPagePresenter {
    private IGetOrderListView iView;

    public GetOrderPagePresenter(IGetOrderListView iView) {
        this.iView = iView;
    }

    public void getOrderList() {


        OkHttpUtils
                .post()
                .url(Const.ORDER_LIST)
                .addParams("token", iView.getToken())
                .addParams("type", iView.getType())
                .addParams("filter", iView.getFilter())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        OrderPageBean bean = GsonUtils.getGsonInstance().fromJson(s, OrderPageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetOrderPage(bean);
                    }
                });
    }


}
