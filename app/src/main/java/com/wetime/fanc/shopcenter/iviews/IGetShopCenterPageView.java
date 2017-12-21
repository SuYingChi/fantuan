package com.wetime.fanc.shopcenter.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopCenterPageView extends IBaseVIew {
    String getJd();

    String getWd();
    void  onGetShopCenterPageBean(ShopCenterPageBean bean);

}
