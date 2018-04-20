package com.wetime.fanc.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;
import com.wetime.fanc.R;

import java.io.File;
import java.util.List;


public class ImageAdapter extends PagerAdapter {

    private Activity activity;
    private LayoutInflater inflater = null;
    private List<String> url;

    public ImageAdapter(Activity activity, List<String> url) {
        this.activity = activity;
        this.url = url;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return url.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//及时销毁界面，防止内存溢出
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @SuppressLint("CheckResult")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现
        View view = LayoutInflater.from(activity).inflate(R.layout.picture_gallery_item, container, false);
        LargeImageView imageView = view.findViewById(R.id.home_gallery_item_imv);
//        Glide.with(activity).load(url.get(position)).into(imageView);


        Glide.with(activity).downloadOnly().load(url.get(position)).listener(new RequestListener<File>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                imageView.setImage(new FileBitmapDecoderFactory(resource));
                return false;
            }

        }).submit();

        imageView.setCriticalScaleValueHook(new LargeImageView.CriticalScaleValueHook() {
            @Override
            public float getMinScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMinScale) {
                return 1;
            }

            @Override
            public float getMaxScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMaxScale) {
                return 4;
            }
        });


        container.addView(view);
        imageView.setOnClickListener(onClickListener);
        return view;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private View.OnClickListener onClickListener;
}
