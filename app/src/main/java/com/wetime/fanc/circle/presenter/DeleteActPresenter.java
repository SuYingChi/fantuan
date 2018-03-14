package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.iviews.IDeleteActView;
import com.wetime.fanc.circle.iviews.IDeleteCommentView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteActPresenter {
    private IDeleteActView iView;

    public DeleteActPresenter(IDeleteActView iView) {
        this.iView = iView;
    }

    public void deleteAct() {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_DELETE)
                .addParams("token", iView.getToken())
                .addParams("dy_id", iView.getDyId())
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onDeleteActtResult(bean);
                    }
                });
    }


}
