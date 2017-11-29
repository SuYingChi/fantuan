package com.wetime.fanc.about.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.wetime.fanc.about.bean.VersionPageBean;
import com.wetime.fanc.about.iviews.IGetVersionPageView;
import com.wetime.fanc.order.iviews.ICommentOrderView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetVersionPresenter {
    private IGetVersionPageView iview;

    public GetVersionPresenter(IGetVersionPageView iview) {
        this.iview = iview;
    }

    public void getVersionResult() {

        OkHttpUtils
                .post()
                .url(Const.VERSION)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        VersionPageBean msg = getGsonInstance().fromJson(s, VersionPageBean.class);
                        if (msg.getError() == 0)
                            iview.onVersionResult(msg);
                    }
                });
    }
}
