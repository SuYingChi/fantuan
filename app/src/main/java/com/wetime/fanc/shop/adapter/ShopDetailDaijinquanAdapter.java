package com.wetime.fanc.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
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

public class ShopDetailDaijinquanAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopDetailBean.DataBean.VoucherBean.ContentBeanXXX> mData;
    private Context mContext;

    public ShopDetailDaijinquanAdapter(Context context, List<ShopDetailBean.DataBean.VoucherBean.ContentBeanXXX> mData) {
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_daijinquan_shopdetail, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShopDetailBean.DataBean.VoucherBean.ContentBeanXXX bean = mData.get(position);


        holder.tvDaijinquanName.setText(bean.getName());
        holder.tvDaijinquanYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvDaijinquanYuanjia.setText("¥" + bean.getMarket_price());
        holder.tvDaijinquanXianjia.setText(bean.getAmount());
        holder.tvDaijinquanYishou.setText(bean.getTotal_sales());
        holder.tvDaijinquanBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuyDaijinquanClickLitener != null)
                    mBuyDaijinquanClickLitener.onBuyDaijinquanClick(v, position);
            }
        });
        return convertView;

    }

    public interface OnBuyDaijinquanClickLitener {
        void onBuyDaijinquanClick(View view, int position);
    }


    private OnBuyDaijinquanClickLitener mBuyDaijinquanClickLitener;


    public void setOnBuyDaijinquanClickLitener(OnBuyDaijinquanClickLitener mBuyDaijinquanClickLitener) {
        this.mBuyDaijinquanClickLitener = mBuyDaijinquanClickLitener;
    }


    public class ViewHolder {
        @BindView(R.id.tv_daijinquan_name)
        TextView tvDaijinquanName;
        @BindView(R.id.tv_daijinquan_xianjia)
        TextView tvDaijinquanXianjia;
        @BindView(R.id.tv_daijinquan_yuanjia)
        TextView tvDaijinquanYuanjia;
        @BindView(R.id.tv_daijinquan_buy)
        TextView tvDaijinquanBuy;
        @BindView(R.id.tv_daijinquan_yishou)
        TextView tvDaijinquanYishou;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

