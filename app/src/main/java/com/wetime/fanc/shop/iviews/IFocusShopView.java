package com.wetime.fanc.shop.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.shop.bean.ShopDetailBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IFocusShopView extends IBaseVIew {
    String getMId();

    void onFocusShop(BaseBean bean);


}
