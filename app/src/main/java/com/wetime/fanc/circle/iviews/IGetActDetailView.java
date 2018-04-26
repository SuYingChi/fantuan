package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetActDetailView extends IBaseVIew {
    void onGetActDetail(ActDetailBean bean,boolean isComment);
    int  getPage();
    String getId();
}
