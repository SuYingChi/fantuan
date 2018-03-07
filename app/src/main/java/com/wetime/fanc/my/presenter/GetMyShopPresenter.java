package com.wetime.fanc.my.presenter;


import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.bean.MyShopListBean;
import com.wetime.fanc.my.iviews.IGetMyNewsView;
import com.wetime.fanc.my.iviews.IGetMyShopView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetMyShopPresenter {
    private IGetMyShopView iView;

    public GetMyShopPresenter(IGetMyShopView iView) {
        this.iView = iView;
    }

    public void getMyShop() {
        OkHttpUtils
                .post()
                .url(Const.USER_FOCUS_LIST)
                .addParams("token", iView.getToken())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        MyShopListBean bean = GsonUtils.getGsonInstance().fromJson(s, MyShopListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetMyShop(bean);
                    }
                });
    }


}
