package com.wetime.fanc.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.shop.bean.ShopDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class ShopDetailYouhuiquanAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopDetailBean.DataBean.CouponBean.ContentBeanX> mData;
    private Context mContext;

    public ShopDetailYouhuiquanAdapter(Context context, List<ShopDetailBean.DataBean.CouponBean.ContentBeanX> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size() > 2 ? 2 : mData.size();
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

    public interface OnGetClickLitener {
        void onItemClick(View view, int position);
    }


    private OnGetClickLitener mOnGetClickLitener;


    public void setOnGetClickLitener(OnGetClickLitener mOnGetClickLitener) {
        this.mOnGetClickLitener = mOnGetClickLitener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_youhuiquan_shopdetail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShopDetailBean.DataBean.CouponBean.ContentBeanX bean = mData.get(position);
        holder.tvJine.setText("￥" + bean.getAmount());
        holder.tvMane.setText("满" + bean.getThreshold() + "可用");

        if (bean.isIs_get()) {
            holder.tvGet.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
        } else {
            holder.tvGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnGetClickLitener != null)
                        mOnGetClickLitener.onItemClick(v, position);
                }
            });
        }
        return convertView;
    }


    public class ViewHolder {
        @BindView(R.id.tv_jine)
        TextView tvJine;
        @BindView(R.id.tv_mane)
        TextView tvMane;
        @BindView(R.id.tv_get)
        TextView tvGet;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

