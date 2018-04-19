package com.wetime.fanc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.builder.PostFormBuilder;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.circle.bean.PublishResultBean;
import com.wetime.fanc.main.bean.PostFileResultBean;
import com.wetime.fanc.service.event.UploadProgessEvent;
import com.wetime.fanc.service.event.uploadEvent;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.wetime.fanc.utils.GsonUtils.getGsonInstance;

public class UploadImageService extends Service {

    private String token = "";
    private ArrayList<String> defaultDataArray;
    private String content;
    private String CircleId;
    private String CJd;
    private String CWd;
    private String Loc;

    public UploadImageService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        defaultDataArray = intent.getStringArrayListExtra("defaultDataArray");
        token = intent.getStringExtra("token");
        CircleId = intent.getStringExtra("CircleId");
        CJd = intent.getStringExtra("CJd");
        CWd = intent.getStringExtra("CWd");
        Loc = intent.getStringExtra("Loc");
        content = intent.getStringExtra("content");
        PostMultiFile(defaultDataArray);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void PostMultiFile(List<String> list) {

        PostFormBuilder builder = OkHttpUtils
                .post()
                .addParams("token", token)
                .url(Const.POSTMUTILEFILE);
        for (int i = 0; i < list.size(); i++) {

            File f = new File(list.get(i));

            builder = builder.addFile("mFile" + i, "jhjhj" + i, f);
        }


        builder.build()
                .execute(new StringCallback() {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        Log.e("xi", "onResponse: " + progress);
                        EventBus.getDefault().post(new UploadProgessEvent(progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        EventBus.getDefault().post(new uploadEvent("-1"));
                        UploadImageService.this.stopSelf();
                        Toast.makeText(UploadImageService.this, "上传失败!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        PostFileResultBean msg = getGsonInstance().fromJson(s, PostFileResultBean.class);
                        if (msg.getError() == 0)
                            publishCircle((GsonUtils.getGsonInstance().toJson(msg.getData().getId())));
                    }
                });
    }

    public void publishCircle(String ids) {
//        "lng":"110.3196",
//                "lat":"20.02862",
//                "location":"百方"
        content = content.replace("\n", " ");
        OkHttpUtils
                .post()
                .url(Const.DYNAMIC_PUBLISH_SAVE)
                .addParams("token", token)
                .addParams("circle_id", CircleId)
                .addParams("content", content)
                .addParams("image_ids", ids)
                .addParams("lng", CJd)
                .addParams("lat", CWd)
                .addParams("location", Loc)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        EventBus.getDefault().post(new uploadEvent("-1"));
                        UploadImageService.this.stopSelf();
                        Toast.makeText(UploadImageService.this, "上传失败!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        PublishResultBean bean = GsonUtils.getGsonInstance().fromJson(s, PublishResultBean.class);
                        if (bean.getError() == 0)
                            EventBus.getDefault().post(new uploadEvent(bean.getData().getId()));
                        Toast.makeText(getApplicationContext(), "文章上传成功", Toast.LENGTH_SHORT).show();
                        UploadImageService.this.stopSelf();
                    }
                });
    }

}
