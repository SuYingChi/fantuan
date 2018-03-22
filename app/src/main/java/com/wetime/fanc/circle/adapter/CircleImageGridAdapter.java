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
import com.wetime.fanc.circle.bean.ActDetailBean;

import java.util.ArrayList;
import java.util.List;

public class CircleImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<ActDetailBean.DataBean.LikeListBean> mlist = new ArrayList<>();
    private LayoutInflater inflater = null;
//    private final int mGridWidth;

    public CircleImageGridAdapter(Context mContext, List<ActDetailBean.DataBean.LikeListBean> list) {
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
            convertView = inflater.inflate(R.layout.circle_img_item_list, null);
            holder = new Holder();
            holder.iv = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Glide.with(mContext.getApplicationContext())
                .load(mlist.get(position).getAvatar())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_head_default).centerCrop())
                .into(holder.iv);


        return convertView;
    }

    private class Holder {
        private ImageView iv;

    }


}
