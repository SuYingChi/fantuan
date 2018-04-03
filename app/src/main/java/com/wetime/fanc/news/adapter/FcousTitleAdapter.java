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

import com.wetime.fanc.R;
import com.wetime.fanc.news.bean.FocusTitleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class FcousTitleAdapter extends RecyclerView.Adapter {

    private List<FocusTitleBean> list;
    private Context mContext;
    private int selected = 0;

    public FcousTitleAdapter(List<FocusTitleBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }
    public void setSelected(int pos){
        selected = pos;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_focus_title, parent, false));
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
        FocusTitleBean bean = list.get(position);
        if (selected==position) {
            ((OrderViewHolder) holder).llBg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            ((OrderViewHolder) holder).ivRed.setVisibility(View.VISIBLE);
        } else {
            ((OrderViewHolder) holder).llBg.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_all));
            ((OrderViewHolder) holder).ivRed.setVisibility(View.INVISIBLE);
        }
        ((OrderViewHolder) holder).tvTitle.setText(bean.getName());


        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_bg)
        LinearLayout llBg;
        @BindView(R.id.iv_redline)
        ImageView ivRed;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
