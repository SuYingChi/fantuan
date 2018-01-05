package com.wetime.fanc.shopcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.customview.ListViewForScrollView;
import com.wetime.fanc.R;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;
import com.wetime.fanc.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class SearchShopListAdapter extends RecyclerView.Adapter {
    private List<MerchantsBean> list;
    private Context mActivity;

    public SearchShopListAdapter(List<MerchantsBean> list, Context mActivity) {
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
        holder.tvDis.setText(bean.getDistance());
        holder.tvFloor.setText(bean.getFloor());
        if(TextUtils.isEmpty(bean.getMall_name())){
            holder.tvMallname.setVisibility(View.GONE);
            holder.tvMallname.setText(bean.getMall_name());
        }else{
            holder.tvMallname.setVisibility(View.VISIBLE);
            holder.tvMallname.setText(bean.getMall_name());
        }


        // 1.1.0 新需求
        if (bean.getSpider().equals("1")) {
            holder.ivFantuan.setVisibility(View.INVISIBLE);
        } else {
            holder.ivFantuan.setVisibility(View.INVISIBLE);
        }

        ShopActItemAdapter actItemAdapter = new ShopActItemAdapter(mActivity, bean.getPromotion_list());
        holder.lvAct.setAdapter(actItemAdapter);
//        holder.lvAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent goweb = new Intent(mActivity, WebActivity.class);
//                goweb.putExtra("url", bean.getDetail_url());
//                mActivity.startActivity(goweb);
//            }
//        });
        actItemAdapter.notifyDataSetChanged();

        ShopTaocanItemAdapter taocanItemAdapter = new ShopTaocanItemAdapter(mActivity, bean.getVoucher_groupon_list());
        holder.lvTaocan.setAdapter(taocanItemAdapter);
        holder.lvTaocan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int ii, long id) {
                Intent goweb = new Intent(mActivity, WebActivity.class);
                goweb.putExtra("url", bean.getVoucher_groupon_list().get(ii).getUrl());
                mActivity.startActivity(goweb);
            }
        });


        if (bean.isZhe()) {
            if(bean.getVoucher_groupon_list().size()>2){
                holder.llMore.setVisibility(View.VISIBLE);
                int num = bean.getVoucher_groupon_list().size() - 2;
                holder.tvMore.setText("查看其他" + num + "个套餐");
            }
            else{
                taocanItemAdapter.setIszhe(false);
                holder.llMore.setVisibility(View.GONE);
            }

        } else {
            taocanItemAdapter.setIszhe(false);
            holder.llMore.setVisibility(View.GONE);
        }
        taocanItemAdapter.notifyDataSetChanged();

        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setZhe(false);
                notifyDataSetChanged();
            }
        });
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view,position);
                }
            });
        }
        holder.tvMallname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goweb = new Intent(mActivity, WebActivity.class);
//                Tools.toastInBottom(mActivity,"商城点击");
                goweb.putExtra("url", bean.getMall_url());
                mActivity.startActivity(goweb);
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
        @BindView(R.id.tv_floor)
        TextView tvFloor;
        @BindView(R.id.tv_mallname)
        TextView tvMallname;
        @BindView(R.id.tv_dis)
        TextView tvDis;
        @BindView(R.id.iv_fantuan)
        ImageView ivFantuan;
        @BindView(R.id.lv_act)
        ListViewForScrollView lvAct;
        @BindView(R.id.lv_taocan)
        ListViewForScrollView lvTaocan;
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
