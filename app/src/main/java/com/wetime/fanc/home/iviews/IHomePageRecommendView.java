package com.wetime.fanc.home.iviews;

import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by admin on 2018/5/6.
 */

public interface IHomePageRecommendView extends IBaseVIew {
    void onGetRecommend(HomePageRecommendBean homePageRecommendBean);
    int getPage();

}
