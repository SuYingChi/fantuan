package com.wetime.fanc.setting.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.wetime.fanc.setting.bean.SettingPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetSettingView extends IBaseVIew {
    void onGetSetting(SettingPageBean bean);


}
