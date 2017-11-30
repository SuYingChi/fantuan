package com.wetime.fanc.shopcenter.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.home.bean.OrderPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetOrderListView extends IBaseVIew {
//    {
//        "token":"UkrBfMhbDggL1YA1LPO5Sl1A7pw63waJ",
//            "type":0,
//            "filter":3,
//            "pn":1
//    }
    String getType();
    String getFilter();
    String getPage();
    void  onGetOrderPage(OrderPageBean bean,String type,String filter);//区分 前后顺序

}
