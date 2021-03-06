package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.news.bean.RecomentFocusUserListBean;
import com.wetime.fanc.news.iviews.IGetRecomentFocusUserView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetRecomentFocusUserPresenter {
    private IGetRecomentFocusUserView iview;

    public GetRecomentFocusUserPresenter(IGetRecomentFocusUserView iview) {
        this.iview = iview;
    }

    public void getRecomentFocusUser() {

        OkHttpUtils
                .post()
                .url(Const.NEWS_CATEGORY_RECOMMEND)
                .addParams("token", iview.getToken())
                .addParams("id", iview.getId())
                .addParams("pn", iview.getPage() + "")
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        RecomentFocusUserListBean msg = getGsonInstance().fromJson(s, RecomentFocusUserListBean.class);
                        if (msg.getError() == 0)
                            iview.onGetRecomentUser(msg);
                    }
                });
    }
}
