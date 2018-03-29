package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.adapter.NineImageGridListAdapter;
import com.wetime.fanc.circle.adapter.NineImageGridListAdapterCard;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.customview.CanDoBlankGridView;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.my.presenter.DeleteMyNewsPresenter;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.act.ShopDetailActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class HomeItemAdapter extends RecyclerView.Adapter {
    private List<HomeItemBean> list;
    private Activity mActivity;
    private LayoutInflater inflater;
    // 区分  0 默认样式  1  特殊 shopnews 带阴影  只有单图
    private int listtype = 0;
    private boolean isBlod;

    public HomeItemAdapter(List<HomeItemBean> list, Activity mActivity) {
        this.list = list;
        this.mActivity = mActivity;
        this.inflater = LayoutInflater.from(mActivity);
        this.isBlod = false;
    }

    public HomeItemAdapter(List<HomeItemBean> list, Activity mActivity, boolean isBlod) {
        this.list = list;
        this.mActivity = mActivity;
        this.inflater = LayoutInflater.from(mActivity);
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
            } else if (viewType == 10 || viewType == 11 || viewType == 19 || viewType == 14) {
                return new NewsHolder19(inflater.inflate(R.layout.item_news_type19, parent, false));
            } else if (viewType == 30 || viewType == 31 || viewType == 39 || viewType == 34) {
                return new NewsHolder39(inflater.inflate(R.layout.item_news_type39, parent, false));
            } else if (viewType == 29) {
                return new NewsHolder29(inflater.inflate(R.layout.item_news_type29, parent, false));
            } else if (viewType == -1) {
                return new NewsHolderDelete(inflater.inflate(R.layout.item_news_delete, parent, false));
            } else if (viewType == 1000) {
                return new NewsHolder1000(inflater.inflate(R.layout.item_usercard_empty, parent, false));
            } else if (viewType == 2) {//图集
                return new NewsHolder2(inflater.inflate(R.layout.item_news_type2, parent, false));
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

//        if (mOnItemClickLitener != null) {
//            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
//        }

        holder.itemView.setOnClickListener(view -> {
            switch (bean.getType()) {
                case 2:

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
                case 10:
                case 11:
                case 14:
                case 19:
                case 30:
                case 31:
                case 34:
                case 39:
                    Intent goDet = new Intent(mActivity, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    mActivity.startActivity(goDet);
                    break;
            }
        });
        if (holder instanceof NewsHolder1000) {
            switch (bean.getEmptyType()) {
                case "1":
                    ((NewsHolder1000) holder).tvEmpty.setText("暂无动态");
                    ((NewsHolder1000) holder).ivEmpty.setImageResource(R.drawable.iv_empty_card_act);
                    break;
                case "2":
                    ((NewsHolder1000) holder).tvEmpty.setText("暂无头条");
                    ((NewsHolder1000) holder).ivEmpty.setImageResource(R.drawable.iv_empty_card_news);
                    break;
                case "3":
                    ((NewsHolder1000) holder).tvEmpty.setText("暂无点评");
                    ((NewsHolder1000) holder).ivEmpty.setImageResource(R.drawable.iv_empty_card_comment);
                    break;
            }

        }
        if (holder instanceof NewsHolderDelete) {
            ((NewsHolderDelete) holder).tvTitle.setText(String.format("抱歉，您收藏的文章《%s》已被删除。", bean.getNews_name()));
            ((NewsHolderDelete) holder).ivNewsDelete.setOnClickListener(view -> {
                list.remove(position);
                notifyItemRemoved(position);
                DeleteMyNewsPresenter deleteMyNewsPresenter = new DeleteMyNewsPresenter();
                deleteMyNewsPresenter.detDleteMyNews(bean.getId(), Tools.getSpu(mActivity).getToken());
            });

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
            Glide.with(mActivity).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_single_big))
                    .into(((NewsHolder4) holder).ivBanner);
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
            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;

            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mActivity).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))

                    .into(((NewsHolder1) holder).ivCover);
        }
        if (holder instanceof NewsHolder2) {
            if (isBlod) {
                ((NewsHolder2) holder).bigLine.setVisibility(View.VISIBLE);
                ((NewsHolder2) holder).smallLine.setVisibility(View.GONE);
            } else {
                ((NewsHolder2) holder).bigLine.setVisibility(View.GONE);
                ((NewsHolder2) holder).smallLine.setVisibility(View.VISIBLE);
            }

            ((NewsHolder2) holder).tvName.setText(bean.getName());
            ((NewsHolder2) holder).tvAuthor.setText(bean.getNews_name());
            ((NewsHolder2) holder).tvTime.setText(bean.getTime());
            ((NewsHolder2) holder).tvReadnum.setText(bean.getRead_num());
            ((NewsHolder2) holder).tvCoverNum.setText(String.format("%d图", bean.getCover().size()));
            if (TextUtils.isEmpty(bean.getNews_name())) {
                ((NewsHolder2) holder).tvAuthor.setVisibility(View.GONE);
            } else {
                ((NewsHolder2) holder).tvAuthor.setVisibility(View.VISIBLE);
            }
            int sw = Tools.getScreenW(mActivity);
            int w = sw - Tools.dip2px(mActivity, 15 + 15);

            Double rate = 160.0 / 345;
            int h = (int) (w * rate);

            ViewGroup.LayoutParams params = ((NewsHolder2) holder).rlIv.getLayoutParams();
            params.height = h;
            params.width = w;
            ((NewsHolder2) holder).rlIv.setLayoutParams(params);

            Glide.with(mActivity).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))

                    .into(((NewsHolder2) holder).ivCover);
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
            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;

            Double rate = 80.0 / 110;

            int h = (int) (w * rate);
            Glide.with(mActivity).load(bean.getCover().get(0)).apply(
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

            int sw = Tools.getScreenW(mActivity);
            int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
            Double rate = 80.0 / 110;

            int h = (int) (w * rate);


            Glide.with(mActivity).load(bean.getCover().get(0)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover0);

            Glide.with(mActivity).load(bean.getCover().get(1)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover1);

            Glide.with(mActivity).load(bean.getCover().get(2)).apply(
                    new RequestOptions()
                            .override(w, h)
                            .centerCrop()
                            .placeholder(R.drawable.iv_default_news_small))
                    .into(((NewsHolder3) holder).ivCover2);
        }
        if (holder instanceof NewsHolder29) {
            if (TextUtils.isEmpty(bean.getYear())) {
                ((NewsHolder29) holder).tvYear.setVisibility(View.GONE);
            } else {
                ((NewsHolder29) holder).tvYear.setVisibility(View.VISIBLE);
                ((NewsHolder29) holder).tvYear.setText(bean.getYear());
            }
            ((NewsHolder29) holder).tvDay.setText(bean.getDay());
            ((NewsHolder29) holder).tvMonth.setText(String.format("%s月", bean.getMonth()));
            ((NewsHolder29) holder).tvContent.setText(bean.getContent());
            ((NewsHolder29) holder).rbSocre.setStar(Float.valueOf(bean.getScore()));

            NineImageGridListAdapterCard gvadapter = new NineImageGridListAdapterCard(mActivity, list.get(position).getCover());
            ((NewsHolder29) holder).gv.setAdapter(gvadapter);


            //九宫格
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((NewsHolder29) holder).gv.getLayoutParams();

            ((NewsHolder29) holder).gv.setNumColumns(3);

            ((NewsHolder29) holder).gv.setLayoutParams(params);
            gvadapter.notifyDataSetChanged();
            ((NewsHolder29) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, bean.getCover(), i));
