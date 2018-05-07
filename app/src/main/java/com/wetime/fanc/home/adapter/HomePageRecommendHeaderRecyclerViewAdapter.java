package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.utils.AttentionCircleTool;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/6.
 */

class HomePageRecommendHeaderRecyclerViewAdapter extends RecyclerView.Adapter  {

    private final Activity activity;
    private final LayoutInflater inflate;
    private  List<HomePageRecommendBean.DataBean.CirclesBean> circles;
    private OnItemClickLitener onItemClickLitener;

    public HomePageRecommendHeaderRecyclerViewAdapter(List<HomePageRecommendBean.DataBean.CirclesBean> circles, Activity activity) {
        this.circles = circles;
        this.activity = activity;
        this.inflate = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflate.inflate(R.layout.item_recommend_circle, parent, false);
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = ((Tools.getScreenW(activity) - Tools.dip2px(activity, 10 + 10)) / 4);
        itemView.setLayoutParams(layoutParams);
        return new RecommendHeaderViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendHeaderViewHolder recommendHeaderViewHolder = (RecommendHeaderViewHolder)holder;
        HomePageRecommendBean.DataBean.CirclesBean circleBean = circles.get(position);
        Glide.with(activity).load(circles.get(position).getCover().getCompress())
                .apply(new RequestOptions().centerCrop())
                .into(recommendHeaderViewHolder.circleImage);
        recommendHeaderViewHolder.circleName.setText(circles.get(position).getName());
        recommendHeaderViewHolder.textView.setText(circles.get(position).getNum());
        recommendHeaderViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Tools.getSpu(activity).getToken())) {
                    Tools.toastInBottom(activity, "请先登录");
                    Intent goLogin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(goLogin);
                } else {
                    AttentionCircleTool.attentionCircle(activity, circles.get(position).getId(), new AttentionCircleTool.OnAttentionCircleListener() {
                        @Override
                        public void onAttentionCircleResponse(boolean isSuccess) {
                            if (isSuccess){
                                recommendHeaderViewHolder.button.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return circles.size();
    }

    public void setRecommendList(List<HomePageRecommendBean.DataBean.CirclesBean> recommendList) {
        this.circles = recommendList;
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
            ButterKnife.bind(this,inflate);
        }
    }
   void  setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
       this.onItemClickLitener = onItemClickLitener;
    }
    public interface OnItemClickLitener{
       void onclick(View view, int position);
    }
}

