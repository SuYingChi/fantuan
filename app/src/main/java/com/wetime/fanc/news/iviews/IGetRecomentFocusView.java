package com.wetime.fanc.news.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.news.bean.AllChannelListBean;
import com.wetime.fanc.news.bean.FocusTitleList;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetRecomentFocusView extends IBaseVIew {

    void onGetRecomentTitle(FocusTitleList bean);

}
