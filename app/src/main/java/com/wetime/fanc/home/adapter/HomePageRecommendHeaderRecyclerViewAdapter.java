package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.utils.AttentionCircleTool;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/6.
 */

class HomePageRecommendHeaderRecyclerViewAdapter extends RecyclerView.Adapter {

    private  Activity activity;
    private  LayoutInflater inflate;
    private List<HomePageRecommendBean.DataBean.CirclesBean> circles;


    public HomePageRecommendHeaderRecyclerViewAdapter(List<HomePageRecommendBean.DataBean.CirclesBean> circles, Activity activity) {
        this.circles= circles;
        this.activity = activity;
        this.inflate = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflate.inflate(R.layout.item_recommend_circle, parent, false);
        return new RecommendHeaderViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RecommendHeaderViewHolder) {
            Glide.with(activity).load(circles.get(holder.getAdapterPosition()).getCover().getCompress())
                    .apply(new RequestOptions().centerCrop())
                    .into(((RecommendHeaderViewHolder)holder).circleImage);
            ((RecommendHeaderViewHolder)holder).circleName.setText(circles.get(holder.getAdapterPosition()).getName());
            ((RecommendHeaderViewHolder)holder).textView.setText(circles.get(holder.getAdapterPosition()).getNum()+"人关注");
            ((RecommendHeaderViewHolder)holder).button.setBackgroundResource(R.drawable.bg_btn_blue_circle);
            ((RecommendHeaderViewHolder)holder).button.setTextColor(ContextCompat.getColor(activity,R.color.text_blue));
            ((RecommendHeaderViewHolder)holder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(Tools.getSpu(activity).getToken())) {
                        Tools.toastInBottom(activity, "请先登录");
                        Intent goLogin = new Intent(activity, LoginActivity.class);
                        activity.startActivity(goLogin);
                    } else {
                        AttentionCircleTool.attentionCircle(activity, circles.get(holder.getAdapterPosition()).getId(), new AttentionCircleTool.OnAttentionCircleListener() {
                            @Override
                            public void onAttentionCircleResponse(boolean isSuccess) {
                                if (isSuccess) {
                                    ((RecommendHeaderViewHolder)holder).button.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                                    ((RecommendHeaderViewHolder)holder).button.setTextColor(ContextCompat.getColor(activity,R.color.agg_text_color_bbb));
                                }
                            }
                        });
                    }
                }
            });

            ((RecommendHeaderViewHolder)holder).circleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (circles.get(position).getId().equals("0")) {
                        Intent goAll = new Intent(activity, AllCircleActivity.class);
                        activity.startActivity(goAll);
                    } else {
                        Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                        goCircle.putExtra("id", circles.get(position).getId());
                        activity.startActivity(goCircle);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return circles.size();
    }

    public static class RecommendHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_image)
        ImageView circleImage;
        @BindView(R.id.recommend_circle_name)
        TextView circleName;
        @BindView(R.id.recommend_circle_attention_num)
        TextView textView;
        @BindView(R.id.is_attention)
        Button button;


        public RecommendHeaderViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }

    public interface OnItemClickLitener {
        void onclick(View view, int position);
    }
}

