package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.order.MyRatingBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class HomeShopListAdapter extends RecyclerView.Adapter {
    private LayoutInflater mInflater;
    private List<HomePageBean.DataBean.MerchantsBean> mData;
    private Context mContext;

    public HomeShopListAdapter(Context context, List<HomePageBean.DataBean.MerchantsBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        mContext = context;
    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return mData.size();
//    }
//
//    @Override
//    public Object getItem(int arg0) {
//        // TODO Auto-generated method stub
//        return mData.get(arg0);
//    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_shop, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder oholder = (ViewHolder) holder;

        final HomePageBean.DataBean.MerchantsBean bean = mData.get(position);
        Glide.with(mContext).load(bean.getLogo()).into(oholder.ivCover);
        oholder.tvName.setText(bean.getName());
        oholder.rbSocre.setStar(bean.getScore());
        oholder.rbSocre.setClickable(false);
        oholder.tvDis.setText(bean.getDistance());
        oholder.tvCentername.setText(bean.getMall_name());
        oholder.tvType.setText(bean.getCategory_name());
        oholder.tvSeal.setText(bean.getSales());
        oholder.tvSpend.setText(bean.getAverage_spend());
        if (bean.getMall_name().equals("")) {
            oholder.tvCentername.setVisibility(View.GONE);
        } else {
            oholder.tvCentername.setVisibility(View.VISIBLE);
        }

        if (bean.getCategory_name().equals("")) {
            oholder.tvType.setVisibility(View.GONE);
        } else {
            oholder.tvType.setVisibility(View.VISIBLE);
        }
        if (bean.getSales().equals("")) {
            oholder.tvSeal.setVisibility(View.INVISIBLE);
        } else {
            oholder.tvSeal.setVisibility(View.VISIBLE);
        }

//        final HomeShopActAdapter actAdapter = new HomeShopActAdapter(mContext, bean.getMerchant_promotion_list());
//        holder.lv.setAdapter(actAdapter);
//        actAdapter.notifyDataSetChanged();

        oholder.llAct.removeAllViews();
        for(HomePageBean.DataBean.MerchantsBean.MerchantPromotionListBean b :bean.getMerchant_promotion_list()){
            View mView = mInflater.inflate(R.layout.item_home_shopact, null);
            TextView tvstr = mView.findViewById(R.id.tv_str);
            tvstr.setText(b.getContent());
            Glide.with(mContext).load(b.getIco()).into((ImageView)mView.findViewById(R.id.iv_icon));
            oholder.llAct.addView(mView);
        }

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view,position);
                }
            });
        }

    }
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    static public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rb_socre)
        MyRatingBar rbSocre;
        @BindView(R.id.tv_spend)
        TextView tvSpend;
        @BindView(R.id.tv_centername)
        TextView tvCentername;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_dis)
        TextView tvDis;
        @BindView(R.id.tv_seal)
        TextView tvSeal;
//        @BindView(R.id.lv)
//        ListViewForScrollView lv;
        @BindView(R.id.ll_act)
        LinearLayout llAct;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

