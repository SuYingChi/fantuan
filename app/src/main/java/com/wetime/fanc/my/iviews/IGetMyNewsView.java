package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.MyNewsListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyNewsView extends IBaseVIew {
    void onGetMyNews(MyNewsListBean bean);

    String getPage();


}
