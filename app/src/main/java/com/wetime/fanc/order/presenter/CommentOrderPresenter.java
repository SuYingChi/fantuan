package com.wetime.fanc.order.presenter;


import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.king.batterytest.fbaselib.utils.DataStringCallback;
import com.wetime.fanc.order.iviews.ICommentOrderView;
import com.wetime.fanc.utils.Const;
import com.zhy.http.okhttp.OkHttpUtils;

import static com.king.batterytest.fbaselib.utils.GsonUtils.getGsonInstance;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class CommentOrderPresenter {
    private ICommentOrderView iview;

    public CommentOrderPresenter(ICommentOrderView iview) {
        this.iview = iview;
    }

    public void getCommentResult(String ids) {

        OkHttpUtils
                .post()
                .url(Const.ORDER_REVIEW)
                .addParams("token", iview.getToken())
                .addParams("order_id", iview.getOrderId())
                .addParams("score", iview.getScore())
                .addParams("is_anonymous", iview.getAnonymous())
                .addParams("content", iview.getContent())
                .addParams("images", ids)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean msg = getGsonInstance().fromJson(s, BaseBean.class);
                        if (msg.getError() == 0)
                            iview.onCommentResult(msg);
                    }
                });
    }
}
