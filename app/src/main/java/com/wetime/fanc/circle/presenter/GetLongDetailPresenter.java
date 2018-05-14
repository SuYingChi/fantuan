package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.circle.bean.ReplyCommBean;
import com.wetime.fanc.circle.iviews.IGetLongDetailView;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetLongDetailPresenter {
    private IGetLongDetailView iView;

    public GetLongDetailPresenter(IGetLongDetailView iView) {
        this.iView = iView;
    }

    public void getLongDetail() {
        OkHttpUtils
                .post()
                .url(Const.LONG_DETAIL)
                .addParams("pn", iView.getPage() + "")
                .addParams("id", iView.getId())
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        LongBean bean = GsonUtils.getGsonInstance().fromJson(s, LongBean.class);
                        iView.onGetLongDetail(bean);
                    }
                });
    }

    public void getClickNub() {// 评论需要滚动到评论
        OkHttpUtils
                .post()
                .url(Const.CLICK_NUMBER)
                .addParams("pn", iView.getPage() + "")
                .addParams("limit", "10")
                .addParams("id", iView.getId())
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ClickNumBean bean = GsonUtils.getGsonInstance().fromJson(s, ClickNumBean.class);
                        iView.onGeClickNumber(bean);
                    }
                });
    }

    public void getClickLike(String type, boolean like) {// 评论需要滚动到评论
        OkHttpUtils
                .post()
                .url(Const.LIKE_ATTENTION)
                .addParams("type", type)
                .addParams("like", String.valueOf(like))
                .addParams("id", iView.getId())
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false, true, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean errorBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        iView.onGeClickLike(errorBean, like);
                    }
                });
    }

    public synchronized void  getCommReply(String commentId, String pn, String limit, int position) {// 评论需要滚动到评论
        OkHttpUtils
                .post()
                .url(Const.GET_REPLY)
                .addParams("commentId", commentId)
                .addParams("pn", pn)
                .addParams("limit", limit)
                .addHeader("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView, false, true, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ReplyCommBean errorBean = GsonUtils.getGsonInstance().fromJson(s, ReplyCommBean.class);
                        iView.onGetReply(errorBean,position);
                    }
                });
    }


}
