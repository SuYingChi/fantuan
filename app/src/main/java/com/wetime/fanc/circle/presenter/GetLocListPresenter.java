package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.SelectLocListBean;
import com.wetime.fanc.circle.iviews.IGetLocListView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class GetLocListPresenter {
    private IGetLocListView iView;

    public GetLocListPresenter(IGetLocListView iView) {
        this.iView = iView;
    }

    public void getLoclist() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_ADDRESS_SEARCH)
                .addParams("token", iView.getToken())
                .addParams("lng", iView.getJd())
                .addParams("lat", iView.getWd())
                .addParams("keyword", iView.getKeyWord())
                .addParams("title", iView.getLocTitle())
                .addParams("pn", iView.getPage() + "")
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        SelectLocListBean bean = GsonUtils.getGsonInstance().fromJson(s, SelectLocListBean.class);
                        if (bean.getError() == 0)
                            iView.onGetLocList(bean);
                    }
                });
    }


}
