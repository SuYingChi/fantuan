package com.wetime.fanc.login.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.login.bean.LoginResultBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface ILoginView extends IBaseVIew {
    void onLoginResult(LoginResultBean bean);
    String getPhone();
    String getPSW();
}
