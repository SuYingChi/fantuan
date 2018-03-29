package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.FocusListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyFocusView extends IBaseVIew {
    void onMyFocus(FocusListBean bean);

    int getPage();
}
