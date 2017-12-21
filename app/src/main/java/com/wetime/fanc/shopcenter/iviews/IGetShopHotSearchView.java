package com.wetime.fanc.shopcenter.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.home.bean.HomeHotSearchBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopHotSearchView extends IBaseVIew {
    String getMailId();
    void  onGetHotSearchPage(HomeHotSearchBean bean);

}
