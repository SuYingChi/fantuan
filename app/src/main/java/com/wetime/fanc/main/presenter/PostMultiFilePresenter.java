package com.wetime.fanc.main.presenter;


import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.main.ivews.IPostMultiFileView;
import com.wetime.fanc.utils.Const;
import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.util.List;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;

/**
 * Created by zhoukang on 2017/5/19.
 */

public class PostMultiFilePresenter {
    private IPostMultiFileView iView;

    public PostMultiFilePresenter(IPostMultiFileView iView) {
        this.iView = iView;
    }

    public void PostMultiFile(List<String> list) {

        PostFormBuilder builder = OkHttpUtils
                .post()
                .addParams("token", iView.getToken())
                .url(Const.POSTMUTILEFILE);
        for (int i = 0; i < list.size(); i++) {

            File f = new File(list.get(i));

            builder = builder.addFile("mFile" + i, "jhjhj" + i, f);
        }


        builder.build()
                .execute(new DataStringCallback(iView, true, true, true, true, false) {
                    @Override
                    public void onResponse(String s, int i) {
                        super.onResponse(s, i);
                        PostFileResultBean msg = getGsonInstance().fromJson(s, PostFileResultBean.class);
                        if (msg.getError() == 0)
                            iView.onPostResult(msg);
                    }
                });
    }


}
