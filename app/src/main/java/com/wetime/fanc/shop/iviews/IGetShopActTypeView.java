package com.wetime.fanc.shop.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.shop.bean.ShopActListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopActTypeView extends IBaseVIew {
    void onGetACt(ShopActListBean bean);

    String getMid();

    String getPage();

}
