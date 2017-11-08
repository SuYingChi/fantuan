package com.wetime.fanc.shopcenter.act;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class CenterListAdapter extends RecyclerView.Adapter {
    private List<MerchantsBean> list;
    private Context mActivity;

    public CenterListAdapter(List<MerchantsBean> list, Context mActivity) {
        this.list = list;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_center_list, parent, false));

    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, final int position) {
        final OrderViewHolder holder = (OrderViewHolder) h;
        final MerchantsBean bean = list.get(position);
        Glide.with(mActivity).load(bean.getLogo()).into(holder.ivCover);
        holder.tvName.setText(bean.getName());
        holder.rbSocre.setRating(bean.getScore());
        holder.tvSpend.setText(bean.getAverage_spend());
        holder.tvType.setText(bean.getCategory_name());
        if (bean.getSpider().equals("1")) {
            holder.ivFantuan.setVisibility(View.GONE);
        } else {
            holder.ivFantuan.setVisibility(View.VISIBLE);
        }
        holder.llActive.removeAllViews();

        for (MerchantsBean.PromotionListBean b : bean.getPromotion_list()) {
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_promotion, null);
            TextView tvstr = item.findViewById(R.id.tv_str);
            tvstr.setText(b.getName());
            ImageView iv = item.findViewById(R.id.iv_icon);
            Glide.with(mActivity).load(b.getIco()).into(iv);
            holder.llActive.addView(item);
        }
        if (bean.getPromotion_list().size() > 0) {
            View item = LayoutInflater.from(mActivity).inflate(R.layout.item_my_line, null);
            holder.llActive.addView(item);
        }
        holder.llTaocan.removeAllViews();
        if (bean.getVoucher_groupon_list().size() > 2 && bean.isIsfla()) {
            holder.llMore.setVisibility(View.VISIBLE);

            for (int i = 0; i < 2; i++) {
                MerchantsBean.VoucherGrouponListBean b = bean.getVoucher_groupon_list().get(i);
                View item = LayoutInflater.from(mActivity).inflate(R.layout.item_voucher, null);
                TextView tvprice = item.findViewById(R.id.tv_price);
                tvprice.setText(b.getAmount());

                TextView tvt = item.findViewById(R.id.tv_title);
                tvt.setText(b.getName());

                TextView tvd = item.findViewById(R.id.tv_des);
                tvd.setText(b.getSales());

                holder.llTaocan.addView(item);
                holder.tvMore.setText("查看其他" + bean.getVoucher_groupon_list().size() + "个套餐");
            }
        } else {
            for (MerchantsBean.VoucherGrouponListBean b : bean.getVoucher_groupon_list()) {
                View item = LayoutInflater.from(mActivity).inflate(R.layout.item_voucher, null);
                TextView tvprice = item.findViewById(R.id.tv_price);
                tvprice.setText(b.getAmount());

                TextView tvt = item.findViewById(R.id.tv_title);
                tvt.setText(b.getName());

                TextView tvd = item.findViewById(R.id.tv_des);
                tvd.setText(b.getSales());

                holder.llTaocan.addView(item);
            }
            holder.llMore.setVisibility(View.GONE);
        }
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setIsfla(false);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rb_socre)
        RatingBar rbSocre;
        @BindView(R.id.tv_spend)
        TextView tvSpend;
        @BindView(R.id.iv_fantuan)
        ImageView ivFantuan;
        @BindView(R.id.ll_active)
        LinearLayout llActive;
        @BindView(R.id.ll_taocan)
        LinearLayout llTaocan;
        @BindView(R.id.tv_more)
        TextView tvMore;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.ll_more)
        LinearLayout llMore;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
