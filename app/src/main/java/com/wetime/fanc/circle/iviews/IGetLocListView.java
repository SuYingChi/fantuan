package com.wetime.fanc.circle.iviews;


import com.wetime.fanc.circle.bean.SelectLocListBean;
import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetLocListView extends IBaseVIew {

    //    "token":"f_p0tkc2_64rOSV_R5G03Snt2VXzRY6Q",
//            "lng":"110.3196",
//            "lat":"20.02862",
//            "keyword":"",
//            "title":"",
//            "pn":"1"
    void onGetLocList(SelectLocListBean bean);

    String getKeyWord();

    String getLocTitle();

    int getPage();

}
