package com.wetime.fanc.home.iviews;

import com.wetime.fanc.home.bean.HeadRecommendBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by admin on 2018/5/6.
 */

public interface IRecommendView extends IBaseVIew {
    void onGetRecommend(HeadRecommendBean headRecommendBean);
    int getPage();

}
