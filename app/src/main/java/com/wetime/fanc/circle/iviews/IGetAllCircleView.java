package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.AllCircleListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetAllCircleView extends IBaseVIew {
    void onGetAllCircle(AllCircleListBean bean);

    int getPage();
}
