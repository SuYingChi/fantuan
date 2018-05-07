package com.wetime.fanc.home.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.home.bean.HomePageCircleBean;
import com.wetime.fanc.utils.AttentionCircleTool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/7.
 */

public class HomePageCirclesAdapter extends RecyclerView.Adapter {
    private  List<HomePageCircleBean.DataBean.NotmissBean> hotCircles;
    private  List<HomePageCircleBean.DataBean.ListBean> myCircles;
    private  LayoutInflater inflater;
    private  FragmentActivity activity;
    private HomePageMyCircleAdapter homePageMyCircleAdapter;

    public HomePageCirclesAdapter(List<HomePageCircleBean.DataBean.NotmissBean> hotCircles, List<HomePageCircleBean.DataBean.ListBean> myCircles, FragmentActivity activity) {
        this.hotCircles = hotCircles;
        this.myCircles = myCircles;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType == -1) {
            return new CircleHeaderViewHolder(inflater.inflate(R.layout.frgament__home_page_circle_header, parent, false));
        } else  {
            return new HotCircleViewHolder(inflater.inflate(R.layout.item_home_page_fragment_circle, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     if(holder instanceof CircleHeaderViewHolder){
         if(homePageMyCircleAdapter == null) {
             LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
             linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
             ((CircleHeaderViewHolder) holder).rcl_circle_my.setLayoutManager(linearLayoutManager);
             homePageMyCircleAdapter = new HomePageMyCircleAdapter(myCircles, activity);
             if(myCircles.size()<=0){
                 ((CircleHeaderViewHolder) holder).emptyMyCircle.setVisibility(View.VISIBLE);
             }else{
                 ((CircleHeaderViewHolder) holder).emptyMyCircle.setVisibility(View.GONE);
             }
             ((CircleHeaderViewHolder) holder).rcl_circle_my.setAdapter(homePageMyCircleAdapter);
             homePageMyCircleAdapter.setOnItemClickListener((view, position2) -> {
                     Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                     goCircle.putExtra("id", myCircles.get(position2).getId());
                     activity.startActivity(goCircle);
             });
             ((CircleHeaderViewHolder) holder).moreCircles.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent goAll = new Intent(activity, AllCircleActivity.class);
                     activity.startActivity(goAll);
                 }
             });
         }else {
             homePageMyCircleAdapter.notifyDataSetChanged();
         }
         if(hotCircles.size()==0){
             ((CircleHeaderViewHolder) holder).relativeLayout.setVisibility(View.GONE);
         }
     }else if(holder instanceof HotCircleViewHolder) {
         if(hotCircles.size()>0) {
             Glide.with(activity).load(hotCircles.get(holder.getAdapterPosition() - 1).getCover().getCompress()).into(((HotCircleViewHolder) holder).hotCircleCover);
             ((HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     AttentionCircleTool.attentionCircle(activity, hotCircles.get(holder.getAdapterPosition() - 1).getId(), new AttentionCircleTool.OnAttentionCircleListener() {
                         @Override
                         public void onAttentionCircleResponse(boolean isSuccess) {
                             if (isSuccess) {
                                 ((HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setText("已关注");
                                 ((HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setTextColor(ContextCompat.getColor(activity, R.color.textcolor));
                             }
                         }
                     });
                 }
             });
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                     goCircle.putExtra("id", hotCircles.get(holder.getAdapterPosition() - 1).getId());
                     activity.startActivity(goCircle);
                 }
             });
             ((HotCircleViewHolder) holder).hotCircleAttentCircleNum.setText(hotCircles.get(holder.getAdapterPosition() - 1).getFollowing_num());
             ((HotCircleViewHolder) holder).hotCircleBrief.setText(hotCircles.get(holder.getAdapterPosition() - 1).getIntro());
             ((HotCircleViewHolder) holder).hotCircleName.setText(hotCircles.get(holder.getAdapterPosition() - 1).getName());

         }
     }
    }

    @Override
    public int getItemCount() {
        return hotCircles.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return -1;
        else return 1;
    }

    public static class CircleHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rcl_circle_my)
        RecyclerView rcl_circle_my;
        @BindView(R.id.more_circles)
        TextView moreCircles;
        @BindView(R.id.rcl_circle_tv)
        TextView emptyMyCircle;
        @BindView(R.id.recommend_circles)
        RelativeLayout relativeLayout;
        public CircleHeaderViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }
    }

    public static class HotCircleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_circle_attent_circle_btn)
        TextView hotCircleAttentCircleBtn;
        @BindView(R.id.hot_circle_attent_circle_num)
        TextView hotCircleAttentCircleNum;
        @BindView(R.id.hot_circle_brief)
        TextView hotCircleBrief;
        @BindView(R.id.hot_circle_cover)
        ImageView hotCircleCover;
        @BindView(R.id.hot_circle_name)
        TextView hotCircleName;

        public HotCircleViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }
    }
}
