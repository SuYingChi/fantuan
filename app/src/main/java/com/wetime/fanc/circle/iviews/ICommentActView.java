package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface ICommentActView extends IBaseVIew {
    void onCommentAct(BaseBean bean);

    String getDyId();

    String getToUid();

    String getContent();

}
