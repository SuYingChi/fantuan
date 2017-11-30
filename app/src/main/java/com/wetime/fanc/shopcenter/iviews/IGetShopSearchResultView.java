package com.wetime.fanc.shopcenter.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopSearchResultView extends IBaseVIew {


//    sort 排列方式： 0=智能排序，1=距离优先，2=人均
//            cid
//    floor_id
//            pn
    String getJd();

    String getWd();
    String getMailId();
    String getFloaId();
    String getPage();
    String getSortMethod();
    String getCId();
    String getWord();




    void  onGetShopCenterListBean(CenterListPageBean bean);

}
