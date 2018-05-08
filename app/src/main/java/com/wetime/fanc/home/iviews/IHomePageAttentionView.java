package com.wetime.fanc.home.iviews;

import com.wetime.fanc.home.bean.HomePageAttentionBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by admin on 2018/5/4.
 */

public interface IHomePageAttentionView extends IBaseVIew {

    void onGetAttention(HomePageAttentionBean bean);

    String getPage();
}
