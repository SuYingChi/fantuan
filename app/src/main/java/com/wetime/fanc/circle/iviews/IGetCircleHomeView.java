package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetCircleHomeView extends IBaseVIew {
    void onGetCircleHome(CircleHomeListBean bean);
    int  getPage();
    String getSort();
}
