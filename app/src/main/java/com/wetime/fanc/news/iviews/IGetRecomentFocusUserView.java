package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.FocusTitleList;
import com.wetime.fanc.news.bean.RecomentFocusUserListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetRecomentFocusUserView extends IBaseVIew {

    void onGetRecomentUser(RecomentFocusUserListBean bean);
    int getPage();
    String getId();

}
