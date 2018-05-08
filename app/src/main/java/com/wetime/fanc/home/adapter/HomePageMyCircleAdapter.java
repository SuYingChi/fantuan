package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomePageCircleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/7.
 */

public class HomePageMyCircleAdapter extends RecyclerView.Adapter {

    private List<HomePageCircleBean.DataBean.ListBean> myCircles;
    private Activity activity;
    private LayoutInflater inflater;
    private OnItemClickLitener onItemClickLitener;

    public HomePageMyCircleAdapter(List<HomePageCircleBean.DataBean.ListBean> myCircles, Activity activity) {
        this.myCircles = myCircles;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCircleViewHolder(inflater.inflate(R.layout.item_home_page_fragment_my_circle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLitener.onClick(v, holder.getAdapterPosition());
            }
        });
        if (holder instanceof MyCircleViewHolder) {
            Glide.with(activity).load(myCircles.get(position).getCover().getCompress()).into(((MyCircleViewHolder) holder).myCirclesCover);
            ((MyCircleViewHolder) holder).myCircleContentNum.setText(myCircles.get(position).getToday_num());
            ((MyCircleViewHolder) holder).myCircleName.setText(myCircles.get(position).getName());
        }

    }

    @Override
    public int getItemCount() {
        return myCircles.size();
    }


    interface OnItemClickLitener {
        void onClick(View v, int position);
    }

    void setOnItemClickListener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public static class MyCircleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_circle_content_num)
        TextView myCircleContentNum;
        @BindView(R.id.my_circle_name)
        TextView myCircleName;
        @BindView(R.id.my_circles_cover)
        ImageView myCirclesCover;

        public MyCircleViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }
}
