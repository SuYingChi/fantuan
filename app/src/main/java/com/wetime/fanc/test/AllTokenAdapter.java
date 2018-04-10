package com.wetime.fanc.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wetime.fanc.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zhoukang on 2017/4/17.
 */

public class AllTokenAdapter extends RecyclerView.Adapter {
    private String[] mData;
    private String[] mToken;
    private Context mContext;


    public AllTokenAdapter(Context context) {
        this.mData = context.getResources().getStringArray(R.array.name);
        this.mToken = context.getResources().getStringArray(R.array.token);
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_alltoken, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder oholder = (ViewHolder) holder;

        oholder.tvName.setText(mData[position]);

        oholder.tvDes.setText(mToken[position]);
        if (mOnItemClickLitener != null) {
            oholder.itemView.setOnClickListener(v -> mOnItemClickLitener.onItemClick(v, position));

        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_des)
        TextView tvDes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

