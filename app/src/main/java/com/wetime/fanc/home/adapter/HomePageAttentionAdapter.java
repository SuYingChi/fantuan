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
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/5/4.
 */

public class HomePageAttentionAdapter extends RecyclerView.Adapter {

    private final List<HomePageAttentionBean.DataBean.ListBean> list;
    private final Activity activity;
    private final LayoutInflater inflater;

    public HomePageAttentionAdapter(List<HomePageAttentionBean.DataBean.ListBean> list, Activity activity){
      this.list=list;
      this.activity=activity;
      this.inflater = LayoutInflater.from(activity);
  }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          if (viewType == 10 || viewType == 19 ) {
              return new AttentionHolder(inflater.inflate(R.layout.item_attention_layout, parent, false));
            }  else if (viewType == 18) {
                return new AttentionHolder18(inflater.inflate(R.layout.item_attention_layout_type18, parent, false));
            }


        return new AttentionHolderTemp(inflater.inflate(R.layout.item_attention_layout_temp, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomePageAttentionBean.DataBean.ListBean bean = list.get(position);
        int type = Integer.valueOf(bean.getType());
        holder.itemView.setOnClickListener(view -> {
            switch (type) {
                case 10:
                case 19:
                    Intent goDet = new Intent(activity, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    activity.startActivity(goDet);
                    break;
            }
        });
        if (holder instanceof AttentionHolder) {
            AttentionHolder attentionHolder = (AttentionHolder) holder;
            Glide.with(activity).load(bean.getAvatar()).into(attentionHolder.atHead);
            attentionHolder.atName.setText(bean.getUsername());
            attentionHolder.atTime.setText(bean.getTime());
            int max = 100;
            //变蓝色 需求
            if (bean.getContent().length() > max) {
                SpannableString ss = new SpannableString(bean.getContent().substring(0, max) + "...全文");
                ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity, R.color.text_blue)),
                        max + 3, max + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                attentionHolder.atContent.setText(ss);
            } else {
                attentionHolder.atContent.setText(bean.getContent());
            }
            if (TextUtils.isEmpty(bean.getContent())) {
                attentionHolder.atContent.setVisibility(View.GONE);
            } else {
                attentionHolder.atContent.setVisibility(View.VISIBLE);
            }
            if (bean.getCovers().size() == 0) {
                attentionHolder.atGv.setVisibility(View.GONE);
            } else {
                attentionHolder.atGv.setVisibility(View.VISIBLE);
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(activity, list.get(position).getCovers());
                attentionHolder.atGv.setAdapter(gvadapter);
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) attentionHolder.atGv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(activity);
                if (type == 19) {
                    attentionHolder.atGv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(activity, 15 + 15);
                } else if (type == 14) {//四宫格
                    attentionHolder.atGv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(activity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(activity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(activity, 6 + 6);
                }

                attentionHolder.atGv.setLayoutParams(params);
                gvadapter.notifyDataSetChanged();
                attentionHolder.atGv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(activity, bean.getCovers(), i));
                attentionHolder.atGv.setOnTouchInvalidPositionListener(motionEvent -> false);
            }
//            ((NewsHolder19) holder).tvZannum.setText(bean.getLike_num());
            if (bean.isHas_like()) {
                attentionHolder.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
            } else {
                attentionHolder.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
            }
            attentionHolder.atZan.setOnClickListener(view -> {
                if (Tools.getSpu(activity).getToken().equals("")) {
                    Intent gologin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(gologin);
                } else {
                    ZanActPresenter presenter = new ZanActPresenter();
                    if (bean.isHas_like()) {
                        attentionHolder.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                        presenter.zanAct(bean.getId(), Tools.getSpu(activity).getToken(), "0");
                        bean.setHas_like(false);
                    } else {
                        attentionHolder.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                        presenter.zanAct(bean.getId(), Tools.getSpu(activity).getToken(), "1");
                        bean.setHas_like(true);
                    }
                }
            });

            if (TextUtils.isEmpty(bean.getCircle_name())) {
                attentionHolder.atPublishTitle.setVisibility(View.GONE);
                attentionHolder.at_linear.setVisibility(View.GONE);
            } else {
                attentionHolder.atPublishTitle.setVisibility(View.VISIBLE);
                attentionHolder.atcirclename.setText(bean.getCircle_name());
            }

            attentionHolder.atcirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                goCircle.putExtra("id", bean.getCircle_id());
                activity.startActivity(goCircle);
            });


//            ((NewsHolder19) holder).tvCommentnum.setText(bean.getComment_num());
            attentionHolder.atHead.setOnClickListener(view -> {
                Intent go = new Intent(activity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                activity.startActivity(go);
            });
            /*if (bean.isOwnerOnly()) {
                attentionHolder.visibleLimited.setImageResource(R.drawable.owner_only);
            } else if(bean.isFriendOnly()){
                attentionHolder.visibleLimited.setImageResource(R.drawable.friend_only);
            }*/
        }
         else    if (holder instanceof AttentionHolder18) {
//            ((NewsHolder18) holder).typelinear.setOnItemClickListener(view -> {
//                if (bean.getType() != -1) {
//                    LongDetailActivity.startToLongDetail(mActivity, bean.getId());
//                }
//            });
            AttentionHolder18 attentionHolder18 = (AttentionHolder18)holder;
                ((AttentionHolder18) holder).itemView.setOnClickListener(view -> {
                    if (String.valueOf(bean.getType()) != -1+"") {
                        LongDetailActivity.startToLongDetail(activity, bean.getId());
                    }
                });
                Glide.with(activity).load(bean.getAvatar())
                        .into(attentionHolder18.atHead);
                attentionHolder18.atName.setText(bean.getUsername());
                attentionHolder18.atTime.setText(bean.getTime());

                attentionHolder18.typename.setText(bean.getTitle());
                if (bean.getCovers() == null || bean.getCovers().size() == 0) {
                } else {
                    Glide.with(activity).load(bean.getCovers().get(0).getCompress())
                            .apply(new RequestOptions().centerCrop())
                            .into(attentionHolder18.typeimage);
                }


//            ((NewsHolder18) holder).tvZannum.setText(bean.getLike_num());
                if (bean.isHas_like()) {
                    attentionHolder18.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                } else {
                    attentionHolder18.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                }
                attentionHolder18.atZan.setOnClickListener(view -> {
                    if (Tools.getSpu(activity).getToken().equals("")) {
                        Intent gologin = new Intent(activity, LoginActivity.class);
                        activity.startActivity(gologin);
                    } else {
                        ZanActPresenter presenter = new ZanActPresenter();
                        if (bean.isHas_like()) {
                            attentionHolder18.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                            presenter.zanAct(bean.getId(), Tools.getSpu(activity).getToken(), "0");
                            bean.setHas_like(false);
                            int num = 0;
//                        if (Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) != 0) {
//                            num = Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) - 1;
//                        }
//                        ((NewsHolder18) holder).tvZannum.setText(String.format("%d", num));
                        } else {
                            attentionHolder18.atZan.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                            presenter.zanAct(bean.getId(), Tools.getSpu(activity).getToken(), "1");
                            bean.setHas_like(true);
//                        int num = Integer.valueOf(((NewsHolder18) holder).tvZannum.getText().toString()) + 1;
//                        ((NewsHolder18) holder).tvZannum.setText(String.format("%d", num));
                        }
                    }
                });

                if (TextUtils.isEmpty(bean.getCircle_name())) {
                    attentionHolder18.atPublishTitle.setVisibility(View.GONE);
                    attentionHolder18.at_linear.setVisibility(View.GONE);
                } else {
                    attentionHolder18.atPublishTitle.setVisibility(View.VISIBLE);
                    attentionHolder18.at_linear.setVisibility(View.VISIBLE);
                    attentionHolder18.atcirclename.setText(bean.getCircle_name());
                }

            attentionHolder18.atcirclename.setOnClickListener(view -> {
                    Intent goCircle = new Intent(activity, CircleDetailActivity.class);
                    goCircle.putExtra("id", bean.getCircle_id());
                    activity.startActivity(goCircle);
                });


//            ((NewsHolder18) holder).tvCommentnum.setText(bean.getComment_num());
               attentionHolder18.atHead.setOnClickListener(view -> {
                    Intent go = new Intent(activity, UserCardActivity.class);
                    go.putExtra("num", bean.isIs_news() ? "3" : "2");
                    go.putExtra("index", 0);
                    go.putExtra("id", bean.getUid());
                    activity.startActivity(go);
                });
           /*     if (bean.isCircle_owner()) {
                    ((NewsHolder18) holder).ivOnwer.setVisibility(View.VISIBLE);
                    ((NewsHolder18) holder).ivOnwer.setImageResource(R.drawable.bg_circle_onwer);
                } else {
                    ((NewsHolder18) holder).ivOnwer.setVisibility(View.GONE);
                }
                if (bean.isCircle_admin()) {
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
                }*/
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
        @BindView(R.id.at_content)
        TextView atContent;
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
