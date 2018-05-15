package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.wetime.fanc.circle.presenter.FocusCirclePresenter;
import com.wetime.fanc.home.bean.HomeRecListBeanV2;
import com.wetime.fanc.utils.GlideRoundTransform;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HomeRecCircleAdapterV2 extends RecyclerView.Adapter {

    private List<HomeRecListBeanV2.DataBean.CirclesBean> list;
    private Context mActivity;
    private RequestOptions myOptions;
    private FocusCirclePresenter focusCirclePresenter;

    public HomeRecCircleAdapterV2(List<HomeRecListBeanV2.DataBean.CirclesBean> list, Context mActivity) {
        this.list = list;
        this.mActivity = mActivity;
        myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mActivity, 5));
        focusCirclePresenter = new FocusCirclePresenter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_home_rec_circle, parent, false));
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
            goCircle.putExtra("id", list.get(position).getId());
            mActivity.startActivity(goCircle);
        });
        final OrderViewHolder mHolder = (OrderViewHolder) holder;
        HomeRecListBeanV2.DataBean.CirclesBean bean = list.get(position);
        Glide.with(mActivity).load(bean.getCover().getCompress())
                .apply(myOptions)
                .into(mHolder.ivCover);
        mHolder.tvName.setText(bean.getName());
        mHolder.tvFocusnum.setText(String.format("%s人关注", bean.getNum()));
        mHolder.tvFocus.setOnClickListener(v -> {
            mHolder.tvFocus.setBackgroundResource(R.drawable.bg_dddddd_round_corner2dp);
            mHolder.tvFocus.setTextColor(Color.parseColor("#999999"));
            mHolder.tvFocusnum.setText(String.format("%d人关注", Integer.valueOf(bean.getNum()) + 1));
            focusCirclePresenter.focusCircle(mActivity,
                    Tools.getSpu(mActivity).getToken(), "1", list.get(position).getId());
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_focusnum)
        TextView tvFocusnum;
        @BindView(R.id.tv_focus)
        TextView tvFocus;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
