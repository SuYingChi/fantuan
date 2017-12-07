package com.wetime.fanc.shopcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;
import com.wetime.fanc.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class ShopListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ShopCenterPageBean.DataBean.ListBean> mData;
    private Context mContext;

    public ShopListAdapter(Context context, List<ShopCenterPageBean.DataBean.ListBean> mData) {
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
    public ShopCenterPageBean.DataBean.ListBean getItem(int arg0) {
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

            convertView = mInflater.inflate(R.layout.item_shopcenter_shop, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShopCenterPageBean.DataBean.ListBean bean = mData.get(position);
        Glide.with(mContext).load(bean.getLogo()).into(holder.civHead);
        holder.tvName.setText(bean.getName());
        holder.tvDis.setText(bean.getDistance());

        Glide.with(mContext).load(bean.getMerchants().get(0).getLogo()).into(holder.iv1);
        Glide.with(mContext).load(bean.getMerchants().get(1).getLogo()).into(holder.iv2);
        Glide.with(mContext).load(bean.getMerchants().get(2).getLogo()).into(holder.iv3);

        holder.tvShopname1.setText(bean.getMerchants().get(0).getName());
        holder.tvShopname2.setText(bean.getMerchants().get(1).getName());
        holder.tvShopname3.setText(bean.getMerchants().get(2).getName());
        if(TextUtils.isEmpty(bean.getMerchants().get(0).getTotal())){
            holder.tvTotal1.setVisibility(View.INVISIBLE);
        }else{
            holder.tvTotal1.setVisibility(View.VISIBLE);
            holder.tvTotal1.setText(bean.getMerchants().get(0).getTotal());
        }

        if(TextUtils.isEmpty(bean.getMerchants().get(1).getTotal())){
            holder.tvTotal2.setVisibility(View.INVISIBLE);
        }else{
            holder.tvTotal2.setVisibility(View.VISIBLE);
            holder.tvTotal2.setText(bean.getMerchants().get(1).getTotal());
        }


        if(TextUtils.isEmpty(bean.getMerchants().get(2).getTotal())){
            holder.tvTotal3.setVisibility(View.INVISIBLE);
        }else{
            holder.tvTotal3.setVisibility(View.VISIBLE);
            holder.tvTotal3.setText(bean.getMerchants().get(2).getTotal());
        }

        holder.tvNum.setText(bean.getNum());
        holder.tvGoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", bean.getMall_list_url());
                mContext.startActivity(goweb);
            }
        });
        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", bean.getMerchants().get(0).getDetail_url());
                mContext.startActivity(goweb);
            }
        });
        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", bean.getMerchants().get(1).getDetail_url());
                mContext.startActivity(goweb);
            }
        });
        holder.iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goweb = new Intent(mContext, WebActivity.class);
                goweb.putExtra("url", bean.getMerchants().get(2).getDetail_url());
                mContext.startActivity(goweb);
            }
        });

        return convertView;

    }


    public class ViewHolder {
        @BindView(R.id.civ_head)
        CircleImageView civHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dis)
        TextView tvDis;
        @BindView(R.id.iv_1)
        ImageView iv1;
        @BindView(R.id.tv_total1)
        TextView tvTotal1;
        @BindView(R.id.tv_shopname1)
        TextView tvShopname1;
        @BindView(R.id.iv_2)
        ImageView iv2;
        @BindView(R.id.tv_total2)
        TextView tvTotal2;
        @BindView(R.id.tv_shopname2)
        TextView tvShopname2;
        @BindView(R.id.iv_3)
        ImageView iv3;
        @BindView(R.id.tv_total3)
        TextView tvTotal3;
        @BindView(R.id.tv_shopname3)
        TextView tvShopname3;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_goshop)
        TextView tvGoshop;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

