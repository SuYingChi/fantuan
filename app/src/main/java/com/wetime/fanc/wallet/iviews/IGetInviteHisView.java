package com.wetime.fanc.wallet.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.wallet.bean.BalanceDetailListBean;
import com.wetime.fanc.wallet.bean.InviteHisBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetInviteHisView extends IBaseVIew {

    String getPage();

    void onGetList(InviteHisBean bean);

}
