package com.wetime.fanc.main.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;

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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现
        View view = LayoutInflater.from(activity).inflate(R.layout.picture_gallery_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.home_gallery_item_imv);

        Glide.with(activity).load(url.get(position)).into(imageView);
        container.addView(view);
        view.setOnClickListener(onClickListener);
        return view;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private View.OnClickListener onClickListener;
}
