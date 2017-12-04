package com.wetime.fanc.shopcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class ShopTaocanItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MerchantsBean.VoucherGrouponListBean> mData;
    private Context mContext;
    private boolean iszhe = true;

    public boolean isIszhe() {
        if (iszhe && mData.size() > 2) {
            return iszhe;
        } else {
            return !iszhe;
        }

    }

    public void setIszhe(boolean iszhe) {
        this.iszhe = iszhe;
        notifyDataSetChanged();
    }

    public ShopTaocanItemAdapter(Context context, List<MerchantsBean.VoucherGrouponListBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (mData.size() > 2) {
            if (iszhe) {
                return 2;
            } else {
                return mData.size();
            }
        } else {
            return mData.size();
        }


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

            convertView = mInflater.inflate(R.layout.item_voucher, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MerchantsBean.VoucherGrouponListBean bean = mData.get(position);
        holder.tvPrice.setText(bean.getAmount());
        holder.tvTitle.setText(bean.getName());
        holder.tvDes.setText(bean.getSales());
        if(bean.getSales().equals("")){
            holder.tvDes.setVisibility(View.GONE);
        }else {
            holder.tvDes.setVisibility(View.VISIBLE);
        }
        return convertView;

    }




    public class ViewHolder {
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}

