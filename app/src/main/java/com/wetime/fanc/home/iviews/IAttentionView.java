package com.wetime.fanc.home.iviews;

import com.wetime.fanc.home.bean.HeadAttentionBean;
import com.wetime.fanc.main.ivews.IBaseVIew;

/**
 * Created by admin on 2018/5/4.
 */

public interface IAttentionView extends IBaseVIew {

    void onGetAttention(HeadAttentionBean bean);

    String getPage();
}
