package com.wetime.fanc.home.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.home.bean.MyInfoBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyInfoView extends IBaseVIew {
    void onGetUserInfo(MyInfoBean bean);


}
