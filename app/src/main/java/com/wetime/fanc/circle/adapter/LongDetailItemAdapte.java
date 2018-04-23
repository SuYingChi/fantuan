package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.utils.Tools;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class LongDetailItemAdapte extends CommonAdapter<LongTextBean> {

    private Context context;

    private List<LongTextBean> datas;
    private ArrayList<String> list;

    LongDetailItemAdapte(Context context, int layoutId, List<LongTextBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.datas = datas;
        list = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            switch (datas.get(i).getType()) {
                case "2":
                    list.add(datas.get(i).getImageUrl());
                    break;
            }
        }
    }

    @Override
    protected void convert(ViewHolder holder, LongTextBean longTextBean, int position) {

        switch (longTextBean.getType()) {
            case "1":
                holder.setText(R.id.item_name, longTextBean.getContent());
                holder.getView(R.id.item_img).setVisibility(View.GONE);
                holder.getView(R.id.item_content).setVisibility(View.GONE);
                break;
            case "2":

                holder.getView(R.id.item_name).setVisibility(View.GONE);
                holder.setText(R.id.item_content, longTextBean.getContent());
                Glide.with(context)
                        .load(longTextBean.getImageUrl())
                        .into(((ImageView) holder.getView(R.id.item_img)));
//                DisplayMetrics dm = new DisplayMetrics();
//                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//                Glide.with(context)
//                        .load(longTextBean.getImageUrl())
//                        .into(new SimpleTarget<Drawable>() {
//                                  /**
//                                   * The method that will be called when the resource load has finished.
//                                   *
//                                   * @param resource   the loaded resource.
//                                   * @param transition
//                                   */
//                                  @Override
//                                  public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                                      Bitmap bitmap = drawableToBitmap(resource);
//                                      RequestOptions options = new RequestOptions()
//                                              .override(dm.widthPixels, bitmap.getHeight())
//                                              .centerCrop();
//                                      Glide.with(context)
//                                              .load(longTextBean.getImageUrl())
//                                              .apply(options)
//                                              .into(((ImageView) holder.getView(R.id.item_img)));
//                                  }
//                              }
//                        );
                holder.setOnClickListener(R.id.item_img, v -> {
                    for (int i = 0; i < list.size(); i++) {
                        if (datas.get(position).getImageUrl().equals(list.get(i))) {
                            Tools.goPicGallery((Activity) context, list, i);
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

}
