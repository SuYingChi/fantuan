package com.wetime.fanc.home.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.home.bean.SearchResult;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeSugView extends IBaseVIew {

    void  onGetHomeSug(SearchResult bean,String word);

}
