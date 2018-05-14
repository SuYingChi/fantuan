package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.circle.bean.ReplyCommBean;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.ErrorBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetLongDetailView extends IBaseVIew {
    void onGetLongDetail(LongBean bean);

    void onGeClickNumber(ClickNumBean bean);

    void onGeClickLike(ErrorBean bean, boolean like);

    void onGetReply(ReplyCommBean bean, int position);

    int getPage();

    String getId();
}
