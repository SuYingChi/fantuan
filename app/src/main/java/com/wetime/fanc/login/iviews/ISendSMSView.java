package com.wetime.fanc.login.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface ISendSMSView extends IBaseVIew {
    void onSendResult(BaseBean bean);
}
