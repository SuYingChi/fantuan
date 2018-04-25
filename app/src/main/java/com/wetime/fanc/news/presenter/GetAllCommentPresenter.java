package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.iviews.IGetAllCommentView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetAllCommentPresenter {
    private IGetAllCommentView iview;

    public GetAllCommentPresenter(IGetAllCommentView iview) {
        this.iview = iview;
    }

    public void getAllComment(String comment_id, String pn) {

        OkHttpUtils.post().url(Const.GRT_ALL_COMMENT)
                .addParams("token", iview.getToken())
                .addParams("article_id", comment_id)
                .addParams("pn", pn)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        CommentBean commentBean = GsonUtils.getGsonInstance().fromJson(s, CommentBean.class);
                        iview.onGetAllComment(commentBean);
                    }
                });
    }

    public void clickLike(String comment_id, String like) {
        OkHttpUtils.post().url(Const.CLICK_LIKE)
                .addParams("token", iview.getToken())
                .addParams("comment_id", comment_id)
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

    public void sendCommonet(String article_id, String content) {
        OkHttpUtils.post().url(Const.SEND_COMMONET)
                .addParams("token", iview.getToken())
                .addParams("article_id", article_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iview, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        GalleryCommentBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, GalleryCommentBean.class);
                        iview.onSendCommont(galleryItemBean);
                    }
                });
    }
    public void deleteCommonet(String comment_id) {
        OkHttpUtils.post().url(Const.DELETE_COMMENT)
                .addParams("token", iview.getToken())
                .addParams("comment_id", comment_id)
                .build()
                .execute(new DataStringCallback(iview, true, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        iview.onDeleteCommont(galleryItemBean);
                    }
                });
    }

}
