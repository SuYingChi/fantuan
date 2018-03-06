package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.CircleHeadBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCircleHeadView extends IBaseVIew {
    void onGetCircleHead(CircleHeadBean bean);
    String getCircleId();

}
