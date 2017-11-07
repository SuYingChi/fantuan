package com.wetime.fanc.login.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.login.bean.LoginResultBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IWXLoginView extends IBaseVIew {
    void onLoginResult(LoginResultBean bean);

}
