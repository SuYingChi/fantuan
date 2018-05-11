package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.presenter.FocusCirclePresenter;
import com.wetime.fanc.home.bean.HomeMyCircleBean;
import com.wetime.fanc.utils.GlideRoundTransform;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HomeMyCircleAdapter extends RecyclerView.Adapter {


    private RequestOptions myOptions;
    private HomeMyCircleBean data;
    private Context mContext;
    private FocusCirclePresenter focusCirclePresenter;

    public HomeMyCircleAdapter(HomeMyCircleBean data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
        focusCirclePresenter = new FocusCirclePresenter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new Title1ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_mycircle_title1, parent, false));
        } else if (viewType == 1) {
            return new CircleType1ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_mycircle_type1, parent, false));
        } else if (viewType == 2) {
            return new Title2ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_mycircle_title2, parent, false));
        } else {
            return new CircleType3ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_mycircle_type3, parent, false));
        }
    }

//    public interface OnItemClickLitener {
//        void onItemClick(View view, int position);
//    }
//
//
//    private OnItemClickLitener mOnItemClickLitener;
//
//
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof Title1ViewHolder) {
            if (data.getData().getList().size() == 0) {
                ((Title1ViewHolder) holder).tvEmpty.setVisibility(View.VISIBLE);
            } else {
                ((Title1ViewHolder) holder).tvEmpty.setVisibility(View.GONE);
            }

        } else if (holder instanceof CircleType1ViewHolder) {
            HomeMyCircleBean.DataBean.ListBean bean = data.getData().getList().get(position - 1);
            Glide.with(mContext)
                    .load(bean.getCover().getCompress())
                    .apply(myOptions)
                    .into(((CircleType1ViewHolder) holder).ivCover);
            ((CircleType1ViewHolder) holder).tvName.setText(bean.getName());
            ((CircleType1ViewHolder) holder).tvTodaynum.setText(String.format("今日：%s", bean.getToday_num()));
        } else if (holder instanceof Title2ViewHolder) {
            ((Title2ViewHolder) holder).tvMore.setOnClickListener(v -> AllCircleActivity.goAllCircleAct(mContext));
        } else if (holder instanceof CircleType3ViewHolder) {
            CircleType3ViewHolder mHoler = (CircleType3ViewHolder) holder;
            HomeMyCircleBean.DataBean.NotmissBean bean = data.getData().getNotmiss().get(position - data.getData().getList().size() - 2);
            mHoler.tvName.setText(bean.getName());
            mHoler.tvDes.setText(bean.getIntro());
            Glide.with(mContext)
                    .load(bean.getCover().getCompress())
                    .apply(myOptions)
                    .into(mHoler.ivCover);

            mHoler.tvFocus.setText("+关注");
            mHoler.tvFocus.setTextColor(Color.parseColor("#1EB0FD"));

            mHoler.llFocus.setOnClickListener(v -> {
                bean.setFollowing_num(bean.getFollowing_num() + 1);
                mHoler.tvFocusnum.setText(String.format("%d人关注", bean.getFollowing_num()));
                mHoler.tvFocus.setText("已关注");
                mHoler.tvFocus.setTextColor(Color.parseColor("#B9B9B9"));
                if (mContext != null)
                    focusCirclePresenter.focusCircle(mContext,
                            Tools.getSpu(mContext).getToken(), "1", bean.getId());
            });


            mHoler.tvFocusnum.setText(String.format("%d人关注", bean.getFollowing_num()));

        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;//0标题1
        } else if (position > 0 && position < data.getData().getList().size() + 1) {
            return 1;//我的圈子
        } else if (position == data.getData().getList().size() + 1) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public int getItemCount() {
        return data.getData().getNotmiss().size() + data.getData().getList().size() + 2;//两个标题的样式
    }

    class Title1ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_empty)
        TextView tvEmpty;


        Title1ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class CircleType1ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_todaynum)
        TextView tvTodaynum;
        @BindView(R.id.iv_cover)
        ImageView ivCover;


        CircleType1ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class CircleType3ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_focus)
        TextView tvFocus;
        @BindView(R.id.tv_focusnum)
        TextView tvFocusnum;
        @BindView(R.id.ll_focus)
        LinearLayout llFocus;


        CircleType3ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class Title2ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_more)
        TextView tvMore;


        Title2ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
