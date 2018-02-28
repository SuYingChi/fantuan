package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wetime.fanc.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class TestAdapter extends RecyclerView.Adapter {

    private Context mActivity;
    private int page = 1;

    public TestAdapter(Context mActivity) {
        this.mActivity = mActivity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_test, parent, false));
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
        oholder.tvWrod.setText(position + "");
        if (mOnItemClickLitener != null) {
            oholder.tvWrod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return page * 10;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_word)
        TextView tvWrod;


        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void reFresh() {
        page = 1;
    }

    public void loadMore() {
        page++;
    }
}
