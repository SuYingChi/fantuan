package com.wetime.fanc.news.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.bean.GalleryItemBean;
import com.wetime.fanc.news.iviews.IGetNewsDetailView;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetNewsDetailPresenter {
    private IGetNewsDetailView iView;

    public GetNewsDetailPresenter(IGetNewsDetailView iView) {
        this.iView = iView;
    }

    public void getNewsDetail(String galleryId) {
        OkHttpUtils.post().url(Const.GET_NEWS_DETAIL)
                .addParams("token", iView.getToken())
                .addParams("article_id", galleryId)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        GalleryItemBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, GalleryItemBean.class);
                        iView.onGetNewDetail(galleryItemBean);
                    }
                });
    }

    public void attentionFriends(String follow, String following_id) {
        OkHttpUtils.post().url(Const.ATTENTION_FRIENDS)
                .addParams("token", iView.getToken())
                .addParams("follow", follow)
                .addParams("following_id", following_id)
                .build()
                .execute(new DataStringCallback(iView, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        AttentionBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, AttentionBean.class);
                        iView.onAttentionFriends(galleryItemBean);
                    }
                });
    }

    public void sendCommonet(String article_id, String content) {
        OkHttpUtils.post().url(Const.SEND_COMMONET)
                .addParams("token", iView.getToken())
                .addParams("article_id", article_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iView, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        GalleryCommentBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, GalleryCommentBean.class);
                        iView.onSendCommont(galleryItemBean);
                    }
                });
    }

    public void collectNews(String article_id, String collect) {
        OkHttpUtils.post().url(Const.COLLECT_NEWS)
                .addParams("token", iView.getToken())
                .addParams("article_id", article_id)
                .addParams("collect", collect)
                .build()
                .execute(new DataStringCallback(iView, false, false, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        ErrorBean errorBean = GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);
                        if (errorBean.getError()==0) iView.onCollectNews();
                    }
                });
    }


}
