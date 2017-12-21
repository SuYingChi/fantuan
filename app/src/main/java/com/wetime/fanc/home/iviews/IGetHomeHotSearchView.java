package com.wetime.fanc.home.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.home.bean.HomeHotSearchBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeHotSearchView extends IBaseVIew {

    void  onGetHotSearchPage(HomeHotSearchBean bean);

}
