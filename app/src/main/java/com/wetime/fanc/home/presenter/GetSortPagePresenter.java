package com.wetime.fanc.home.presenter;


import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.wetime.fanc.home.bean.SortPageBean;
import com.wetime.fanc.home.iviews.IGetSortView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetSortPagePresenter {
    private IGetSortView iView;

    public GetSortPagePresenter(IGetSortView iView) {
        this.iView = iView;
    }

    public void getSortPage() {
        OkHttpUtils
                .post()
                .url(Const.CATEGORY)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        SortPageBean bean = GsonUtils.getGsonInstance().fromJson(s, SortPageBean.class);
                        if (bean.getError() == 0)
                            iView.onGetSortPage(bean);
                    }
                });
    }


}
