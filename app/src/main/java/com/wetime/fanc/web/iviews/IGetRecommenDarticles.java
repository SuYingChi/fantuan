package com.wetime.fanc.web.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.web.bean.NewsWebBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetRecommenDarticles extends IBaseVIew {
    void onRecommenDarticles(NewsWebBean bean);

    void onAttentionFriends(AttentionBean bean);
}
