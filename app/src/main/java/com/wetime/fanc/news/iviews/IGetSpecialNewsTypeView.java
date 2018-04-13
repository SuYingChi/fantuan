package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.NewsListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetSpecialNewsTypeView extends IBaseVIew {

    void onGetSpecialNews(NewsListBean bean);

}
