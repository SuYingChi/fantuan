package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.news.bean.ReplyCommentBean;
import com.wetime.fanc.news.iviews.IGetCommentReplyView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetCommentReplyPresenter {
    private IGetCommentReplyView iview;

    public GetCommentReplyPresenter(IGetCommentReplyView iview) {
        this.iview = iview;
    }

    public void getCommentReply(String comment_id) {

        OkHttpUtils.post().url(Const.GET_COMMENT_REPLY)
                .addParams("token", iview.getToken())
                .addParams("comment_id", comment_id)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ReplyCommentBean replyCommentBean = GsonUtils.getGsonInstance().fromJson(s, ReplyCommentBean.class);
                        iview.onGetCommentReply(replyCommentBean);
                    }
                });
    }

    public void sendCommentReply(String pid, String comment_id, String content) {

        OkHttpUtils.post().url(Const.SEND_COMMENT_REPLY)
                .addParams("token", iview.getToken())
                .addParams("pid", pid)
                .addParams("comment_id", comment_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean errorBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        iview.onSendCommentReply(String.valueOf(errorBean.getError()));
                    }
                });
    }

    public void clickLike(String reply_id, String like) {
        OkHttpUtils.post().url(Const.REPLY_CLICK_LIKE)
                .addParams("token", iview.getToken())
                .addParams("reply_id", reply_id)
                .addParams("like", like)
                .build()
                .execute(new DataStringCallback(iview, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
//                        Log.e("xi", "onResponse: " + s);
                    }
                });
    }

    public void deleteReply(String reply_id) {
        OkHttpUtils.post().url(Const.DELETE_COMMENT_REPLY)
                .addParams("token", iview.getToken())
                .addParams("reply_id", reply_id)
                .build()
                .execute(new DataStringCallback(iview, true, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        iview.onDeleteReply(galleryItemBean);
                    }
                });
    }


}
