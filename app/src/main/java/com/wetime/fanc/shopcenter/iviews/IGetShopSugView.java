package com.wetime.fanc.shopcenter.iviews;


import com.wetime.fanc.home.bean.SearchResult;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetShopSugView extends IBaseVIew {

    String getMailId();
    void  onGetHomeSug(SearchResult bean, String word);

}
