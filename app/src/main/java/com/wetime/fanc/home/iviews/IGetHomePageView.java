package com.wetime.fanc.home.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.home.bean.HomePageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomePageView extends IBaseVIew {
    String getPage();

    void onGetHomePage(HomePageBean bean);

    String getSort();


}
