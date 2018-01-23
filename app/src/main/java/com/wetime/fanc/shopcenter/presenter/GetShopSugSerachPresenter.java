package com.wetime.fanc.shopcenter.presenter;


import com.wetime.fanc.home.bean.SearchResult;
import com.wetime.fanc.home.iviews.IGetHomeSugView;
import com.wetime.fanc.shopcenter.iviews.IGetShopSugView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetShopSugSerachPresenter {
    private IGetShopSugView iView;

    public GetShopSugSerachPresenter(IGetShopSugView iView) {
        this.iView = iView;
    }

    public void getSugSearchPage(final String word) {
        OkHttpUtils
                .post()
                .url(Const.MALL_SEARCH_SUGGEST)
                .addParams("lng", iView.getJd())
                .addParams("search_word", word)
                .addParams("lat", iView.getWd())
                .addParams("mall_id", iView.getMailId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        SearchResult bean = GsonUtils.getGsonInstance().fromJson(s, SearchResult.class);
                        if (bean.getError() == 0)
                            iView.onGetHomeSug(bean,word);
                    }
                });
    }


}
