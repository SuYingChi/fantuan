package com.wetime.fanc.shopcenter.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
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

//        {
//            "lng":"110.31803",
//                "lat":"20.02833",
//                "search_word":"国佳跆拳道",
//                "mall_id":24,
//                "floor_id":"",
//                "cid":"",
//                "pn":"1"
//        }

        OkHttpUtils
                .post()
                .url(Const.MALL_LIST)
                .addParams("mall_id", iView.getMailId())
                .addParams("floor_id", iView.getFloaId())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .addParams("sort", iView.getSortMethod())
                .addParams("cid", iView.getCId())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView,false) {
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
