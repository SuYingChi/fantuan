package com.wetime.fanc.home.iviews;


import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopNewsTypeView extends IBaseVIew {
    void onGetNews(NewsListBean bean);
    String getMid();
    String getPage();

}
