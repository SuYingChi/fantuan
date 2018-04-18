package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.CircleDetailBean;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.ErrorBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCircleDetailView extends IBaseVIew {
    void onGetCircleDetail(CircleDetailBean bean);
    void onSetCircleAttention(ErrorBean bean);

    String getCircleId();
}
