package com.wetime.fanc.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wetime.fanc.R;
import com.wetime.fanc.utils.MLoggerInterceptor;
import com.wetime.fanc.utils.SharePreferenceUtil;
import com.wetime.fanc.utils.Tools;
import com.fan.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


public class FApp extends Application {
    private List<Activity> oList;
    private SharePreferenceUtil spu;
    private static FApp instance;
    public static IWXAPI mWxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        registToWX();
        JPushInterface.init(this);
        JPushInterface.stopCrashHandler(this);
//        ArrayList<String> keys = new ArrayList<>();
//        if (isd(this)) {
//            keys.add("41:C2:55:46:96:1E:86:A8:FC:21:77:2C:77:37:6C:C9:30:41:C9:FA");//test
//        }
//
//
//        boolean checked = false;
//        for (String s : keys) {
//            SignCheck signCheck = new SignCheck(this, s);
//            if (signCheck.check()) {
//                checked = true;
//            }
//
//        }
//        if (!checked) {
//            System.exit(0);
//        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MLoggerInterceptor("http", true))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)

                .build();

        OkHttpUtils.initClient(okHttpClient);
        oList = new ArrayList<>();
        spu = Tools.getSpu(this);
        instance = this;


    }

    public static boolean isd(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    static {

//        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> new MyMaterialHeader(context));
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            ClassicsHeader header = new ClassicsHeader(context);
            header.setDrawableArrowSize(14);//设置箭头的大小（dp单位）
            header.setDrawableProgressSize(14);//设置图片的大小（dp单位）
            header.setAccentColor(context.getResources().getColor(R.color.text_hint));//设置强调颜色
            header.setTextSizeTitle(14);
            return header;
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            ClassicsFooter footer = new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            footer.setDrawableArrowSize(14);//设置箭头的大小（dp单位）
            footer.setDrawableProgressSize(14);//设置图片的大小（dp单位）、
            footer.setProgressDrawable(context.getResources().getDrawable(R.drawable.loading_32dp));
            footer.setAccentColor(context.getResources().getColor(R.color.text_hint));//设置强调颜色
            footer.setTextSizeTitle(14);
            footer.setFinishDuration(0);
            return footer;
        });


    }

    public static FApp getInstance() {
        return instance;
    }


    public void addActivity(Activity activity) {

        if (!oList.contains(activity)) {
            oList.add(activity);
        }
    }

    private void registToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, "wx2fbcb61b6e5b1384", true);
        mWxApi.registerApp("wx2fbcb61b6e5b1384");
    }


    public void removeActivity(Activity activity) {
        if (oList.contains(activity)) {
            oList.remove(activity);
            activity.finish();
        }
    }


    public void removeALLActivity() {
        for (Activity activity : oList) {
            activity.finish();
        }
    }
}

