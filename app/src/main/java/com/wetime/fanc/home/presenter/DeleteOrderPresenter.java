package com.wetime.fanc.home.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.home.iviews.IDeleteOrderView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteOrderPresenter {
    private IDeleteOrderView iView;

    public DeleteOrderPresenter(IDeleteOrderView iView) {
        this.iView = iView;
    }

    public void deleteOrder(String id) {
        OkHttpUtils
                .post()
                .url(Const.ORDER_DELETE)
                .addParams("token", iView.getToken())
                .addParams("order_id", id)
                .build()
                .execute(new DataStringCallback(iView,false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if(bean.getError()==0)
                            iView.onDeleteOrder(bean);
                    }
                });
    }


}
