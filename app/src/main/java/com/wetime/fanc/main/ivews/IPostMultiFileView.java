package com.wetime.fanc.main.ivews;


import com.king.batterytest.fbaselib.main.iview.IBaseVIew;
import com.wetime.fanc.main.bean.PostFileResultBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IPostMultiFileView extends IBaseVIew {
    void onPostResult(PostFileResultBean bean);

}
