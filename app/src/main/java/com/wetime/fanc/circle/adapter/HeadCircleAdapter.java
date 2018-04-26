package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.utils.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HeadCircleAdapter extends RecyclerView.Adapter {

    private int type;
    private List<CircleHomeListBean.DataBean.CirclesBean> mlist = new ArrayList<>();
    private List<CircleHomeListBean.DataBean.FollowCirclesBean> follow_circles = new ArrayList<>();
    private Context mContext;
    private RequestOptions myOptions;
    private OnItemClickLitener mOnItemClickLitener;

    public HeadCircleAdapter(List<CircleHomeListBean.DataBean.FollowCirclesBean> follow_circles, List<CircleHomeListBean.DataBean.CirclesBean> list, Context mContext, int type) {
        this.mlist = list;
        this.follow_circles = follow_circles;
        this.mContext = mContext;
        this.type = type;
        myOptions = new RequestOptions().transform(new GlideRoundTransform(mContext, 5));
    }

    public List<CircleHomeListBean.DataBean.FollowCirclesBean> getFollow_circles() {
        return follow_circles;
    }

    public void setFollow_circles(List<CircleHomeListBean.DataBean.FollowCirclesBean> follow_circles) {

        this.follow_circles.clear();
        this.follow_circles.addAll(follow_circles);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_circle, parent, false);
        if (type == 0) {
            return new ViewHolder(inflate);
        } else {
            ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
            layoutParams.width = 160;
            inflate.setLayoutParams(layoutParams);
            return new ViewHolder(inflate);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder oholder = (ViewHolder) holder;
        if (type == 0) {
            CircleHomeListBean.DataBean.CirclesBean bean = mlist.get(position);
            ((ViewHolder) holder).tvname.setText(bean.getName());
            Glide.with(mContext)
                    .load(bean.getCover())
                    .apply(myOptions)
                    .into(oholder.ivcover);
        } else {
            ((ViewHolder) holder).tvname.setText(follow_circles.get(position).getName());
            Glide.with(mContext)
                    .load(follow_circles.get(position).getCover())
                    .apply(myOptions)
                    .into(oholder.ivcover);
        }

        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
    }

    @Override
    public int getItemCount() {
        if (type == 0) {
            return mlist.size();
        } else {
            return follow_circles.size();
        }

    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvname;
        @BindView(R.id.iv_cover)
        ImageView ivcover;
        @BindView(R.id.iv_user)
        ImageView ivuser;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
