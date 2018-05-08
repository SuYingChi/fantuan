package com.wetime.fanc.news.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.MixtureTextView;
import com.wetime.fanc.news.act.GalleryActivity;
import com.wetime.fanc.news.act.RecomentFocusActivity;
import com.wetime.fanc.news.act.SpecialTopicActivity;
import com.wetime.fanc.news.bean.NewsListItemBean;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuxun on 2017/4/15.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private List<NewsListItemBean> list;
    private Activity mActivity;
    private LayoutInflater inflater;


    public NewsListAdapter(List<NewsListItemBean> list, Activity mActivity) {
        this.list = list;
        this.mActivity = mActivity;
        this.inflater = LayoutInflater.from(mActivity);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new NewsHolder0(inflater.inflate(R.layout.item_news_type0, parent, false));
        } else if (viewType == 1) {
            return new NewsHolder1(inflater.inflate(R.layout.item_news_type1, parent, false));
        } else if (viewType == 3) {
            return new NewsHolder3(inflater.inflate(R.layout.item_news_type3, parent, false));
        } else if (viewType == 4) {
            return new NewsHolder4(inflater.inflate(R.layout.item_news_type4, parent, false));
        } else if (viewType == 2) {//图集
            return new NewsHolder2(inflater.inflate(R.layout.item_news_type2, parent, false));
        } else if (viewType == 9000) {//头条 推荐 头部
            return new NewsHolder9000(inflater.inflate(R.layout.item_news_type9000, parent, false));
        } else if (viewType == 5) {//专题
            return new NewsHolder5(inflater.inflate(R.layout.item_news_type_report, parent, false));
        }

        return new NewsHolderTemp(inflater.inflate(R.layout.item_news_type_temp, parent, false));

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsListItemBean bean = list.get(position);

