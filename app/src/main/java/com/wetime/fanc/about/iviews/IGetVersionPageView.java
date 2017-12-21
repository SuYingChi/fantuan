package com.wetime.fanc.about.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.about.bean.VersionPageBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetVersionPageView extends IBaseVIew {


    void onVersionResult(VersionPageBean bean);

}
