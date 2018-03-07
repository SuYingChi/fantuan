package com.wetime.fanc.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.my.bean.MyShopListBean;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.act.ShopSayActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class MyShopAdapter extends RecyclerView.Adapter {
    private List<MyShopListBean.DataBean.ListBean> list;
    private Context mActivity;

    public MyShopAdapter(List<MyShopListBean.DataBean.ListBean> list, Context mActivity) {
        this.list = list;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_my_shop, parent, false));
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderViewHolder oholder = (OrderViewHolder) holder;
        MyShopListBean.DataBean.ListBean bean = list.get(position);

        Glide.with(mActivity).load(bean.getLogo()).into(((OrderViewHolder) holder).ivCover);
        ((OrderViewHolder) holder).tvName.setText(bean.getName());
        ((OrderViewHolder) holder).rbSocre.setStar(Float.valueOf(bean.getScore()));
        ((OrderViewHolder) holder).tvCentername.setText(bean.getMall_name());
        ((OrderViewHolder) holder).tvType.setText(bean.getCate_name());
        ((OrderViewHolder) holder).tvAddres.setText(bean.getAddress());
        ((OrderViewHolder) holder).tvDis.setText(bean.getDistance());
        ((OrderViewHolder) holder).tvAct.setOnClickListener(view -> {
            Intent go = new Intent(mActivity, ShopSayActivity.class);
            go.putExtra("mid", bean.getMid());
            go.putExtra("url", bean.getLogo());
            go.putExtra("name", bean.getName());
            go.putExtra("score", bean.getScore());
            mActivity.startActivity(go);
        });


        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
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
        MyRatingBar rbSocre;
        @BindView(R.id.tv_spend)
        TextView tvSpend;
        @BindView(R.id.tv_dis)
        TextView tvDis;
        @BindView(R.id.tv_centername)
        TextView tvCentername;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.tv_act)
        TextView tvAct;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
