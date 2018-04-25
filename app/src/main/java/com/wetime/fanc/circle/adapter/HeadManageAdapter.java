package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.CircleDetailBean;
import com.wetime.fanc.utils.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HeadManageAdapter extends RecyclerView.Adapter {

    private int type;
    private List<CircleDetailBean.DataBean.ManagersBean> mlist = new ArrayList<>();
    private Context mContext;
    private RequestOptions myOptions;
    private OnItemClickLitener mOnItemClickLitener;

    public HeadManageAdapter(List<CircleDetailBean.DataBean.ManagersBean> list, Context mContext, int type) {
        this.mlist = list;
        this.mContext = mContext;
        this.type = type;
        myOptions = new RequestOptions().transform(new GlideRoundTransform(mContext, 5));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_circle, parent, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.width = 100;
        inflate.setLayoutParams(layoutParams);
        return new ViewHolder(inflate);

    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder oholder = (ViewHolder) holder;
        CircleDetailBean.DataBean.ManagersBean bean = mlist.get(position);

        ((ViewHolder) holder).tvname.setText(bean.getUsername());

        if (!TextUtils.isEmpty(bean.getCover())) {
            Glide.with(mContext)
                    .load(bean.getCover())
                    .apply(myOptions)
                    .into(oholder.ivuser);
        }

        ((ViewHolder) holder).ivuser.setVisibility(View.VISIBLE);
        ((ViewHolder) holder).ivcover.setVisibility(View.GONE);

        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
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
