package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.adapter.NineImageGridListAdapter;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.customview.CanDoBlankGridView;
import com.wetime.fanc.home.bean.HomePageAttentionBean;
import com.wetime.fanc.home.presenter.AttentionClickZanPresenter;
import com.wetime.fanc.home.presenter.AttentionClickZanPresenter.OnLikeAttentionListener;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/5/4.
 */

public class HomePageAttentionAdapter extends RecyclerView.Adapter {

    private List<HomePageAttentionBean.DataBean.ListBean> list;
    private Activity activity;
    private LayoutInflater inflater;

    public HomePageAttentionAdapter(List<HomePageAttentionBean.DataBean.ListBean> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 10 || viewType == 19||viewType == 14||viewType == 11) {
            return new AttentionHolder(inflater.inflate(R.layout.item_attention_layout, parent, false));
        } else if (viewType == 18) {
            return new AttentionHolder18(inflater.inflate(R.layout.item_attention_layout_type18, parent, false));
        }


        return new AttentionHolderTemp(inflater.inflate(R.layout.item_attention_layout_temp, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomePageAttentionBean.DataBean.ListBean bean = list.get(holder.getAdapterPosition());
        int type = Integer.valueOf(bean.getType());
        if (holder instanceof AttentionHolder) {
            holder.itemView.setOnClickListener(view -> {
                if (Integer.valueOf(bean.getType()) != -1) {
                    Intent goDet = new Intent(activity, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    activity.startActivity(goDet);
                }
            });
            Glide.with(activity).load(bean.getAvatar()).into(((AttentionHolder)holder).atHead);
            ((AttentionHolder)holder).atName.setText(bean.getUsername());
            ((AttentionHolder)holder).atTime.setText(bean.getTime());
            int max = 100;
            if (bean.getContent().length() > max) {
                SpannableString ss = new SpannableString(bean.getContent().substring(0, max) + "...全文");
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.text_blue)),
                        max + 3, max + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((AttentionHolder)holder).atContent.setText(ss);
            } else {
                ((AttentionHolder)holder).atContent.setText(bean.getContent());
            }
            if (TextUtils.isEmpty(bean.getContent())) {
                ((AttentionHolder)holder).atContent.setVisibility(View.GONE);
            } else {
                ((AttentionHolder)holder).atContent.setVisibility(View.VISIBLE);
            }
            if (bean.getCovers().size() == 0) {
                ((AttentionHolder)holder).atGv.setVisibility(View.GONE);
            } else {
                ((AttentionHolder)holder).atGv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(activity, list.get(position).getCovers());
                ((AttentionHolder)holder).atGv.setAdapter(gvadapter);
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((AttentionHolder)holder).atGv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(activity);
                if (type == 19) {
                    ((AttentionHolder)holder).atGv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(activity, 15 + 15);
                } else if (type == 14) {//四宫格
                    ((AttentionHolder)holder).atGv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(activity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(activity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(activity, 6 + 6);
                }

                ((AttentionHolder)holder).atGv.setLayoutParams(params);
                gvadapter.notifyDataSetChanged();
                ((AttentionHolder)holder).atGv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(activity, bean.getCovers(), i));
                ((AttentionHolder)holder).atGv.setOnTouchInvalidPositionListener(motionEvent -> false);
            }
//            ((NewsHolder19) holder).tvZannum.setText(bean.getLike_num());
            if (bean.isHas_like()) {
                ((AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((AttentionHolder) holder).atZannum.setText(Integer.valueOf(bean.getLike_num())==0?"点赞":bean.getLike_num());
            ((AttentionHolder) holder).atCommentnum.setText(Integer.valueOf(bean.getComment_num())==0?"评论":bean.getLike_num());
            ((AttentionHolder)holder).atZan.setOnClickListener(view -> {
                if (Tools.getSpu(activity).getToken().equals("")) {
                    Intent gologin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(gologin);
                } else {
                    if (bean.isHas_like()) {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),false, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                                    bean.setHas_like(false);
                                    int num = 0;
                                    if (Integer.valueOf(bean.getLike_num()) != 0) {
                                        num = Integer.valueOf(bean.getLike_num()) - 1;
                                    }
                                    ((AttentionHolder) holder).atZannum.setText(num==0?"点赞":bean.getLike_num());
                                    bean.setLike_num(String.valueOf(num));
                                }
                            }
                        });
                    } else {
                        AttentionClickZanPresenter.clickZan(activity,bean.getId(),true, new AttentionClickZanPresenter.OnLikeAttentionListener() {
                            @Override
                            public void onLikeAttentionListener(boolean isSuccess) {
                                if(isSuccess){
                                    ((AttentionHolder)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                                    bean.setHas_like(true);
                                    int num = Integer.valueOf(bean.getLike_num()) + 1;
                                    ((AttentionHolder) holder).atZannum.setText(String.format("%d", num));
                                    bean.setLike_num(String.valueOf(num));
                                }
                            }
                        });
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                ((AttentionHolder)holder).atPublishTitle.setVisibility(View.GONE);
                ((AttentionHolder)holder).at_linear.setVisibility(View.GONE);
            } else {
                ((AttentionHolder)holder).atPublishTitle.setVisibility(View.VISIBLE);
                ((AttentionHolder)holder).atcirclename.setText(bean.getCircle_name());
            }

            ((AttentionHolder)holder).atcirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                activity.startActivity(goCircle);
            });

            ((AttentionHolder)holder).atHead.setOnClickListener(view -> {
                Intent go = new Intent(activity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                activity.startActivity(go);
            });
        } else if (holder instanceof AttentionHolder18) {
            ((HomePageAttentionAdapter.AttentionHolder18) holder).itemView.setOnClickListener(view -> {
                if (String.valueOf(bean.getType()) != -1 + "") {
                    LongDetailActivity.startToLongDetail(activity, bean.getId());
                }
            });
            Glide.with(activity).load(bean.getAvatar())
                    .into(((AttentionHolder18)holder).atHead);
            ((AttentionHolder18)holder).atName.setText(bean.getUsername());
            ((AttentionHolder18)holder).atTime.setText(bean.getTime());

            ((AttentionHolder18)holder).typename.setText(bean.getTitle());
            if (bean.getCovers() == null || bean.getCovers().size() == 0) {
            } else {
                Glide.with(activity).load(bean.getCovers().get(0).getCompress())
                        .apply(new RequestOptions().centerCrop())
                        .into(((AttentionHolder18)holder).typeimage);
            }

            if (bean.isHas_like()) {
                ((AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                ((AttentionHolder18)holder).atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            ((AttentionHolder18) holder).atZannum.setText(Integer.valueOf(bean.getLike_num())==0?"点赞":bean.getLike_num());
            ((AttentionHolder18) holder).atCommentnum.setText(Integer.valueOf(bean.getComment_num())==0?"评论":bean.getLike_num());
            ((AttentionHolder18)holder).atZan.setOnClickListener(view -> {
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
                                    bean.setHas_like(false);
                                    int num = 0;
                                    if (Integer.valueOf(bean.getLike_num()) != 0) {
                                        num = Integer.valueOf(bean.getLike_num()) - 1;
                                    }
                                    ((HomePageAttentionAdapter.AttentionHolder18) holder).atZannum.setText(num==0?"点赞":bean.getLike_num());
                                    bean.setLike_num(String.valueOf(num));
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
                ((AttentionHolder18)holder).atPublishTitle.setVisibility(View.GONE);
                ((AttentionHolder18)holder).at_linear.setVisibility(View.GONE);
            } else {
                ((AttentionHolder18)holder).atPublishTitle.setVisibility(View.VISIBLE);
                ((AttentionHolder18)holder).at_linear.setVisibility(View.VISIBLE);
                ((AttentionHolder18)holder).atcirclename.setText(bean.getCircle_name());
            }

            ((AttentionHolder18)holder).atcirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                activity.startActivity(goCircle);
            });


            ((AttentionHolder18)holder).atHead.setOnClickListener(view -> {
                Intent go = new Intent(activity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                activity.startActivity(go);
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class AttentionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.at_name)
        TextView atName;
        @BindView(R.id.at_time)
        TextView atTime;
        @BindView(R.id.at_content)
        TextView atContent;
        @BindView(R.id.at_gv)
        CanDoBlankGridView atGv;
        @BindView(R.id.at_commentnum)
        TextView atCommentnum;
        @BindView(R.id.at_zan)
        ImageView atZan;
        @BindView(R.id.at_zannum)
        TextView atZannum;
        @BindView(R.id.at_head)
        CircleImageView atHead;
        @BindView(R.id.at_linear)
        LinearLayout at_linear;
        @BindView(R.id.at_circlename)
        TextView atcirclename;
        @BindView(R.id.at_publishtitle)
        TextView atPublishTitle;
        @BindView(R.id.visible_limited)
        ImageView visibleLimited;

        AttentionHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class AttentionHolder18 extends RecyclerView.ViewHolder {
        @BindView(R.id.at_name)
        TextView atName;
        @BindView(R.id.at_time)
        TextView atTime;
        @BindView(R.id.at_commentnum)
        TextView atCommentnum;
        @BindView(R.id.at_zan)
        ImageView atZan;
        @BindView(R.id.at_zannum)
        TextView atZannum;
        @BindView(R.id.at_head)
        CircleImageView atHead;
        @BindView(R.id.at_linear)
        LinearLayout at_linear;
        @BindView(R.id.at_circlename)
        TextView atcirclename;
        @BindView(R.id.at_publishtitle)
        TextView atPublishTitle;
        @BindView(R.id.visible_limited)
        ImageView visibleLimited;
        @BindView(R.id.at_longText_linear)
        LinearLayout atLongTextLinear;
        @BindView(R.id.at_longText_image)
        ImageView typeimage;
        @BindView(R.id.at_longText_name)
        TextView typename;

        AttentionHolder18(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class AttentionHolderTemp extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;


        AttentionHolderTemp(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.valueOf(list.get(position).getType());
    }
}
