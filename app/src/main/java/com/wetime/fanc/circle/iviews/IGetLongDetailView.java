package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetLongDetailView extends IBaseVIew {
    void onGetLongDetail(LongBean bean);
    int  getPage();
    String getId();
}
