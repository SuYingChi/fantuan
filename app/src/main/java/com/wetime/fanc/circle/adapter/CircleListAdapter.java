package com.wetime.fanc.circle.adapter;

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
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.bean.CircleListBean;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.customview.CanDoBlankGridView;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.act.RecomentFocusActivity;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.act.ShopDetailActivity;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class CircleListAdapter extends RecyclerView.Adapter {
    private List<CircleListBean.DataBean.ListBean> list;
    private Activity mActivity;
    private LayoutInflater inflater;
    // 区分  0 默认样式  1  特殊 shopnews 带阴影  只有单图
    private int listtype = 0;
    private boolean isBlod;

    public CircleListAdapter(List<CircleListBean.DataBean.ListBean> list, Activity mActivity) {
        this.list = list;
        this.mActivity = mActivity;
        this.inflater = LayoutInflater.from(mActivity);
        this.isBlod = false;
    }


    public CircleListAdapter(List<CircleListBean.DataBean.ListBean> list, Activity mActivity, boolean isBlod) {
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
            if (viewType == 10 || viewType == 11 || viewType == 19 || viewType == 14) {
                return new NewsHolder19(inflater.inflate(R.layout.item_news_type19, parent, false));
            } else if (viewType == 18) {
                return new NewsHolder18(inflater.inflate(R.layout.item_news_type18, parent, false));
            }
        } else if (listtype == 1) {
            //商家说  带阴影的 和正常的不一样 特殊处理
            return new NewsHolder1Shop(inflater.inflate(R.layout.item_news_type1_shop, parent, false));
        }
        return new NewsHolderTemp(inflater.inflate(R.layout.item_news_type_temp, parent, false));

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CircleListBean.DataBean.ListBean bean = list.get(position);

//        if (mOnItemClickLitener != null) {
//            holder.itemView.setOnItemClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
//        }
        //点击
        holder.itemView.setOnClickListener(view -> {
            switch (Integer.parseInt(bean.getType())) {
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
                case 9000:
                    Intent goFocus = new Intent(mActivity, RecomentFocusActivity.class);
                    mActivity.startActivity(goFocus);
                    break;
            }
        });


        if (holder instanceof NewsHolder18) {
//            ((NewsHolder18) holder).typelinear.setOnItemClickListener(view -> {
//                if (bean.getType() != -1) {
//                    LongDetailActivity.startToLongDetail(mActivity, bean.getId());
//                }
//            });
            ((NewsHolder18) holder).itemView.setOnClickListener(view -> {
                if (Integer.parseInt(bean.getType()) != -1) {
                    LongDetailActivity.startToLongDetail(mActivity, bean.getId());
                }
            });
            Glide.with(mActivity).load(bean.getAvatar())
                    .into(((NewsHolder18) holder).ivHead);
            ((NewsHolder18) holder).tvName.setText(bean.getUsername());
            ((NewsHolder18) holder).tvTime.setText(bean.getTime());

            ((NewsHolder18) holder).typename.setText(bean.getTitle());
            if (bean.getCovers() == null || bean.getCovers().size() == 0) {
            } else {
                Glide.with(mActivity).load(bean.getCovers().get(0).getCompress())
                        .apply(new RequestOptions().centerCrop())
                        .into(((NewsHolder18) holder).typeimage);
            }

            ((NewsHolder18) holder).tvSee.setText(bean.getRead_num());
//            ((NewsHolder18) holder).tvZannum.setText(bean.getLike_num());
            if (bean.isHas_like()) {
                ((NewsHolder18) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((NewsHolder18) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((NewsHolder18) holder).ivZan.setOnClickListener(view -> {
                if (Tools.getSpu(mActivity).getToken().equals("")) {
                    Intent gologin = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(gologin);
                } else {
                    ZanActPresenter presenter = new ZanActPresenter();
                    if (bean.isHas_like()) {
                        ((NewsHolder18) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "0");
                        bean.setHas_like(false);
                        int num = 0;
//                        if (Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) != 0) {
//                            num = Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) - 1;
//                        }
//                        ((NewsHolder18) holder).tvZannum.setText(String.format("%d", num));
                    } else {
                        ((NewsHolder18) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "1");
                        bean.setHas_like(true);
//                        int num = Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) + 1;
//                        ((NewsHolder18) holder).tvZannum.setText(String.format("%d", num));
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((NewsHolder18) holder).tvPubTitle.setVisibility(View.GONE);
                ((NewsHolder18) holder).linear.setVisibility(View.GONE);
            } else {
                ((NewsHolder18) holder).tvPubTitle.setVisibility(View.VISIBLE);
                ((NewsHolder18) holder).linear.setVisibility(View.VISIBLE);
                ((NewsHolder18) holder).tvCirclename.setText(bean.getCircle_name());
            }

            ((NewsHolder18) holder).tvCirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                mActivity.startActivity(goCircle);
            });

            ((NewsHolder18) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            if (bean.isIs_circle_owner()) {
                ((NewsHolder18) holder).ivOnwer.setVisibility(View.VISIBLE);
                ((NewsHolder18) holder).ivOnwer.setImageResource(R.drawable.bg_circle_onwer);
            } else {
                ((NewsHolder18) holder).ivOnwer.setVisibility(View.GONE);
            }
            if (bean.isIs_circle_manager()) {
                ((NewsHolder18) holder).ivManager.setVisibility(View.VISIBLE);
                ((NewsHolder18) holder).ivManager.setImageResource(R.drawable.invalid_manage);
            } else {
                ((NewsHolder18) holder).ivManager.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(bean.getLocation())) {
                ((NewsHolder18) holder).tvAddres.setVisibility(View.GONE);
            } else {
                ((NewsHolder18) holder).tvAddres.setVisibility(View.VISIBLE);
                ((NewsHolder18) holder).tvAddres.setText(bean.getLocation());
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
            if (list.get(position).getCovers().size() == 0) {
                ((NewsHolder19) holder).gv.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).gv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(mActivity, list.get(position).getCovers());
                ((NewsHolder19) holder).gv.setAdapter(gvadapter);
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((NewsHolder19) holder).gv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(mActivity);
                if (Integer.parseInt(bean.getType()) == 19) {
                    ((NewsHolder19) holder).gv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(mActivity, 15 + 15);
                } else if (Integer.parseInt(bean.getType()) == 14) {//四宫格
                    ((NewsHolder19) holder).gv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(mActivity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(mActivity, 6 + 6);
                }

                ((NewsHolder19) holder).gv.setLayoutParams(params);
                gvadapter.notifyDataSetChanged();
                ((NewsHolder19) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, bean.getCovers(), i));
                ((NewsHolder19) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            }

            ((NewsHolder19) holder).tvSee.setText(bean.getRead_num());
//            ((NewsHolder19) holder).tvZannum.setText(bean.getLike_num());
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
                        int num = 0;
//                        if (Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) != 0) {
//                            num = Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) - 1;
//                        }
//                        ((NewsHolder19) holder).tvZannum.setText(String.format("%d", num));
                    } else {
                        ((NewsHolder19) holder).ivZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                        presenter.zanAct(bean.getId(), Tools.getSpu(mActivity).getToken(), "1");
                        bean.setHas_like(true);
//                        int num = Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) + 1;
//                        ((NewsHolder19) holder).tvZannum.setText(String.format("%d", num));
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((NewsHolder19) holder).tvPubTitle.setVisibility(View.GONE);
                ((NewsHolder19) holder).tvCirclename.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).tvPubTitle.setVisibility(View.VISIBLE);
                ((NewsHolder19) holder).tvCirclename.setText(bean.getCircle_name());
            }

            ((NewsHolder19) holder).tvCirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                mActivity.startActivity(goCircle);
            });


//            ((NewsHolder19) holder).tvCommentnum.setText(bean.getComment_num());
            ((NewsHolder19) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            if (bean.isIs_circle_owner()) {
                ((NewsHolder19) holder).ivOnwer.setVisibility(View.VISIBLE);
                ((NewsHolder19) holder).ivOnwer.setImageResource(R.drawable.bg_circle_onwer);
            } else {
                ((NewsHolder19) holder).ivOnwer.setVisibility(View.GONE);
            }
            if (bean.isIs_circle_manager()) {
                ((NewsHolder19) holder).ivManager.setVisibility(View.VISIBLE);
                ((NewsHolder19) holder).ivManager.setImageResource(R.drawable.invalid_manage);
            } else {
                ((NewsHolder19) holder).ivManager.setVisibility(View.GONE);
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
        return Integer.parseInt(list.get(position).getType());
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
        @BindView(R.id.iv_manager)
        ImageView ivManager;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.linear)
        LinearLayout llCircleLine;

        NewsHolder19(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class NewsHolder18 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.type_image)
        ImageView typeimage;
        @BindView(R.id.type_name)
        TextView typename;
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
        @BindView(R.id.iv_manager)
        ImageView ivManager;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.linear)
        LinearLayout linear;
        @BindView(R.id.type_linear)
        LinearLayout typelinear;

        NewsHolder18(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



}
