package com.wetime.fanc.news.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.news.bean.FocusTitleBean;
import com.wetime.fanc.news.bean.RecomentFocusUserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class FcousUserAdapter extends RecyclerView.Adapter {


    private List<RecomentFocusUserBean> list;
    private Context mContext;

    public FcousUserAdapter(List<RecomentFocusUserBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_focus_user, parent, false));
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }





    public interface OnFocusClickLitener {
        void onItemClick(View view, int position);
    }


    private OnFocusClickLitener mOnFocusClickLitener;


    public void setOnFocusClickLitener(OnFocusClickLitener mOnItemClickLitener) {
        this.mOnFocusClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderViewHolder oholder = (OrderViewHolder) holder;
        RecomentFocusUserBean bean = list.get(position);
        Glide.with(mContext).load(bean.getAvatar()).into(((OrderViewHolder) holder).ivHead);
        ((OrderViewHolder) holder).tvName.setText(bean.getUsername());
        ((OrderViewHolder) holder).tvDes.setText(bean.getIntro());
        if(bean.isIs_follow()){

        }else{

        }
        if (bean.isIs_follow()) {
            ((OrderViewHolder) holder).tvFocus.setText("已关注");
            ((OrderViewHolder) holder).tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
            ((OrderViewHolder) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_gray_circle);
        } else {
            ((OrderViewHolder) holder).tvFocus.setText("关注");
            ((OrderViewHolder) holder).tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            ((OrderViewHolder) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_red_circle);
        }


        if (mOnFocusClickLitener != null) {
            ((OrderViewHolder) holder).tvFocus.setOnClickListener(view -> mOnFocusClickLitener.onItemClick(view, position));
        }
        if (mOnItemClickLitener != null) {
            ((OrderViewHolder) holder).itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_focus)
        TextView tvFocus;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
