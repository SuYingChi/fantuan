package com.wetime.fanc.setting.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/15.
 */

public interface ISetUserInfoView extends IBaseVIew {

//    String getPushToken();
    void onSetUserInfoResult(BaseBean bean);

}
