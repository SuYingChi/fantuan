package com.wetime.fanc.about.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.wetime.fanc.about.bean.VersionPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetVersionPageView extends IBaseVIew {


    void onVersionResult(VersionPageBean bean);

}
