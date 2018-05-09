package com.wetime.fanc.web.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.web.bean.NewsWebBean;
import com.wetime.fanc.web.iviews.IGetRecommenDarticles;


/**
 * Created by zhoukang on 2017/5/15.
 */

public class GetAllRecommenDarticles {
    private IGetRecommenDarticles iview;

    public GetAllRecommenDarticles(IGetRecommenDarticles iview) {
        this.iview = iview;
    }

    public void getRecommenDarticles(String id) {

        OkHttpUtils.post().url(Const.GET_RECOMMEN_DA)
                .addHeader("token", iview.getToken())
                .addParams("id", id)
                .build()
                .execute(new DataStringCallback(iview, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        NewsWebBean commentBean = GsonUtils.getGsonInstance().fromJson(s, NewsWebBean.class);
                        iview.onRecommenDarticles(commentBean);
                    }
                });
    }

    public void attentionFriends(String follow, String following_id) {
        OkHttpUtils.post().url(Const.ATTENTION_FRIENDS)
                .addParams("token", iview.getToken())
                .addParams("follow", follow)
                .addParams("following_id", following_id)
                .build()
                .execute(new DataStringCallback(iview, false, false, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        AttentionBean galleryItemBean = GsonUtils.getGsonInstance().fromJson(s, AttentionBean.class);
                        iview.onAttentionFriends(galleryItemBean);
                    }
                });
    }


}
