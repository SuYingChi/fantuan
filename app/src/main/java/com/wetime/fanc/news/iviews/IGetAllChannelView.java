package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.AllChannelListBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetAllChannelView extends IBaseVIew {

    void onGetAllChannel(AllChannelListBean bean);

}
