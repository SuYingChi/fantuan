package com.wetime.fanc.shop.iviews;


import com.wetime.fanc.home.bean.NewsListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopNewsView extends IBaseVIew {
    void onGetNews(NewsListBean bean);
    String getMid();
    String getPage();

}
