package com.wetime.fanc.setting.presenter;


import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.setting.iviews.IUnBindView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class UnBindWeiXinPresenter {
    private IUnBindView iView;

    public UnBindWeiXinPresenter(IUnBindView iView) {
        this.iView = iView;
    }

    public void unBindWeixin() {
        OkHttpUtils
                .post()
                .url(Const.WEIXIN_UNBIND)
                .addParams("token", iView.getToken())
                .build()
                .execute(new DataStringCallback(iView) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onUnbindWeixin(bean);
                    }
                });
    }


}
