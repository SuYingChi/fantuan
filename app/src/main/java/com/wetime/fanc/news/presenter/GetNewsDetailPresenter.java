package com.wetime.fanc.news.presenter;


import android.util.Log;

import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.my.bean.AttentionBean;
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
                .addParams("article_id", "7241")
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
        Log.e("xi", "attentionFriends: "+follow );
        OkHttpUtils.post().url(Const.ATTENTION_FRIENDS)
                .addParams("token", "YlUhGvfphqft7DmirjYxbYVu_gqLuHLc")
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


}
