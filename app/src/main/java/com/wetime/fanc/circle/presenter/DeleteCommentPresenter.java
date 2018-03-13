package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.iviews.IDeleteCommentView;
import com.wetime.fanc.circle.iviews.IPublishCircleView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class DeleteCommentPresenter {
    private IDeleteCommentView iView;

    public DeleteCommentPresenter(IDeleteCommentView iView) {
        this.iView = iView;
    }

    public void deleteComment(String id) {
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_COMMENT_DELETE)
                .addParams("token", iView.getToken())
                .addParams("comment_id", id)
                .build()
                .execute(new DataStringCallback(iView, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onDeleteResult(bean);
                    }
                });
    }


}
