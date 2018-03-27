package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.PublishResultBean;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IPublishCircleView extends IBaseVIew {
    void onPublisResultCircle(PublishResultBean bean);

    String getCircleId();

    String getContent();


    String getCJd();

    String getCWd();

    String getLoc();
}
