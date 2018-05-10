package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.adapter.NineImageGridListAdapter;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.home.bean.HomePageRecommendBean;
import com.wetime.fanc.home.presenter.AttentionClickZanPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/6.
 */

public class HomePageRecommendAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<HomePageRecommendBean.DataBean.BannerBean> banners;
    private List<String> imgeUrls = new ArrayList<String>();
    private List<String> bannerTitles = new ArrayList<String>();
    private List<HomePageRecommendBean.DataBean.CirclesBean> circles ;
    private List<HomePageRecommendBean.DataBean.ListBean> list ;
    private Activity activity;
    private RecyclerView.ViewHolder holder;
    private HomePageRecommendHeaderRecyclerViewAdapter homePageRecommendHeaderRecyclerViewAdapter;


    public HomePageRecommendAdapter(List<HomePageRecommendBean.DataBean.BannerBean> banner, List<HomePageRecommendBean.DataBean.CirclesBean> circles, List<HomePageRecommendBean.DataBean.ListBean> list, FragmentActivity activity) {
       this.banners = banner;
        this.circles = circles;
        this.list = list;

        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 10 || viewType == 11 || viewType == 19 || viewType == 14) {
            return new HomePageAttentionAdapter.AttentionHolder(inflater.inflate(R.layout.item_attention_layout, parent, false));
        } else if (viewType == -1) {
            return new RecommendHeaderViewHolder(inflater.inflate(R.layout.recommend_header_layout, parent, false));
        } else if (viewType == 18) {
            return new HomePageAttentionAdapter.AttentionHolder18(inflater.inflate(R.layout.item_attention_layout_type18, parent, false));
        }
        return new HomePageAttentionAdapter.AttentionHolderTemp(inflater.inflate(R.layout.item_attention_layout_temp, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof RecommendHeaderViewHolder) {
            if (banners.size() <= 0) {
                ((RecommendHeaderViewHolder) holder).banner.setVisibility(View.GONE);
            } else {
                ((RecommendHeaderViewHolder) holder).banner.setVisibility(View.VISIBLE);
            }
            if (circles.size() <= 0) {
                ((RecommendHeaderViewHolder) holder).linearLayout.setVisibility(View.GONE);
                ((RecommendHeaderViewHolder) holder).recommendCircle.setVisibility(View.GONE);
            } else {
                ((RecommendHeaderViewHolder) holder).linearLayout.setVisibility(View.VISIBLE);
                ((RecommendHeaderViewHolder) holder).recommendCircle.setVisibility(View.VISIBLE);
            }
            if (homePageRecommendHeaderRecyclerViewAdapter == null) {
                Banner banner = ((RecommendHeaderViewHolder) holder).banner;
                ((RecommendHeaderViewHolder) holder).banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                });
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);//设置圆形指示器与标题
                banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置
                banner.setDelayTime(5000);//设置轮播时间
                for (HomePageRecommendBean.DataBean.BannerBean bannerBean : banners) {
                    imgeUrls.add(bannerBean.getCover().getCompress());
                    bannerTitles.add("");
                }
                banner.setImages(imgeUrls);//设置图片源
                banner.setBannerTitles(bannerTitles);
                banner.start();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ((RecommendHeaderViewHolder) holder).recommendCircle.setLayoutManager(linearLayoutManager);
                homePageRecommendHeaderRecyclerViewAdapter = new HomePageRecommendHeaderRecyclerViewAdapter(circles, activity);
                ((RecommendHeaderViewHolder) holder).recommendCircle.setAdapter(homePageRecommendHeaderRecyclerViewAdapter);
            } else {
                homePageRecommendHeaderRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
        if (holder instanceof HomePageAttentionAdapter.AttentionHolder) {
            HomePageRecommendBean.DataBean.ListBean bean = list.get(position - 1);
            holder.itemView.setOnClickListener(view -> {
                if (Integer.valueOf(bean.getType()) != -1) {
                    Intent goDet = new Intent(activity, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    activity.startActivity(goDet);
                }
            });
            Glide.with(activity).load(bean.getAvatar()).into(((HomePageAttentionAdapter.AttentionHolder) holder).atHead);
            ((HomePageAttentionAdapter.AttentionHolder) holder).atName.setText(bean.getUsername());
            ((HomePageAttentionAdapter.AttentionHolder) holder).atTime.setText(bean.getTime());
            int max = 100;
            //变蓝色 需求
            if (bean.getContent().length() > max) {
                SpannableString ss = new SpannableString(bean.getContent().substring(0, max) + "...全文");
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.text_blue)),
                        max + 3, max + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((HomePageAttentionAdapter.AttentionHolder) holder).atContent.setText(ss);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atContent.setText(bean.getContent());
            }
            if (TextUtils.isEmpty(bean.getContent())) {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atContent.setVisibility(View.GONE);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atContent.setVisibility(View.VISIBLE);
            }
            if (bean.getCovers().size() == 0) {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setVisibility(View.GONE);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(activity, bean.getCovers());
                ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setAdapter(gvadapter);
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(activity);
                if (Integer.valueOf(bean.getType()) == 19) {
                    ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(activity, 15 + 15);
                } else if (Integer.valueOf(bean.getType()) == 14) {//四宫格
                    ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(activity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(activity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(activity, 6 + 6);
                }

                ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setLayoutParams(params);
                gvadapter.notifyDataSetChanged();
                ((HomePageAttentionAdapter.AttentionHolder) holder).atGv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(activity, bean.getCovers(), i));
//                ((NewsHolder19) holder).gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            }
            ((HomePageAttentionAdapter.AttentionHolder) holder).atZannum.setText(Integer.valueOf(bean.getLike_num())==0?"点赞":bean.getLike_num());
            if (bean.isHas_like()) {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((HomePageAttentionAdapter.AttentionHolder) holder).atZannum.setText(Integer.valueOf(bean.getLike_num())==0?"点赞":bean.getLike_num());
            ((HomePageAttentionAdapter.AttentionHolder) holder).atCommentnum.setText(Integer.valueOf(bean.getComment_num())==0?"评论":bean.getLike_num());
            ((HomePageAttentionAdapter.AttentionHolder) holder).atZan.setOnClickListener(view -> {
                if (Tools.getSpu(activity).getToken().equals("")) {
                    Intent gologin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(gologin);
                } else {
                         if (bean.isHas_like()) {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),false, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((HomePageAttentionAdapter.AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                                    bean.setHas_like(false);
                                    int num = 0;
                                    if (Integer.valueOf(bean.getLike_num()) != 0) {
                                        num = Integer.valueOf(bean.getLike_num()) - 1;
                                    }
                                    ((HomePageAttentionAdapter.AttentionHolder) holder).atZannum.setText(num==0?"点赞":bean.getLike_num());
                                    bean.setLike_num(String.valueOf(num));
                                }
                            }
                        });
                    } else {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),true, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((HomePageAttentionAdapter.AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                                    bean.setHas_like(true);
                                    int num = Integer.valueOf(bean.getLike_num()) + 1;
                                    ((HomePageAttentionAdapter.AttentionHolder) holder).atZannum.setText(String.format("%d", num));
                                    bean.setLike_num(String.valueOf(num));
                                }
                            }
                        });
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atPublishTitle.setVisibility(View.GONE);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder) holder).atPublishTitle.setVisibility(View.VISIBLE);
                ((HomePageAttentionAdapter.AttentionHolder) holder).atcirclename.setText(bean.getCircle_name());
            }

            ((HomePageAttentionAdapter.AttentionHolder) holder).atcirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                activity.startActivity(goCircle);
            });


            ((HomePageAttentionAdapter.AttentionHolder) holder).atCommentnum.setText(bean.getComment_num());
            ((HomePageAttentionAdapter.AttentionHolder) holder).atHead.setOnClickListener(view -> {
                Intent go = new Intent(activity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                activity.startActivity(go);
            });
        }

        if (holder instanceof HomePageAttentionAdapter.AttentionHolder18) {
            HomePageRecommendBean.DataBean.ListBean bean = list.get(position - 1);
            ((HomePageAttentionAdapter.AttentionHolder18) holder).itemView.setOnClickListener(view -> {
                if (String.valueOf(bean.getType()) != -1 + "") {
                    LongDetailActivity.startToLongDetail(activity, bean.getId());
                }
            });
            Glide.with(activity).load(bean.getAvatar())
                    .into(((HomePageAttentionAdapter.AttentionHolder18)holder).atHead);
            ((HomePageAttentionAdapter.AttentionHolder18)holder).atName.setText(bean.getUsername());
            ((HomePageAttentionAdapter.AttentionHolder18)holder).atTime.setText(bean.getTime());

            ((HomePageAttentionAdapter.AttentionHolder18)holder).typename.setText(bean.getTitle());
            if (bean.getCovers() == null || bean.getCovers().size() == 0) {
            } else {
                Glide.with(activity).load(bean.getCovers().get(0).getCompress())
                        .apply(new RequestOptions().centerCrop())
                        .into(((HomePageAttentionAdapter.AttentionHolder18)holder).typeimage);
            }


//            ((NewsHolder18) holder).tvZannum.setText(bean.getLike_num());
            if (bean.isHas_like()) {
                ((HomePageAttentionAdapter.AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((HomePageAttentionAdapter.AttentionHolder18) holder).atZannum.setText(Integer.valueOf(bean.getLike_num())==0?"点赞":bean.getLike_num());
            ((HomePageAttentionAdapter.AttentionHolder18) holder).atCommentnum.setText(Integer.valueOf(bean.getComment_num())==0?"评论":bean.getLike_num());
            ((HomePageAttentionAdapter.AttentionHolder18)holder).atZan.setOnClickListener(view -> {
                if (Tools.getSpu(activity).getToken().equals("")) {
                    Intent gologin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(gologin);
                } else {
                    if (bean.isHas_like()) {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),false, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((HomePageAttentionAdapter.AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                                    int num = 0;
                                    if (Integer.valueOf(bean.getLike_num()) != 0) {
                                        num = Integer.valueOf(bean.getLike_num()) - 1;
                                    }
                                    ((HomePageAttentionAdapter.AttentionHolder18) holder).atZannum.setText(num==0?"点赞":bean.getLike_num());
                                    bean.setLike_num(String.valueOf(num));
                                    bean.setHas_like(false);

                                }
                            }
                        });
                    } else {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),true, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((HomePageAttentionAdapter.AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                                    bean.setHas_like(true);
                                    int num = Integer.valueOf(bean.getLike_num()) + 1;
                                    ((HomePageAttentionAdapter.AttentionHolder18) holder).atZannum.setText(String.format("%d", num));
                                    bean.setLike_num(String.valueOf(num));
                                }
                            }
                        });
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((HomePageAttentionAdapter.AttentionHolder18)holder).atPublishTitle.setVisibility(View.GONE);
                ((HomePageAttentionAdapter.AttentionHolder18)holder).at_linear.setVisibility(View.GONE);
            } else {
                ((HomePageAttentionAdapter.AttentionHolder18)holder).atPublishTitle.setVisibility(View.VISIBLE);
                ((HomePageAttentionAdapter.AttentionHolder18)holder).at_linear.setVisibility(View.VISIBLE);
                ((HomePageAttentionAdapter.AttentionHolder18)holder).atcirclename.setText(bean.getCircle_name());
            }

            ((HomePageAttentionAdapter.AttentionHolder18)holder).atcirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                activity.startActivity(goCircle);
            });

            ((HomePageAttentionAdapter.AttentionHolder18)holder).atHead.setOnClickListener(view -> {
                Intent go = new Intent(activity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                activity.startActivity(go);
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return -1;
        return Integer.valueOf(list.get(position - 1).getType());
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }






    public static class RecommendHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.rcl_circle)
        RecyclerView recommendCircle;
        @BindView(R.id.recommend_circles_title)
        LinearLayout linearLayout;


        public RecommendHeaderViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }
}
