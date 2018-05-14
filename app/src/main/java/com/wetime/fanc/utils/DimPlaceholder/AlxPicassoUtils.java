package com.wetime.fanc.utils.DimPlaceholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.wetime.fanc.R;

import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alex on 2016/9/20.
 */
public class AlxPicassoUtils {
    private static final WeakHashMap<String, ProgressWheel> progressWheelHashMap = new WeakHashMap<>();//用于管理进度条的map,使用弱引用可以防止OOM
    private static final WeakHashMap<String, TextView> textViewHashMap = new WeakHashMap<>();//用于管理进度条的map,使用弱引用可以防止OOM
    private static final ConcurrentHashMap<String, Integer> progressHashMap = new ConcurrentHashMap<>();//用于记录某个url的下载进度
    private static final Handler handler = new Handler();
    private static final int PROGRESS_SIZE = 200;//圆形进度条的大小
    private static final float PROGRESS_SPIN_SPEED = 0.2f;//圆形进度条的旋转速度
    private static Glide picasso;

    /**
     * 含模糊图片的情况
     *
     * @param url
     * @param imageView
     * @param progressWheel
     * @param textView
     * @param base64Blur
     */
    public static void displayImageProgress(Context context, final String url, ImageView imageView, final ProgressWheel progressWheel, final TextView textView, final String base64Blur) {

        if (progressWheel.getCircleRadius() != PROGRESS_SIZE)
            progressWheel.setCircleRadius(PROGRESS_SIZE);
        //控制进度显示的控件
        if (progressWheel.getSpinSpeed() != PROGRESS_SPIN_SPEED)
            progressWheel.setSpinSpeed(PROGRESS_SPIN_SPEED);
        //设置tag防止复用引起的错乱,将url和空间绑定起来
        progressWheel.setTag(R.id.progress_wheel, url);
        textView.setTag(R.id.tv1, url);
        Integer oldProgress = progressHashMap.get(url);
        //默认不显示，有读数的时候才显示
        if (oldProgress == null) {//null为以前没有下载过，0为开始下载但是没收到数据
            Log.i("Alex", "当前图片未下载");
            progressWheel.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            progressHashMap.put(url, 0);
        } else if (progressHashMap.get(url) >= 100) {
            Log.i("Alex", "当前图片下载已经完成" + progressHashMap.get(url));
            progressWheel.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        } else {
            Log.i("Alex", "当前图片正在下载");
            progressWheel.setVisibility(View.VISIBLE);
            progressWheel.setProgress(progressHashMap.get(url) / 100f);
            textView.setVisibility(View.VISIBLE);
            textView.setText(progressHashMap.get(url) + "%");
        }
        progressWheelHashMap.put(url, progressWheel);
        textViewHashMap.put(url, textView);
        //同一张图会开两个线程同时下载，如果你滑下去又划上来
        //如果没有模糊占位图，就是用普通图占位，反之使用模糊图占位
        if (TextUtils.isEmpty(base64Blur))
            Glide.with(context).load(url).apply(new RequestOptions().placeholder(R.drawable.default_error)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    //防止listView的控件复用
                    if (url.equals(progressWheel.getTag(R.id.progress_wheel)) && url.equals(textView.getTag(R.id.tv1))) {
                        progressWheel.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        progressHashMap.put(url, 101);//101代表成功显示出来了\
                        imageView.setImageDrawable(resource);
                    }
                }
            });
        else
            Glide.with(context).load(url).apply(new RequestOptions().placeholder(R.drawable.default_error)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    //防止listView的控件复用
                    if (url.equals(progressWheel.getTag(R.id.progress_wheel)) && url.equals(textView.getTag(R.id.tv1))) {
                        progressWheel.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        progressHashMap.put(url, 101);//101代表成功显示出来了
                        imageView.setImageDrawable(resource);
                    }
                }
            });//模糊图占位
        //setImageBitmap必须再picasso.into()方法之后，否则会显示空白
        if (oldProgress == null || oldProgress < 100) {//如果没开始下载或者没有下载完
            Bitmap blurBitmap = null;
            if (!TextUtils.isEmpty(base64Blur))
                blurBitmap = AlxBitmapUtils.base64ToBlurBitmap(imageView.getContext(), base64Blur);
            if (blurBitmap != null) imageView.setImageBitmap(blurBitmap);
        }
    }
}