//            ((NewsHolder29) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            if (TextUtils.isEmpty(bean.getReply())) {
                ((NewsHolder29) holder).tvShopsay.setVisibility(View.GONE);
            } else {
                ((NewsHolder29) holder).tvShopsay.setVisibility(View.VISIBLE);
                SpannableString s1 = new SpannableString("商家回复：" + bean.getReply());
                s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.textcolor)),
                        0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((NewsHolder29) holder).tvShopsay.setText(s1);
            }
            SpannableString s2 = new SpannableString("点评了店铺 " + bean.getMerchant_name());
            s2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.textcolor)),
                    0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((NewsHolder29) holder).tvShopname.setText(s2);
            ((NewsHolder29) holder).tvShopname.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, ShopDetailActivity.class);
                go.putExtra("mid", list.get(position).getMid());
                mActivity.startActivity(go);
            });

        }
        if (holder instanceof NewsHolder39) {
            if (TextUtils.isEmpty(bean.getYear())) {
                ((NewsHolder39) holder).tvYear.setVisibility(View.GONE);
            } else {
                ((NewsHolder39) holder).tvYear.setVisibility(View.VISIBLE);
                ((NewsHolder39) holder).tvYear.setText(bean.getYear());
            }

            ((NewsHolder39) holder).tvDay.setText(bean.getDay());
            ((NewsHolder39) holder).tvMonth.setText(String.format("%s月", bean.getMonth()));
            int max = 100;
            //变蓝色 需求
            if (bean.getContent().length() > max) {
                SpannableString ss = new SpannableString(bean.getContent().substring(0, max) + "...全文");
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.text_blue)),
                        max + 3, max + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((NewsHolder39) holder).tvContent.setText(ss);
            } else {
                ((NewsHolder39) holder).tvContent.setText(bean.getContent());
            }
            ((NewsHolder39) holder).tvSee.setText(bean.getRead_num());
            ((NewsHolder39) holder).tvZannum.setText(bean.getLike_num());
            ((NewsHolder39) holder).tvCommentnum.setText(bean.getComment_num());
            if (bean.isHas_like()) {
                ((NewsHolder39) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((NewsHolder39) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((NewsHolder39) holder).ivZan.setOnClickListener(view -> {
                if (Tools.getSpu(mActivity).getToken().equals("")) {
                    Intent gologin = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(gologin);
                } else {
                    ZanActPresenter presenter = new ZanActPresenter();
                    if (bean.isHas_like()) {
                        ((NewsHolder39) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "0");
                        bean.setHas_like(false);
                        int num = Integer.valueOf(((NewsHolder39) holder).tvZannum.getText().toString()) - 1;
                        ((NewsHolder39) holder).tvZannum.setText(String.format("%d", num));
                    } else {
                        ((NewsHolder39) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "1");
                        bean.setHas_like(true);
                        int num = Integer.valueOf(((NewsHolder39) holder).tvZannum.getText().toString()) + 1;
                        ((NewsHolder39) holder).tvZannum.setText(String.format("%d", num));
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((NewsHolder39) holder).tvPubTitle.setVisibility(View.GONE);
            } else {
                ((NewsHolder39) holder).tvPubTitle.setVisibility(View.VISIBLE);
                ((NewsHolder39) holder).tvCirclename.setText(bean.getCircle_name());
            }

            //九宫格
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((NewsHolder39) holder).gv.getLayoutParams();
            //获取当前控件的布局对象
            int sw = Tools.getScreenW(mActivity);
            if (bean.getType() == 39) {
                ((NewsHolder39) holder).gv.setNumColumns(3);
                params.width = sw - Tools.dip2px(mActivity, 60 + 15 + 15);
            } else if (bean.getType() == 34) {//四宫格
                ((NewsHolder39) holder).gv.setNumColumns(2);
                int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6 + 60)) / 3;
                params.width = w * 2 + Tools.dip2px(mActivity, 6);//设置当前控件布局的高度
            } else {//单图
                params.width = sw - Tools.dip2px(mActivity, 15 + 15 + 60);
            }

            NineImageGridListAdapterCard gvadapter = new NineImageGridListAdapterCard(mActivity, list.get(position).getCover());
            ((NewsHolder39) holder).gv.setLayoutParams(params);
            ((NewsHolder39) holder).gv.setAdapter(gvadapter);


            gvadapter.notifyDataSetChanged();
            ((NewsHolder39) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            ((NewsHolder39) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, bean.getCover(), i));
            ((NewsHolder39) holder).tvCirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                mActivity.startActivity(goCircle);
            });
            if (TextUtils.isEmpty(bean.getLocation())) {
                ((NewsHolder39) holder).tvAddres.setVisibility(View.GONE);
            } else {
                ((NewsHolder39) holder).tvAddres.setVisibility(View.VISIBLE);
                ((NewsHolder39) holder).tvAddres.setText(bean.getLocation());
            }

        }
        if (holder instanceof NewsHolder19) {
            Glide.with(mActivity).load(bean.getAvatar()).into(((NewsHolder19) holder).ivHead);
            ((NewsHolder19) holder).tvName.setText(bean.getUsername());
            ((NewsHolder19) holder).tvTime.setText(bean.getTime());
            int max = 100;
            //变蓝色 需求
            if (bean.getContent().length() > max) {
                SpannableString ss = new SpannableString(bean.getContent().substring(0, max) + "...全文");
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.text_blue)),
                        max + 3, max + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((NewsHolder19) holder).tvContent.setText(ss);
            } else {
                ((NewsHolder19) holder).tvContent.setText(bean.getContent());
            }
            if (TextUtils.isEmpty(bean.getContent())) {
                ((NewsHolder19) holder).tvContent.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).tvContent.setVisibility(View.VISIBLE);
            }
            if (list.get(position).getCover().size() == 0) {
                ((NewsHolder19) holder).gv.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).gv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(mActivity, list.get(position).getCover());
                ((NewsHolder19) holder).gv.setAdapter(gvadapter);
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((NewsHolder19) holder).gv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(mActivity);
                if (bean.getType() == 19) {
                    ((NewsHolder19) holder).gv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(mActivity, 15 + 15);
                } else if (bean.getType() == 14) {//四宫格
                    ((NewsHolder19) holder).gv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(mActivity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(mActivity, 6 + 6);
                }

                ((NewsHolder19) holder).gv.setLayoutParams(params);
                gvadapter.notifyDataSetChanged();
                ((NewsHolder19) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, bean.getCover(), i));
                ((NewsHolder19) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            }

            ((NewsHolder19) holder).tvSee.setText(bean.getRead_num());
            ((NewsHolder19) holder).tvZannum.setText(bean.getLike_num());
            if (bean.isHas_like()) {
                ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((NewsHolder19) holder).ivZan.setOnClickListener(view -> {
                if (Tools.getSpu(mActivity).getToken().equals("")) {
                    Intent gologin = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(gologin);
                } else {
                    ZanActPresenter presenter = new ZanActPresenter();
                    if (bean.isHas_like()) {
                        ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "0");
                        bean.setHas_like(false);
                        int num = Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) - 1;
                        ((NewsHolder19) holder).tvZannum.setText(String.format("%d", num));
                    } else {
                        ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "1");
                        bean.setHas_like(true);
                        int num = Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) + 1;
                        ((NewsHolder19) holder).tvZannum.setText(String.format("%d", num));
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((NewsHolder19) holder).tvPubTitle.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).tvPubTitle.setVisibility(View.VISIBLE);
                ((NewsHolder19) holder).tvCirclename.setText(bean.getCircle_name());
            }

            ((NewsHolder19) holder).tvCirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                mActivity.startActivity(goCircle);
            });


            ((NewsHolder19) holder).tvCommentnum.setText(bean.getComment_num());
            ((NewsHolder19) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            if (bean.isCircle_owner()) {
                ((NewsHolder19) holder).ivOnwer.setVisibility(View.VISIBLE);
            } else {
                ((NewsHolder19) holder).ivOnwer.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(bean.getLocation())) {
                ((NewsHolder19) holder).tvAddres.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).tvAddres.setVisibility(View.VISIBLE);
                ((NewsHolder19) holder).tvAddres.setText(bean.getLocation());
            }

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
        @BindView(R.id.bigline)
        View bigLine;
        @BindView(R.id.smallline)
        View smallLine;


        NewsHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

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

    // 新闻 收藏删除
    class NewsHolderDelete extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_news_delete)
        ImageView ivNewsDelete;

        NewsHolderDelete(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    // 九宫格

    class NewsHolder19 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        CanDoBlankGridView gv;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentnum;
        @BindView(R.id.tv_publishtitle)
        TextView tvPubTitle;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.iv_onwer)
        ImageView ivOnwer;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;

        NewsHolder19(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder39 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        CanDoBlankGridView gv;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentnum;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_publishtitle)
        TextView tvPubTitle;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;

        NewsHolder39(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder29 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.rb_socre)
        MyRatingBar rbSocre;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        CanDoBlankGridView gv;
        @BindView(R.id.tv_shopname)
        TextView tvShopname;
        @BindView(R.id.tv_shopsay)
        TextView tvShopsay;

        NewsHolder29(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder1000 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_empty)
        ImageView ivEmpty;
        @BindView(R.id.tv_empty)
        TextView tvEmpty;
        @BindView(R.id.rl_empty)
        RelativeLayout rlEmpty;

        NewsHolder1000(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


//    public interface OnItemClickLitener {
//        void onItemClick(View view, int position);
//    }
//
//
//    private OnItemClickLitener mOnItemClickLitener;
//
//
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }

}
