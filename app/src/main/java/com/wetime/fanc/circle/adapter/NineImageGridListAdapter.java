package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.Cover;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class NineImageGridListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cover> mlist = new ArrayList<Cover>();
    private LayoutInflater inflater = null;
//    private final int mGridWidth;

    public NineImageGridListAdapter(Context mContext, List<Cover> list) {
        this.mContext = mContext;
        this.mlist = list;
        inflater = LayoutInflater.from(mContext);
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.img_item_list, null);
            holder = new Holder();
            holder.iv = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (mlist.size() > 1) {
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;
            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mContext.getApplicationContext()).load(mlist.get(position))
                    .apply(new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(holder.iv);
        } else {
            int sw = Tools.getScreenW(mContext);
            int w = sw - Tools.dip2px(mContext, 15 + 15);
            Double rate = 9.0 / 16;

            int h = (int) (w * rate);
            Glide.with(mContext.getApplicationContext()).load(mlist.get(position))
                    .apply(new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_single_big))
                    .into(holder.iv);
        }


        return convertView;
    }

    private class Holder {
        private ImageView iv;

    }


}
