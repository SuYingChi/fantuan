package com.wetime.fanc.my.iviews;


import com.wetime.fanc.main.ivews.IBaseVIew;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.my.bean.UserCardBean;

/**
 * Created by zhoukang on 2017/5/19.
 */

public interface IGetUserCardView extends IBaseVIew {
    void onGetUserCard(UserCardBean bean);
    void onReportResult(BaseBean bean);
    int getPage();

    String getType();

    String getUid();


}
