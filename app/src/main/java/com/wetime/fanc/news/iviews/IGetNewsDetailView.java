package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.news.bean.GalleryItemBean;
import com.wetime.fanc.news.bean.NewsListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetNewsDetailView extends IBaseVIew {

    void onGetNewDetail(GalleryItemBean bean);

    void onAttentionFriends(AttentionBean bean);

}
