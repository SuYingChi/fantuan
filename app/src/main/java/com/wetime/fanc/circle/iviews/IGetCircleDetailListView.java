package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.CircleDetailListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCircleDetailListView extends IBaseVIew {
    void onGetCircleList(CircleDetailListBean bean);

    int getPage();

    String getType();

    String getCircleId();
}
