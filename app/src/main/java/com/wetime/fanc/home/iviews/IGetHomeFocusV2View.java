package com.wetime.fanc.home.iviews;


import com.wetime.fanc.home.bean.HomeListBeanV2;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeFocusV2View extends IBaseVIew {
    void onGetHomeFocus(HomeListBeanV2 bean);
    int getPage();


}
