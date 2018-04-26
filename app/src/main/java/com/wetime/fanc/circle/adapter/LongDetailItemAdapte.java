package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
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
                if (Double.parseDouble(longTextBean.getHeight()) > 4096) {
                    h = 4096;
                } else {
                    h = (int) (screenW * d);
                }
                if (screenW > 4096) {
                    screenW = 4096;
                }
                ViewGroup.LayoutParams layoutParams = ((ImageView) holder.getView(R.id.item_img)).getLayoutParams();
                layoutParams.height = h;
                layoutParams.width = screenW;
                ((ImageView) holder.getView(R.id.item_img)).setLayoutParams(layoutParams);
                Glide.with(context)
                        .load(longTextBean.getImageUrl())
                        .apply(
                                new RequestOptions()
                                        .override(screenW, h)
                                        .centerCrop()
                                        .dontAnimate()
                                        .placeholder(R.drawable.iv_default_news_small)
                        )
                        .into(((ImageView) holder.getView(R.id.item_img)));
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

}
