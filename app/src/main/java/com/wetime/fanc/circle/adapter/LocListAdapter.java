package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.LocItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class LocListAdapter extends RecyclerView.Adapter {
    private List<LocItemBean> mData;
    private Context mContext;


    public LocListAdapter(Context context, List<LocItemBean> mData) {
        this.mData = mData;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_loclist, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder oholder = (ViewHolder) holder;
        LocItemBean bean = mData.get(holder.getAdapterPosition());
        oholder.tvAdd.setText(bean.getAddress());
        oholder.tvTitle.setText(bean.getTitle());
        if (TextUtils.isEmpty(bean.getTitle())) {
            oholder.tvNoLoc.setVisibility(View.VISIBLE);
            oholder.tvAdd.setVisibility(View.GONE);
            oholder.tvTitle.setVisibility(View.GONE);
        } else {
            oholder.tvNoLoc.setVisibility(View.GONE);
            oholder.tvAdd.setVisibility(View.VISIBLE);
            oholder.tvTitle.setVisibility(View.VISIBLE);
        }

        if (bean.isSelected()) {
            oholder.ivCheck.setVisibility(View.VISIBLE);
        } else {
            oholder.ivCheck.setVisibility(View.GONE);
        }
        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, holder.getAdapterPosition()));
        }
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
        @BindView(R.id.tv_noloc)
        TextView tvNoLoc;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_addres)
        TextView tvAdd;
        @BindView(R.id.iv_check)
        ImageView ivCheck;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

