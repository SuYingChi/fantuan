package com.wetime.fanc.home.adapter;

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
import com.wetime.fanc.home.bean.HomeItemBeanV2;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class FoucsNineGridLAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cover> mlist = new ArrayList<>();
    private LayoutInflater inflater = null;
//    private final int mGridWidth;

    public FoucsNineGridLAdapter(Context mContext, List<Cover> list) {
        this.mContext = mContext;
        this.mlist = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mlist != null ? mlist.size() : 0;
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
            convertView = inflater.inflate(R.layout.img_s_item_focus, null);
            holder = new Holder();
            holder.iv = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Glide.with(mContext.getApplicationContext()).load(mlist.get(position).getCompress())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(holder.iv);


        return convertView;
    }

    private class Holder {
        private ImageView iv;

    }


}
