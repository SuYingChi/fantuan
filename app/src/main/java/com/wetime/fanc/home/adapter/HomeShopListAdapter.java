package com.wetime.fanc.home.adapter;

import android.content.Context;
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

public class HomeShopListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<HomePageBean.DataBean.MerchantsBean> mData;
    private Context mContext;

    public HomeShopListAdapter(Context context, List<HomePageBean.DataBean.MerchantsBean> mData) {
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
        ViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_home_shop, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HomePageBean.DataBean.MerchantsBean bean = mData.get(position);
        Glide.with(mContext).load(bean.getLogo()).into(holder.ivCover);
        holder.tvName.setText(bean.getName());
        holder.rbSocre.setStar(bean.getScore());
        holder.rbSocre.setClickable(false);
        holder.tvDis.setText(bean.getDistance());
        holder.tvCentername.setText(bean.getMall_name());
        holder.tvType.setText(bean.getCategory_name());
        holder.tvSeal.setText(bean.getSales());
        if (bean.getMall_name().equals("")) {
            holder.tvCentername.setVisibility(View.GONE);
        } else {
            holder.tvCentername.setVisibility(View.VISIBLE);
        }
        if (bean.getCategory_name().equals("")) {
            holder.tvType.setVisibility(View.GONE);
        } else {
            holder.tvType.setVisibility(View.VISIBLE);
        }
        if (bean.getSales().equals("")) {
            holder.tvSeal.setVisibility(View.INVISIBLE);
        } else {
            holder.tvSeal.setVisibility(View.VISIBLE);
        }

//        final HomeShopActAdapter actAdapter = new HomeShopActAdapter(mContext, bean.getMerchant_promotion_list());
//        holder.lv.setAdapter(actAdapter);
//        actAdapter.notifyDataSetChanged();

        holder.llAct.removeAllViews();
        for(HomePageBean.DataBean.MerchantsBean.MerchantPromotionListBean b :bean.getMerchant_promotion_list()){
            View mView = mInflater.inflate(R.layout.item_home_shopact, null);
            TextView tvstr = mView.findViewById(R.id.tv_str);
            tvstr.setText(b.getContent());
            Glide.with(mContext).load(b.getIco()).into((ImageView)mView.findViewById(R.id.iv_icon));
            holder.llAct.addView(mView);
        }

        return convertView;

    }


    static public class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

