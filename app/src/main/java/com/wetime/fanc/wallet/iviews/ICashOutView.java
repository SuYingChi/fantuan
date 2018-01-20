package com.wetime.fanc.wallet.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface ICashOutView extends IBaseVIew {

    void onCashOutResult(BaseBean bean);
    String getNum();

}
