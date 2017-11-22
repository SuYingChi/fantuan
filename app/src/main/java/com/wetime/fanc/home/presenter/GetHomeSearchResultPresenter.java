package com.wetime.fanc.home.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.home.bean.HomeSearchResult;
import com.wetime.fanc.home.iviews.IGetHomeSearchResultView;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterListView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetHomeSearchResultPresenter {
    private IGetHomeSearchResultView iView;

    public GetHomeSearchResultPresenter(IGetHomeSearchResultView iView) {
        this.iView = iView;
    }

    public void getShopCenterList() {
//        "search_word":"澳门",
//                "lng":"110.31803",
//                "lat":"20.02833",
//                "sort":"0",
//                "cate_id":"",
//                "mall_id":"",
//                "pn":"1"

        OkHttpUtils
                .post()
                .url(Const.SEARCH_RESULT)
                .addParams("search_word", iView.getWord())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .addParams("sort", iView.getSortMethod())
                .addParams("cate_id", iView.getCId())
                .addParams("mall_id", iView.getMailId())
                .addParams("pn", iView.getPage())
                .build()
                .execute(new DataStringCallback(iView,false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        HomeSearchResult bean = GsonUtils.getGsonInstance().fromJson(s, HomeSearchResult.class);
                        if (bean.getError() == 0)
                            iView.onGetHomeSearchResultBean(bean);
                    }
                });
    }


}
