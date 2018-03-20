package com.wetime.fanc.setting.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.setting.iviews.ISetHeadImageView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class SetHeadImagePresenter {
    private ISetHeadImageView iview;

    public SetHeadImagePresenter(ISetHeadImageView iview) {
        this.iview = iview;
    }

    public void setHeadImage(String id) {

        OkHttpUtils
                .post()
                .url(Const.USER_PROFILE_UPDATEAVATAR)
                .addParams("token", iview.getToken())
                .addParams("image_id", id)
                .build()
                .execute(new DataStringCallback(iview) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean msg = getGsonInstance().fromJson(s, BaseBean.class);
                        if (msg.getError() == 0)
                            iview.onSetHeadImageResult(msg);
                    }
                });
    }
}
