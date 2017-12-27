package com.wetime.fanc.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.shop.bean.ShopDetailBean;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class ShopDetailActAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopDetailBean.DataBean.CouponBean.ActivityBean> mData;
    private Context mContext;

    public ShopDetailActAdapter(Context context, List<ShopDetailBean.DataBean.CouponBean.ActivityBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mData.get(arg0);
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_promotion_shopdetail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShopDetailBean.DataBean.CouponBean.ActivityBean bean = mData.get(position);
        Glide.with(mContext).load(bean.getIco()).into(holder.ivIcon);
        holder.tvStr.setText(bean.getName());
        convertView.setClickable(false);
        return convertView;

    }


    public class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_str)
        TextView tvStr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

