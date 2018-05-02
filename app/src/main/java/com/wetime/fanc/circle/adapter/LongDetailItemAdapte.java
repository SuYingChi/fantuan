package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shizhefei.view.largeimage.UpdateImageView;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.customview.LargeImageViewTarget;
import com.wetime.fanc.utils.Tools;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class LongDetailItemAdapte extends CommonAdapter<LongTextBean> {

    private Context context;

    private List<LongTextBean> datas;
    private ArrayList<String> list;
    private ArrayList<String> content;

    LongDetailItemAdapte(Context context, int layoutId, List<LongTextBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.datas = datas;
        list = new ArrayList<>();
        content = new ArrayList<>();
        if (datas == null) return;
        for (int i = 0; i < datas.size(); i++) {
            switch (datas.get(i).getType()) {
                case "2":
                    list.add(datas.get(i).getImageUrl());
                    content.add(datas.get(i).getDes());
                    break;
            }
        }
    }

    @Override
    protected void convert(ViewHolder holder, LongTextBean longTextBean, int position) {

        switch (longTextBean.getType()) {
            case "1":
                if (TextUtils.isEmpty(longTextBean.getContent())) {
                    holder.getView(R.id.item_name).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.item_name).setVisibility(View.VISIBLE);
                    holder.setText(R.id.item_name, longTextBean.getContent());
                }
                holder.getView(R.id.item_img).setVisibility(View.GONE);
                holder.getView(R.id.item_content).setVisibility(View.GONE);
                break;
            case "2":
                holder.getView(R.id.item_img).setVisibility(View.VISIBLE);
                holder.getView(R.id.item_name).setVisibility(View.GONE);
                if (TextUtils.isEmpty(longTextBean.getDes())) {
                    holder.getView(R.id.item_content).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.item_content).setVisibility(View.VISIBLE);
                    String des = longTextBean.getDes();
                    des = des.replace("\n", " ");
                    holder.setText(R.id.item_content, des);
                }
                int screenW = Tools.getScreenW(context);
                Double d = Double.parseDouble(longTextBean.getHeight()) / Double.parseDouble(longTextBean.getWidth());
                int h;
                h = (int) (screenW * d);
//                if (Double.parseDouble(longTextBean.getHeight()) > 4096) {
//                    h = 4096;
//                } else {
//                    h = (int) (screenW * d);
//                }
//                if (screenW > 4096) {
//                    screenW = 4096;
//                }
                ViewGroup.LayoutParams layoutParams = (holder.getView(R.id.item_img)).getLayoutParams();
                layoutParams.height = h;
                layoutParams.width = screenW;
                (holder.getView(R.id.item_img)).setLayoutParams(layoutParams);


                Glide.with(mContext).load(longTextBean.getImageUrl()).downloadOnly(new LargeImageViewTarget((holder.getView(R.id.item_img))) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        //设置加载中的占位图
//                        ((UpdateImageView) holder.getView(R.id.item_img)).setImage(new ColorDrawable(Color.GREEN));
                    }
                });


                holder.setOnClickListener(R.id.item_img, v -> {
                    for (int i = 0; i < list.size(); i++) {
                        if (datas.get(position).getImageUrl().equals(list.get(i))) {
                            Tools.goPicGallery((Activity) context, list, content, i);
                        }
                    }
                });
                break;
        }


    }

    public Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }

    // Drawable转换成InputStream
    public InputStream Drawable2InputStream(Drawable d) {
        Bitmap bitmap = this.drawable2Bitmap(d);
        return this.Bitmap2InputStream(bitmap);
    }

    // Drawable转换成Bitmap
    public Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    // 将Bitmap转换成InputStream
    public InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }


}