//        if (mOnItemClickLitener != null) {
//            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
//        }
        //点击
        holder.itemView.setOnClickListener(view -> {
            switch (bean.getType()) {
                case 2:
                    GalleryActivity.startToGallery(mActivity, bean.getId());
                    break;
                case 1:
                case 3:
                    if (TextUtils.isEmpty(list.get(position).getArticle_url()))
                        return;
                    Intent goweb = new Intent(mActivity, WebActivity.class);
                    goweb.putExtra("url", list.get(position).getArticle_url());
                    goweb.putExtra("type", "2");
                    goweb.putExtra("title", list.get(position).getNews_name());
                    mActivity.startActivity(goweb);
                    break;
                case 9000:
                    Intent goFocus = new Intent(mActivity, RecomentFocusActivity.class);
                    mActivity.startActivity(goFocus);
                    break;
            }
        });


        if (holder instanceof NewsHolder5) {
            ((NewsHolder5) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (TextUtils.isEmpty(bean.getArticle_url()))
//                        return;
                    if (TextUtils.isEmpty(bean.getSpecial().getCoverStr()))
                        return;
                    if (TextUtils.isEmpty(bean.getSpecial().getName()))
                        return;
                    if (TextUtils.isEmpty(bean.getSpecial().getIntro()))
                        return;
                    SpecialTopicActivity.startToSpecialTopic(mActivity, bean.getElements(),
                            bean.getSpecial().getCover().get(0).getCompress(),
                            bean.getSpecial().getName(),
                            bean.getSpecial().getIntro(),
                            bean.getSpecial().getArticle_url());
                }
            });

            if (bean.getSpecial() != null && bean.getSpecial().getName() != null) {
                ((NewsHolder5) holder).mixtureTextView.setText(" " + bean.getSpecial().getName());
            }
            if (bean.getLastest() != null && bean.getLastest().getName() != null) {
                ((NewsHolder5) holder).reportnew.setText(bean.getLastest().getName());
            }
            if (bean.getHottest() != null && bean.getHottest().getName() != null) {
                ((NewsHolder5) holder).reporthot.setText(bean.getHottest().getName());
            }
            if (bean.getFocused() != null && bean.getFocused().getName() != null) {
                ((NewsHolder5) holder).reportnewhot.setText(bean.getFocused().getName());
            }

            ((NewsHolder5) holder).reporthotliner.setOnClickListener(v -> {
                if (bean.getHottest().getContentType().equals("0")) {
                    if (TextUtils.isEmpty(bean.getHottest().getArticle_url()))
                        return;
                    Intent goweb = new Intent(mActivity, WebActivity.class);
                    goweb.putExtra("url", bean.getHottest().getArticle_url());
                    goweb.putExtra("type", "2");
                    goweb.putExtra("title", bean.getHottest().getAuthor());
                    mActivity.startActivity(goweb);
                } else {
                    GalleryActivity.startToGallery(mActivity, bean.getHottest().getId());
                }
            });
            ((NewsHolder5) holder).reportnewhotliner.setOnClickListener(v -> {
                if (bean.getFocused().getContentType().equals("0")) {
                    if (TextUtils.isEmpty(bean.getFocused().getArticle_url()))
                        return;
                    Intent goweb = new Intent(mActivity, WebActivity.class);
                    goweb.putExtra("url", bean.getFocused().getArticle_url());
                    goweb.putExtra("type", "2");
                    goweb.putExtra("title", bean.getFocused().getAuthor());
                    mActivity.startActivity(goweb);
                } else {
                    GalleryActivity.startToGallery(mActivity, bean.getFocused().getId());
                }

            });
            ((NewsHolder5) holder).reportnewliner.setOnClickListener(v -> {
                if (bean.getLastest().getContentType().equals("0")) {
                    if (TextUtils.isEmpty(bean.getLastest().getArticle_url()))
                        return;
                    Intent goweb = new Intent(mActivity, WebActivity.class);
                    goweb.putExtra("url", bean.getLastest().getArticle_url());
                    goweb.putExtra("type", "2");
                    goweb.putExtra("title", bean.getLastest().getAuthor());
                    mActivity.startActivity(goweb);
                } else {
                    GalleryActivity.startToGallery(mActivity, bean.getLastest().getId());
                }

            });
            int sw = Tools.getScreenW(mActivity);
            int w = sw - Tools.dip2px(mActivity, 15 + 15);

            Double rate = 194.0 / 345;
            int h = (int) (w * rate);
            if (bean.getSpecial().getCover() != null && bean.getSpecial().getCover().size() > 0) {

                Glide.with(mActivity)
                        .load(bean.getSpecial().getCover().get(0).getCompress())
                        .apply(
                                new RequestOptions()
                                        .override(w, h)
                                        .centerCrop()
                                        .placeholder(R.drawable.iv_default_news_small))
                        .into(((NewsHolder5) holder).reportcover);
            }

        }


        if (holder instanceof NewsHolder0) {
            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15));
            Double rate = 80.0 / 345;

            int h = (int) (w * rate);

            Glide.with(mActivity).load(bean.getBanner().get(0).getImg()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder0) holder).ivBanner);
        }
        if (holder instanceof NewsHolder4) {
            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15));
            Double rate = 135.0 / 345;

            int h = (int) (w * rate);
            ((NewsHolder4) holder).tvName.setText(bean.getName());
            Glide.with(mActivity).load(bean.getCover().get(0).getCompress()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_single_big))
                    .into(((NewsHolder4) holder).ivBanner);
        }
        if (holder instanceof NewsHolder1) {


            ((NewsHolder1) holder).tvName.setText(bean.getName());
            ((NewsHolder1) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder1) holder).tvTime.setText(bean.getTime());
            ((NewsHolder1) holder).tvReadnum.setText(bean.getRead_num());
            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder1) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder1) holder).tvAuthor.setVisibility(View.VISIBLE);
            }
            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;

            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mActivity).load(bean.getCover().get(0).getCompress()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))

                    .into(((NewsHolder1) holder).ivCover);
        }
        if (holder instanceof NewsHolder2) {

            ((NewsHolder2) holder).tvName.setText(bean.getName());
            ((NewsHolder2) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder2) holder).tvTime.setText(bean.getTime());
            ((NewsHolder2) holder).tvReadnum.setText(bean.getRead_num());
            ((NewsHolder2) holder).tvCoverNum.setText(bean.getAtlas_num());
            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder2) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder2) holder).tvAuthor.setVisibility(View.VISIBLE);
            }
            int sw = Tools.getScreenW(mActivity);
            int w = sw - Tools.dip2px(mActivity, 15 + 15);

            Double rate = 194.0 / 345;
            int h = (int) (w * rate);

            ViewGroup.LayoutParams params = ((NewsHolder2) holder).rlIv.getLayoutParams();
            params.height = h;
            params.width = w;
            ((NewsHolder2) holder).rlIv.setLayoutParams(params);

            Glide.with(mActivity).load(bean.getCover().get(0).getCompress()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder2) holder).ivCover);
        }


        if (holder instanceof NewsHolder3) {


            ((NewsHolder3) holder).tvName.setText(bean.getName());
            ((NewsHolder3) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder3) holder).tvTime.setText(bean.getTime());
            ((NewsHolder3) holder).tvReadnum.setText(bean.getRead_num());

            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder3) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder3) holder).tvAuthor.setVisibility(View.VISIBLE);
            }

            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
            Double rate = 80.0 / 110;

            int h = (int) (w * rate);


            Glide.with(mActivity).load(bean.getCover().get(0).getCompress()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover0);

            Glide.with(mActivity).load(bean.getCover().get(1).getCompress()).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover1);

            Glide.with(mActivity).load(bean.getCover().get(2).getCompress()).apply(
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
    class NewsHolderTemp extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_banner)
//        ImageView ivBanner;


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



        NewsHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    // 图集
    class NewsHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.rl_iv)
        RelativeLayout rlIv;
        @BindView(R.id.tv_covernum)
        TextView tvCoverNum;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_readnum)
        TextView tvReadnum;
        @BindView(R.id.iv_cover)
        ImageView ivCover;


        NewsHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }// 图集

    class NewsHolder9000 extends RecyclerView.ViewHolder {

        NewsHolder9000(View view) {
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


    class NewsHolder5 extends RecyclerView.ViewHolder {
        @BindView(R.id.report_cover)
        ImageView reportcover;
        @BindView(R.id.mixtureTextView)
        MixtureTextView mixtureTextView;
        //        @BindView(R.id.report_name)
//        TextView reportname;
        @BindView(R.id.report_new)
        TextView reportnew;
        @BindView(R.id.report_hot)
        TextView reporthot;
        @BindView(R.id.report_newhot)
        TextView reportnewhot;
        @BindView(R.id.report_hot_liner)
        LinearLayout reporthotliner;
        @BindView(R.id.report_new_liner)
        LinearLayout reportnewliner;
        @BindView(R.id.report_newhot_liner)
        LinearLayout reportnewhotliner;

        NewsHolder5(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
