package com.wetime.fanc.home.iviews;


import com.wetime.fanc.home.bean.HomeRecListBeanV2;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeRecV2View extends IBaseVIew {
    void onGetHomeRec(HomeRecListBeanV2 bean);

    int getPage();


}
