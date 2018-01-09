package com.wetime.fanc.wallet.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.wallet.bean.WalletBalanceBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetWalletBalanceView extends IBaseVIew {

    void onGetWalletBalance(WalletBalanceBean bean);


}
