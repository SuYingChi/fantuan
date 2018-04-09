package com.wetime.fanc.circle.presenter;


import com.wetime.fanc.circle.iviews.ICommentActView;
import com.wetime.fanc.circle.iviews.IPublishCircleView;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.fan.http.okhttp.OkHttpUtils;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class CommentActPresenter {
    private ICommentActView iView;

    public CommentActPresenter(ICommentActView iView) {
        this.iView = iView;
    }

    public void commnetAct() {
        String content = iView.getContent();
        content=content.replace("\n"," ");
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_COMMENT)
                .addParams("token", iView.getToken())
                .addParams("dy_id", iView.getDyId())
                .addParams("content", content)
                .addParams("to_uid", iView.getToUid())
                .build()
                .execute(new DataStringCallback(iView, true) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        BaseBean bean = GsonUtils.getGsonInstance().fromJson(s, BaseBean.class);
                        if (bean.getError() == 0)
                            iView.onCommentAct(bean);
                    }
                });
    }


}
