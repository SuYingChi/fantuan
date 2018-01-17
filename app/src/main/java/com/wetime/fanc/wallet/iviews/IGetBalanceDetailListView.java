package com.wetime.fanc.wallet.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetBalanceDetailListView extends IBaseVIew {

    String getPage();

    void onGetList(BalanceDetailListBean bean);

}
