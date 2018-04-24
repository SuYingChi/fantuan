package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
                holder.setText(R.id.item_name, longTextBean.getContent());
                holder.getView(R.id.item_img).setVisibility(View.GONE);
                holder.getView(R.id.item_content).setVisibility(View.GONE);
                break;
            case "2":
                holder.getView(R.id.item_name).setVisibility(View.GONE);
                holder.setText(R.id.item_content, longTextBean.getDes());
                int sw = Tools.getScreenW(context);
                Double rate = Double.parseDouble(longTextBean.getHeight()) / Double.parseDouble(longTextBean.getWidth());
                int h = (int) (sw * rate);
                Glide.with(context)
                        .load(longTextBean.getImageUrl())
                        .apply(
                                new RequestOptions()
                                        .override(sw, h)
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
