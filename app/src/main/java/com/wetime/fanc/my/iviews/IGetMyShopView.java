package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.bean.MyShopListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetMyShopView extends IBaseVIew {
    void onGetMyShop(MyShopListBean bean);

    String getPage();


}
