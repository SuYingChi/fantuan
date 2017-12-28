package com.wetime.fanc.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.shop.bean.ShopDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class ShopDetailTaocanAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopDetailBean.DataBean.GrouponBean.ContentBeanXX> mData;
    private Context mContext;
    private boolean iszhe = true;

    public boolean isIszhe() {
        return iszhe;
    }

    public void setIszhe(boolean iszhe) {
        this.iszhe = iszhe;
    }
    public ShopDetailTaocanAdapter(Context context, List<ShopDetailBean.DataBean.GrouponBean.ContentBeanXX> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (isIszhe())
            return mData.size() > 2 ? 2 : mData.size();
        else
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_taocan_shopdetail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShopDetailBean.DataBean.GrouponBean.ContentBeanXX bean = mData.get(position);


        Glide.with(mContext).load(bean.getImgUrl()).into(holder.ivTancanPic);
        holder.tvTaocanName.setText(bean.getName());
        holder.tvTaocanYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvTaocanYuanjia.setText("¥"+bean.getMarket_price());
        holder.tvTaocanXianjia.setText(bean.getAmount());
        holder.tvTaocanYishou.setText(bean.getTotal_sales());
        holder.tvTaocanBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuyTaocanClickLitener != null)
                    mBuyTaocanClickLitener.onBuyTaocanClick(v, position);
            }
        });
        return convertView;

    }
    public interface OnBuyTaocanClickLitener {
        void onBuyTaocanClick(View view, int position);
    }


    private OnBuyTaocanClickLitener mBuyTaocanClickLitener;


    public void setOnBuyTaocanClickLitener(OnBuyTaocanClickLitener mBuyTaocanClickLitener) {
        this.mBuyTaocanClickLitener = mBuyTaocanClickLitener;
    }


    public class ViewHolder {
        @BindView(R.id.iv_tancan_pic)
        ImageView ivTancanPic;
        @BindView(R.id.tv_taocan_name)
        TextView tvTaocanName;
        @BindView(R.id.tv_taocan_xianjia)
        TextView tvTaocanXianjia;
        @BindView(R.id.tv_taocan_yuanjia)
        TextView tvTaocanYuanjia;
        @BindView(R.id.tv_taocan_buy)
        TextView tvTaocanBuy;
        @BindView(R.id.tv_taocan_yishou)
        TextView tvTaocanYishou;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}

