package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.MyInfoBean;
import com.wetime.fanc.my.bean.MyProfileBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyprofileView extends IBaseVIew {
    void onGetMyProfile(MyProfileBean bean);


}
