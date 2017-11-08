package com.wetime.fanc.shopcenter.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopCenterListView extends IBaseVIew {
    String getJd();

    String getWd();
    String getMailId();
    String getFloaId();
    void  onGetShopCenterListBean(CenterListPageBean bean);

}
