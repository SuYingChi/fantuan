package com.wetime.fanc.home.adapter;

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
import com.wetime.fanc.home.bean.NewsListBean;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 *
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsListBean.DataBean.ListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public NewsAdapter(List<NewsListBean.DataBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new NewsHolder0(inflater.inflate(R.layout.item_news_type0, parent, false));
        } else if (viewType == 1) {
            return new NewsHolder1(inflater.inflate(R.layout.item_news_type1, parent, false));
        } else if (viewType == 3) {
            return new NewsHolder3(inflater.inflate(R.layout.item_news_type3, parent, false));
        } else {
            return null;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsListBean.DataBean.ListBean bean = list.get(position);

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
        if (holder instanceof NewsHolder0) {
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15));
            int h = Tools.dip2px(mContext, 80);

            Glide.with(mContext).load(bean.getBanner().get(0).getImg()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small)).into(((NewsHolder0) holder).ivBanner);
        }
        if (holder instanceof NewsHolder1) {
            ((NewsHolder1) holder).tvName.setText(bean.getName());
            ((NewsHolder1) holder).tvAuthor.setText(bean.getAuthor());
            ((NewsHolder1) holder).tvTime.setText(bean.getTime());
            ((NewsHolder1) holder).tvReadnum.setText(bean.getRead_num());
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;
            int h = Tools.dip2px(mContext, 80);
            Glide.with(mContext).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))

                    .into(((NewsHolder1) holder).ivCover);
        }
        if (holder instanceof NewsHolder3) {
            ((NewsHolder3) holder).tvName.setText(bean.getName());
            ((NewsHolder3) holder).tvAuthor.setText(bean.getAuthor());
            ((NewsHolder3) holder).tvTime.setText(bean.getTime());
            ((NewsHolder3) holder).tvReadnum.setText(bean.getRead_num());

            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;
            int h = Tools.dip2px(mContext, 80);

            Glide.with(mContext).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover0);

            Glide.with(mContext).load(bean.getCover().get(1)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover1);

            Glide.with(mContext).load(bean.getCover().get(2)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover2);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    // 一张小图
    class NewsHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;


        NewsHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 小图加文字
    class NewsHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_readnum)
        TextView tvReadnum;
        @BindView(R.id.iv_cover)
        ImageView ivCover;


        NewsHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_readnum)
        TextView tvReadnum;
        @BindView(R.id.iv_cover0)
        ImageView ivCover0;
        @BindView(R.id.iv_cover1)
        ImageView ivCover1;
        @BindView(R.id.iv_cover2)
        ImageView ivCover2;


        NewsHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
