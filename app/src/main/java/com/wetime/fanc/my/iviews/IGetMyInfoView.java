package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.MyInfoBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyInfoView extends IBaseVIew {
    void onGetUserInfo(MyInfoBean bean);


}
