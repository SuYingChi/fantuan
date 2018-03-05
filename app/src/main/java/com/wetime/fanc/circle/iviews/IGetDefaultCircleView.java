package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.DefaultCircleBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetDefaultCircleView extends IBaseVIew {
    void onGetDefaultCircle(DefaultCircleBean bean);

    String getCircleId();
}
