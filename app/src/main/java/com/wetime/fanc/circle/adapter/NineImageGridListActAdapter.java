package com.wetime.fanc.circle.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shizhefei.view.largeimage.UpdateImageView;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.LargeImageViewTarget;
import com.wetime.fanc.home.bean.Cover;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class NineImageGridListActAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cover> mlist = new ArrayList<Cover>();
    private LayoutInflater inflater = null;
//    private final int mGridWidth;

    public NineImageGridListActAdapter(Context mContext, List<Cover> list) {
        this.mContext = mContext;
        this.mlist = list;
        inflater = LayoutInflater.from(mContext);
    }

    public static Bitmap convertViewToBitmap(View view, int size) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int width = size * 40;
        view.layout(0, 0, width, view.getMeasuredHeight());  //根据字符串的长度显示view的宽度
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("CheckResult")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.img_item_list, null);
            holder = new Holder();
            holder.iv = convertView.findViewById(R.id.img_larg);
            holder.iv_img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (mlist.size() > 1) {

            holder.iv.setVisibility(View.GONE);
            holder.iv_img.setVisibility(View.VISIBLE);
            int sw;
            int h;
            if (mlist.size() == 2 || mlist.size() == 4) {
                sw = Tools.getScreenW(mContext) / 2;
                Double rate = 80.0 / 110;

                h = (int) (sw * rate);

                ViewGroup.LayoutParams layoutParams = holder.iv_img.getLayoutParams();
                layoutParams.height = h;
                layoutParams.width = sw;
                holder.iv_img.setLayoutParams(layoutParams);
            } else {
                sw = Tools.getScreenW(mContext) / 3;
                Double rate = 80.0 / 110;

                h = (int) (sw * rate);

                ViewGroup.LayoutParams layoutParams = holder.iv_img.getLayoutParams();
                layoutParams.height = h;
                layoutParams.width = sw;
                holder.iv_img.setLayoutParams(layoutParams);
            }

            Glide.with(mContext)
                    .load(mlist.get(position).getUrl())
                    .apply(new RequestOptions()
                            .override(sw, h)
                            .centerCrop())
                    .into(holder.iv_img);

        } else {

            holder.iv.setVisibility(View.VISIBLE);
            holder.iv_img.setVisibility(View.GONE);

            int sw = Tools.getScreenW(mContext);
            Double rate = (double) (mlist.get(position).getHeight() / mlist.get(position).getWidth());
            int h;
            if (mlist.get(position).getHeight() > mlist.get(position).getWidth()) {
                h = (int) (sw * rate);
            } else {
                h = (int) (sw * 9 / 16);
            }


            ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
            layoutParams.height = h;
            layoutParams.width = sw;
            holder.iv.setLayoutParams(layoutParams);

            Glide.with(mContext).load(mlist.get(position).getUrl())
                    .downloadOnly(new LargeImageViewTarget(holder.iv) {
                        @Override
                        public void onLoadStarted(Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            //设置加载中的占位图
//                    ((UpdateImageView) holder.iv).setImage(LayoutToDrawable(R.layout.img_item_pregess, sw));
                        }
                    });


        }


        return convertView;
    }

    public Drawable LayoutToDrawable(int layout_id, int w) {

        LayoutInflater inflator = ((Activity) mContext).getLayoutInflater();
        View viewHelp = inflator.inflate(/*R.layout.test */ layout_id, null);
        Bitmap snapshot = convertViewToBitmap(viewHelp, w);
        Drawable drawable = (Drawable) new BitmapDrawable(snapshot);

        return drawable;

    }

    private class Holder {
        private UpdateImageView iv;
        private ImageView iv_img;

    }


}
