package com.wetime.fanc.home.iviews;


import com.wetime.fanc.home.bean.NewsListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetNewsTypeView extends IBaseVIew {
    void onGetNews(NewsListBean bean);
    String getCid();
    String getPage();
    String getTotal();

}
