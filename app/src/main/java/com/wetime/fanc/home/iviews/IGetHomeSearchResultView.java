package com.wetime.fanc.home.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.home.bean.HomeSearchResult;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetHomeSearchResultView extends IBaseVIew {


//    sort 排列方式： 0=智能排序，1=距离优先，2=人均
//            cid
//    floor_id
//            pn


//      "search_word":"澳门",
//              "lng":"110.31803",
//              "lat":"20.02833",
//              "sort":"0",
//              "cate_id":"",
//              "mall_id":"",
//              "pn":"1"



    String getWord();
    String getJd();
    String getWd();
    String getSortMethod();
    String getMailId();
    String getPage();

    String getCId();




    void  onGetHomeSearchResultBean(HomeSearchResult bean);

}
