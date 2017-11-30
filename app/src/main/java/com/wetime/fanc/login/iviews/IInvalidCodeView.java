package com.wetime.fanc.login.iviews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.king.batterytest.fbaselib.main.model.BaseBean;
import com.wetime.fanc.login.bean.LoginResultBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IInvalidCodeView extends IBaseVIew {
    void onInvalidResult(LoginResultBean bean);
}
