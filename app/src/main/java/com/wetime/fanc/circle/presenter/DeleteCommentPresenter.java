package com.wetime.fanc.circle.presenter;


import com.fan.http.okhttp.OkHttpUtils;
import com.wetime.fanc.circle.iviews.IDeleteCommentView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteCommentPresenter {
    private IDeleteCommentView iView;

    public DeleteCommentPresenter(IDeleteCommentView iView) {
        this.iView = iView;
    }

    public void deleteComment(String type, String type_id, String content) {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_COMMENT_DELETE)
                .addHeader("token", iView.getToken())
                .addParams("token", iView.getToken())
                .addParams("type", type)
                .addParams("type_id", type_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0) {
                            iView.onDeleteResult(bean, type);
                        }
                    }
                });
    }

    public void reportComment(String type, String type_id, String content) {
        OkHttpUtils
                .post()
                .url(Const.REPORT)
                .addHeader("token", iView.getToken())
                .addParams("token", iView.getToken())
                .addParams("type", type)
                .addParams("type_id", type_id)
                .addParams("content", content)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onReportResult(bean);
                    }
                });
    }


}
