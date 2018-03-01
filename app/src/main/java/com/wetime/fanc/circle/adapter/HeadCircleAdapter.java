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

    private List<CircleHomeListBean.DataBean.CirclesBean> mlist = new ArrayList<>();
    private Context mContext;
    private RequestOptions myOptions;

    public HeadCircleAdapter(List<CircleHomeListBean.DataBean.CirclesBean> list, Context mContext) {
        this.mlist = list;
        this.mContext = mContext;
        myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, 5));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_circle, parent, false));
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
        final ViewHolder oholder = (ViewHolder) holder;
        CircleHomeListBean.DataBean.CirclesBean bean = mlist.get(position);

        ((ViewHolder) holder).tvname.setText(bean.getName());
        Glide.with(mContext)
                .load(bean.getCover())
                .apply(myOptions)
                .into(oholder.ivcover);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvname;
        @BindView(R.id.iv_cover)
        ImageView ivcover;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
