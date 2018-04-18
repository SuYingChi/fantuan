package com.wetime.fanc.home.iviews;


import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.SpecialTopicBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetNewsTypeView extends IBaseVIew {
    void onGetNews(NewsListBean bean, SpecialTopicBean specialTopicBean);
    String getCid();
    String getPage();
    String getTotal();

}
