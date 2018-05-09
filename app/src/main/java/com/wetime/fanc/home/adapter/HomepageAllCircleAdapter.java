package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.home.bean.HomepageAllCirclesBean;
import com.wetime.fanc.utils.AttentionCircleTool;
import com.wetime.fanc.utils.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class HomepageAllCircleAdapter extends RecyclerView.Adapter {
    private List<HomepageAllCirclesBean.DataBean.ListBean> listBeans;
    private Context mContext;
    private RequestOptions myOptions;


    public HomepageAllCircleAdapter(Context context, List<HomepageAllCirclesBean.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        mContext = context;
        myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotCircleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_page_fragment_circle_hot_circles, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        {
                Glide.with(mContext).load(listBeans.get(holder.getAdapterPosition()).getCover().getCompress()).apply(myOptions).into(((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleCover);
                if(listBeans.get(holder.getAdapterPosition()).isFollowed()){
                    ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                    ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setText("已关注");
                    ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setTextColor(ContextCompat.getColor(mContext, R.color.textcolor));
                }else {
                    ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setText("+关注");
                    ((HomepageAllCircleAdapter.HotCircleViewHolder)holder).hotCircleAttentCircleBtn.setBackgroundResource(R.drawable.bg_btn_blue_circle);
                    ((HomepageAllCircleAdapter.HotCircleViewHolder)holder).hotCircleAttentCircleBtn.setTextColor(ContextCompat.getColor(mContext,R.color.text_blue));
                    ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AttentionCircleTool.attentionCircle((Activity) mContext, listBeans.get(holder.getAdapterPosition() ).getId(), new AttentionCircleTool.OnAttentionCircleListener() {
                                @Override
                                public void onAttentionCircleResponse(boolean isSuccess) {
                                    if (isSuccess) {
                                        ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                                        ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setText("已关注");
                                        ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleBtn.setTextColor(ContextCompat.getColor(mContext, R.color.textcolor));
                                    }
                                }
                            });
                        }
                    });
                }
            ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleCover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goCircle = new Intent(mContext, CircleDetailActivity.class);
                        goCircle.putExtra("id", listBeans.get(holder.getAdapterPosition()).getId());
                        mContext.startActivity(goCircle);
                    }
                });
            ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleAttentCircleNum.setText(listBeans.get(holder.getAdapterPosition()).getFollowing_num() + "人关注");
            ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleBrief.setText(listBeans.get(holder.getAdapterPosition()).getIntro());
            ((HomepageAllCircleAdapter.HotCircleViewHolder) holder).hotCircleName.setText(listBeans.get(holder.getAdapterPosition()).getName());

            }

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
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
            ButterKnife.bind(this, inflate);
        }
    }
}

