package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.customview.CanDoBlankGridView;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class CircleHomeAdapter extends RecyclerView.Adapter {
    private List<HomeItemBean> list;
    private Activity mActivity;
    private LayoutInflater inflater;
    private List<CircleHomeListBean.DataBean.CirclesBean> circllist;
    private HeadCircleAdapter circleAdapter;
    private OnTabClickLitener mOnTabClickLitener;
    private OnItemClickLitener mOnItemClickLitener;
    private HeadCircleAdapter circleAdapter1;

    public CircleHomeAdapter(List<CircleHomeListBean.DataBean.CirclesBean> circllist,
                             List<HomeItemBean> mList,
                             Activity mActivity) {
        this.circllist = circllist;
        this.list = mList;
        this.mActivity = mActivity;
        this.inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 10 || viewType == 11 || viewType == 19 || viewType == 14) {
            return new NewsHolder19(inflater.inflate(R.layout.item_news_type19, parent, false));
        } else if (viewType == -1) {
            return new CircleHeadViewHolder(inflater.inflate(R.layout.item_circle_head, parent, false));
        }
        return new NewsHolderTemp(inflater.inflate(R.layout.item_news_type_temp, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


//        if (mOnItemClickLitener != null) {
//            holder.itemView.setOnClickListener(view -> mOnItemClickLitener.onItemClick(view, position));
//        }
        if (holder instanceof CircleHeadViewHolder) {
            if (circleAdapter == null) {
                ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
                String[] mTitles = {"热门动态", "最新动态"};
                for (String mTitle : mTitles) {
                    mTabEntities.add(new TabEntity(mTitle));
                }
                ((CircleHeadViewHolder) holder).commonTabLayout.setTabData(mTabEntities);
                ((CircleHeadViewHolder) holder).commonTabLayout.setCurrentTab(0);
                ((CircleHeadViewHolder) holder).commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
                    @Override
                    public void onTabSelect(int position) {
                        if (mOnTabClickLitener != null) {
                            mOnTabClickLitener.onTabClick(position);
                        }
                    }

                    @Override
                    public void onTabReselect(int position) {

                    }
                });
                //头部
                GridLayoutManager manager = new GridLayoutManager(mActivity, 4);
                ((CircleHeadViewHolder) holder).rclCircle.setLayoutManager(manager);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ((CircleHeadViewHolder) holder).rclCircleMy.setLayoutManager(linearLayoutManager);
                circleAdapter = new HeadCircleAdapter(circllist, mActivity, 0);
                if (circllist == null || circllist.size() == 0) {
                    ((CircleHeadViewHolder) holder).rclCircleMy.setVisibility(View.GONE);
                    ((CircleHeadViewHolder) holder).llHeadTv.setVisibility(View.VISIBLE);
                } else {
                    ((CircleHeadViewHolder) holder).rclCircleMy.setVisibility(View.VISIBLE);
                    ((CircleHeadViewHolder) holder).llHeadTv.setVisibility(View.GONE);
                    circleAdapter1 = new HeadCircleAdapter(circllist, mActivity, 1);
                }
                ((CircleHeadViewHolder) holder).rclCircle.setAdapter(circleAdapter);
                ((CircleHeadViewHolder) holder).rclCircleMy.setAdapter(circleAdapter1);
                circleAdapter.setOnItemClickLitener((view, position2) -> {
                    if (circllist.get(position2).getId().equals("0")) {
                        Intent goAll = new Intent(mActivity, AllCircleActivity.class);
                        mActivity.startActivity(goAll);
                    } else {
                        Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                        goCircle.putExtra("id", circllist.get(position2).getId());
                        mActivity.startActivity(goCircle);
                    }

                });
            }
        }
        if (holder instanceof NewsHolder19) {
            HomeItemBean bean = list.get(position - 1);
            holder.itemView.setOnClickListener(view -> {
                if (bean.getType() != -1) {
                    Intent goDet = new Intent(mActivity, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    mActivity.startActivity(goDet);
                }
            });
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
            if (bean.getCover().size() == 0) {
                ((NewsHolder19) holder).gv.setVisibility(View.GONE);
            } else {
                ((NewsHolder19) holder).gv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(mActivity, bean.getCover());
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
//                ((NewsHolder19) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
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
                        int num = 0;
                        if (Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) != 0) {
                            num = Integer.valueOf(((NewsHolder19) holder).tvZannum.getText().toString()) - 1;
                        }
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
        return list.size() + 1;
    }


    // 九宫格

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return -1;
        return list.get(position - 1).getType();
    }

    public void setOnTabClickLitener(OnTabClickLitener mOnTabClickLitener) {
        this.mOnTabClickLitener = mOnTabClickLitener;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public interface OnTabClickLitener {
        void onTabClick(int position);
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
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

    class NewsHolder19 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_addres)
        TextView tvAddres;
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

    class CircleHeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tablayout)
        CommonTabLayout commonTabLayout;
        @BindView(R.id.rcl_circle)
        RecyclerView rclCircle;
        @BindView(R.id.ll_headinfo)
        LinearLayout llHeadinfo;
        @BindView(R.id.rcl_circle_my)
        RecyclerView rclCircleMy;
        @BindView(R.id.ll_headmy)
        LinearLayout llHeadMy;
        @BindView(R.id.rcl_circle_tv)
        TextView llHeadTv;

        CircleHeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
