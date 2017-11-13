package com.wetime.fanc.home.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeHotSearchView extends IBaseVIew {

    void  onGetHotSearchPage(HomeHotSearchBean bean);

}
