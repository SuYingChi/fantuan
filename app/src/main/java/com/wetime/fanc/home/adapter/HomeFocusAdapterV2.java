package com.wetime.fanc.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.presenter.FocusCirclePresenter;
import com.wetime.fanc.home.bean.HomeItemBeanV2;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HomeFocusAdapterV2 extends RecyclerView.Adapter {


    private List<HomeItemBeanV2> list;
    private Context mContext;
    private FocusCirclePresenter focusCirclePresenter;

    public HomeFocusAdapterV2(List<HomeItemBeanV2> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

//    tpye 10=无图 11=1图 14=四图 19=其他 18=长文

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 10 || viewType == 11) {
            return new V10ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_act_type10_v2, parent, false));
        }

        return new NewsHolderTemp(LayoutInflater.from(mContext).inflate(R.layout.item_news_type_temp, parent, false));


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        HomeItemBeanV2 bean = list.get(position);
        if (holder instanceof V10ViewHolder) {
            V10ViewHolder mHolder = (V10ViewHolder) holder;
            Glide.with(mContext).load(bean.getAvatar()).into(mHolder.ivHead);
            mHolder.tvName.setText(bean.getUsername());
            mHolder.tvTime.setText(bean.getTime());
            if (TextUtils.isEmpty(bean.getCircle_name())) {
                mHolder.llHeadCircle.setVisibility(View.GONE);
            } else {
                mHolder.llHeadCircle.setVisibility(View.VISIBLE);
                mHolder.tvCirclename.setText(bean.getCircle_name());
            }
            mHolder.tvContent.setText(bean.getContent());
            if (TextUtils.isEmpty(bean.getLocation())) {
                mHolder.tvLoc.setVisibility(View.GONE);
            } else {
                mHolder.tvLoc.setVisibility(View.VISIBLE);
                mHolder.tvLoc.setText(bean.getLocation());
            }
            if (bean.getLike_num().equals("0")) {
                mHolder.tvZannum.setText("点赞");
            } else {
                mHolder.tvZannum.setText(bean.getLike_num());
            }
            mHolder.tvZannum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools.toastInBottom(mContext,"zan le ");
                }
            });
            if (bean.getComment_num().equals("0")) {
                mHolder.tvCommentNum.setText("评论");
            } else {
                mHolder.tvCommentNum.setText(bean.getComment_num());
            }

        }
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsHolderTemp extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;


        NewsHolderTemp(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class V10ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_loc)
        TextView tvLoc;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentNum;


        V10ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
