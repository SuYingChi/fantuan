package com.wetime.fanc.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.customview.GridViewForScrollView;
import com.wetime.fanc.shop.bean.ShopActListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class ShopSayActAdapter extends RecyclerView.Adapter {


    private List<ShopActListBean.DataBean.ListBean> list;
    private Context mContext;

    public ShopSayActAdapter(List<ShopActListBean.DataBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shopsay_act, parent, false));
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
        final OrderViewHolder oholder = (OrderViewHolder) holder;
        ShopActListBean.DataBean.ListBean bean = list.get(position);
        oholder.tvTime.setText(bean.getCreate_at());
        oholder.tvContent.setText(bean.getContent());

        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
        ImageGridListAdapter adapter = new ImageGridListAdapter(mContext, list.get(position).getImageUrl());
        oholder.gv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        GridViewForScrollView gv;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
