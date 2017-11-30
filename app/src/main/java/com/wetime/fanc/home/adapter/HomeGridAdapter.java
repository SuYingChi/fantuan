package com.wetime.fanc.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomePageBean.DataBean.BigcatesBean> mlist = new ArrayList<>();
    private LayoutInflater inflater = null;


    public HomeGridAdapter(Context mContext, List<HomePageBean.DataBean.BigcatesBean> list) {
        this.mContext = mContext;
        this.mlist.addAll(list);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
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

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_home_bigcat, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(mlist.get(position).getName());
        Glide.with(mContext).load(mlist.get(position).getIcon()).into(holder.ivcover);

        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvname;
        @BindView(R.id.iv_cover)
        ImageView ivcover;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
