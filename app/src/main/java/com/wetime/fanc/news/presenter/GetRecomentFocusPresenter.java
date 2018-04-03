package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.news.bean.FocusTitleList;
import com.wetime.fanc.news.iviews.IGetRecomentFocusView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetRecomentFocusPresenter {
    private IGetRecomentFocusView iview;

    public GetRecomentFocusPresenter(IGetRecomentFocusView iview) {
        this.iview = iview;
    }

    public void getRecomentFocus() {

        OkHttpUtils
                .post()
                .url(Const.NEWS_CATEGORY_LIST)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        FocusTitleList msg = getGsonInstance().fromJson(s, FocusTitleList.class);
                        if (msg.getError() == 0)
                            iview.onGetRecomentTitle(msg);
                    }
                });
    }
}
