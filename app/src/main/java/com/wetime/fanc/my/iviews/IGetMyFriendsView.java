package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.my.bean.MyFriendsBaseBean;
import com.wetime.fanc.my.bean.MyInfoBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyFriendsView extends IBaseVIew {

    void onGetUserFriend(MyFriendsBaseBean bean);
    void onAttention(AttentionBean bean);

    String onGetType();




}
