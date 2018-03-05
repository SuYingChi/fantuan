package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.CircleDetailBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCircleDetailView extends IBaseVIew {
    void onGetCircleDetail(CircleDetailBean bean);

    String getCircleId();
}
