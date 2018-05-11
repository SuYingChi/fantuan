package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.AllCircleListBean;
import com.wetime.fanc.circle.presenter.FocusCirclePresenter;
import com.wetime.fanc.utils.AttentionCircleTool;
import com.wetime.fanc.utils.GlideRoundTransform;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class AllCircleAdapter extends RecyclerView.Adapter {

    private List<AllCircleListBean.DataBean.ListBean> mData;
    private Context mContext;
    private RequestOptions myOptions;
    private FocusCirclePresenter focusCirclePresenter;
    ;


    public AllCircleAdapter(Context context, List<AllCircleListBean.DataBean.ListBean> mData) {
        this.mData = mData;
        mContext = context;
        myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
        focusCirclePresenter = new FocusCirclePresenter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_allcircle, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder oholder = (ViewHolder) holder;
        AllCircleListBean.DataBean.ListBean bean = mData.get(holder.getAdapterPosition());
        oholder.tvName.setText(bean.getName());
        oholder.tvDes.setText(bean.getIntro());
        Glide.with(mContext)
                .load(bean.getCover().getCompress())
                .apply(myOptions)
                .into(oholder.ivCover);
        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, holder.getAdapterPosition()));
        }
        if (bean.isFollowed()) {
            ((ViewHolder) holder).tvFocus.setText("已关注");
            ((ViewHolder) holder).tvFocus.setTextColor(Color.parseColor("#B9B9B9"));
        } else {
            ((ViewHolder) holder).tvFocus.setText("+关注");
            ((ViewHolder) holder).tvFocus.setTextColor(Color.parseColor("#1EB0FD"));
            ((ViewHolder) holder).llFocus.setOnClickListener(v -> {
                bean.setFollowed(true);
                bean.setFollowing_num(bean.getFollowing_num() + 1);
                notifyItemChanged(position);
                if (mContext != null)
                    focusCirclePresenter.focusCircle(mContext,
                            Tools.getSpu(mContext).getToken(), "1", bean.getId());
            });
        }

        ((ViewHolder) holder).tvFocusnum.setText(String.format("%d人关注", bean.getFollowing_num()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

