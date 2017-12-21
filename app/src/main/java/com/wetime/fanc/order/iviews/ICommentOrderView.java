package com.wetime.fanc.order.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface ICommentOrderView extends IBaseVIew {
//
//    "token":"UkrBfMhbDggL1YA1LPO5Sl1A7pw63waJ",
//            "order_id":"6804",
//            "score":"4",
//            "is_anonymous":"0",
//            "content":"很实惠，很超值，值得推荐！！！",
//            "images":"139544,139548,7586"

    void onCommentResult(BaseBean bean);
    String getOrderId();
    String getScore();
    String getAnonymous();
    String getContent();
}
