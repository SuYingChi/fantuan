package com.wetime.fanc.home.adapter;

import android.content.Context;
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
import com.wetime.fanc.customview.GridViewForScrollView;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.shop.adapter.ImageGridListAdapter;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HomeItemAdapter extends RecyclerView.Adapter {
    private List<HomeItemBean> list;
    private Context mContext;
    private LayoutInflater inflater;
    // 区分  0 默认样式  1  特殊 shopnews 带阴影  只有单图
    private int listtype = 0;
    private boolean isBlod;

    public HomeItemAdapter(List<HomeItemBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.isBlod = false;
    }

    public HomeItemAdapter(List<HomeItemBean> list, Context mContext, boolean isBlod) {
        this.list = list;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.isBlod = isBlod;
    }

    public int getListtype() {
        return listtype;
    }

    public void setListtype(int listtype) {
        this.listtype = listtype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (listtype == 0) {
            if (viewType == 0) {
                return new NewsHolder0(inflater.inflate(R.layout.item_news_type0, parent, false));
            } else if (viewType == 1) {
                return new NewsHolder1(inflater.inflate(R.layout.item_news_type1, parent, false));
            } else if (viewType == 3) {
                return new NewsHolder3(inflater.inflate(R.layout.item_news_type3, parent, false));
            } else if (viewType == 4) {
                return new NewsHolder4(inflater.inflate(R.layout.item_news_type4, parent, false));
            } else if (viewType == 10) {
                return new NewsHolder11(inflater.inflate(R.layout.item_news_type11, parent, false));
            } else if (viewType == 11) {
                return new NewsHolder11(inflater.inflate(R.layout.item_news_type11, parent, false));
            } else if (viewType == 19) {
                return new NewsHolder19(inflater.inflate(R.layout.item_news_type19, parent, false));
            }

        } else if (listtype == 1) {
            //商家说  带阴影的 和正常的不一样 特殊处理
            return new NewsHolder1Shop(inflater.inflate(R.layout.item_news_type1_shop, parent, false));
        }
        return new NewsHolderTemp(inflater.inflate(R.layout.item_news_type_temp, parent, false));


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HomeItemBean bean = list.get(position);

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
        }
        if (holder instanceof NewsHolder0) {
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15));
            Double rate = 80.0 / 345;

            int h = (int) (w * rate);

            Glide.with(mContext).load(bean.getBanner().get(0).getImg()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small)).into(((NewsHolder0) holder).ivBanner);
        }
        if (holder instanceof NewsHolder4) {
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15));
            Double rate = 135.0 / 345;

            int h = (int) (w * rate);
            ((NewsHolder4) holder).tvName.setText(bean.getName());
            Glide.with(mContext).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_single_big)).into(((NewsHolder4) holder).ivBanner);
        }
        if (holder instanceof NewsHolder1) {
            if (isBlod) {
                ((NewsHolder1) holder).bigLine.setVisibility(View.VISIBLE);
                ((NewsHolder1) holder).smallLine.setVisibility(View.GONE);
            } else {
                ((NewsHolder1) holder).bigLine.setVisibility(View.GONE);
                ((NewsHolder1) holder).smallLine.setVisibility(View.VISIBLE);
            }

            ((NewsHolder1) holder).tvName.setText(bean.getName());
            ((NewsHolder1) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder1) holder).tvTime.setText(bean.getTime());
            ((NewsHolder1) holder).tvReadnum.setText(bean.getRead_num());
            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder1) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder1) holder).tvAuthor.setVisibility(View.VISIBLE);
            }
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;

            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mContext).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))

                    .into(((NewsHolder1) holder).ivCover);
        }

        if (holder instanceof NewsHolder1Shop) {
            ((NewsHolder1Shop) holder).tvName.setText(bean.getName());
            ((NewsHolder1Shop) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder1Shop) holder).tvTime.setText(bean.getTime());
            ((NewsHolder1Shop) holder).tvReadnum.setText(bean.getRead_num());
            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder1Shop) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder1Shop) holder).tvAuthor.setVisibility(View.VISIBLE);
            }
            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;

            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mContext).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder1Shop) holder).ivCover);
        }
        if (holder instanceof NewsHolder3) {
            if (isBlod) {
                ((NewsHolder3) holder).bigLine.setVisibility(View.VISIBLE);
                ((NewsHolder3) holder).smallLine.setVisibility(View.GONE);
            } else {
                ((NewsHolder3) holder).bigLine.setVisibility(View.GONE);
                ((NewsHolder3) holder).smallLine.setVisibility(View.VISIBLE);
            }

            ((NewsHolder3) holder).tvName.setText(bean.getName());
            ((NewsHolder3) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder3) holder).tvTime.setText(bean.getTime());
            ((NewsHolder3) holder).tvReadnum.setText(bean.getRead_num());

            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder3) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder3) holder).tvAuthor.setVisibility(View.VISIBLE);
            }

            int sw = Tools.getScreenW(mContext);
            int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;
            Double rate = 80.0 / 110;

            int h = (int) (w * rate);


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
        if (holder instanceof NewsHolder11) {
            Glide.with(mContext).load(bean.getAvatar()).into(((NewsHolder11) holder).ivHead);
            ((NewsHolder11) holder).tvName.setText(bean.getUsername());
            ((NewsHolder11) holder).tvTime.setText(bean.getTime());

            //变蓝色 需求
            ((NewsHolder11) holder).tvContent.setText(bean.getContent());
            if (bean.getCover().size() != 0) {
                Glide.with(mContext).load(bean.getCover().get(0)).into(((NewsHolder11) holder).ivCover);
            } else {
                ((NewsHolder11) holder).ivCover.setVisibility(View.GONE);
            }
            ((NewsHolder11) holder).tvSee.setText(bean.getRead_num());
            ((NewsHolder11) holder).tvCirclename.setText(bean.getCircle_name());

            if (bean.isHas_like()) {
                ((NewsHolder11) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((NewsHolder11) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((NewsHolder11) holder).tvCommentnum.setText(bean.getComment_num());

        }
        if (holder instanceof NewsHolder19) {
            Glide.with(mContext).load(bean.getAvatar()).into(((NewsHolder19) holder).ivHead);
            ((NewsHolder19) holder).tvName.setText(bean.getUsername());
            ((NewsHolder19) holder).tvTime.setText(bean.getTime());

            //变蓝色 需求
            ((NewsHolder19) holder).tvContent.setText(bean.getContent());

            ImageGridListAdapter gvadapter = new ImageGridListAdapter(mContext, list.get(position).getCover());
            ((NewsHolder19) holder).gv.setAdapter(gvadapter);


            //九宫格
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((NewsHolder19) holder).gv.getLayoutParams();
            //获取当前控件的布局对象
            int sw = Tools.getScreenW(mContext);
            if (bean.getType() == 19) {
                ((NewsHolder19) holder).gv.setNumColumns(3);
                params.width = sw - Tools.dip2px(mContext, 15 + 15);
            } else {//四宫格
                ((NewsHolder19) holder).gv.setNumColumns(2);
                int w = (sw - Tools.dip2px(mContext, 15 + 15 + 6 + 6)) / 3;
                params.width = w * 2 + Tools.dip2px(mContext, 6);//设置当前控件布局的高度
            }


            ((NewsHolder19) holder).gv.setLayoutParams(params);

            gvadapter.notifyDataSetChanged();


            ((NewsHolder19) holder).tvSee.setText(bean.getRead_num());
            ((NewsHolder19) holder).tvCirclename.setText(bean.getCircle_name());

            if (bean.isHas_like()) {
                ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((NewsHolder19) holder).tvCommentnum.setText(bean.getComment_num());

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
    class NewsHolderTemp extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;


        NewsHolderTemp(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
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
        @BindView(R.id.bigline)
        View bigLine;
        @BindView(R.id.smallline)
        View smallLine;


        NewsHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }// 小图加文字

    class NewsHolder1Shop extends RecyclerView.ViewHolder {
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


        NewsHolder1Shop(View view) {
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
        @BindView(R.id.bigline)
        View bigLine;
        @BindView(R.id.smallline)
        View smallLine;


        NewsHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 一张小图
    class NewsHolder4 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;
        @BindView(R.id.tv_name)
        TextView tvName;

        NewsHolder4(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //圈子 一张 或者 没有
    class NewsHolder11 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentnum;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;

        NewsHolder11(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    // 九宫格

    class NewsHolder19 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        GridViewForScrollView gv;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentnum;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;

        NewsHolder19(View view) {
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
