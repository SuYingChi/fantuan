package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.customview.GridViewForScrollView;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhoukang on 2018/3/12.
 */

public class ActDetailAdapter extends RecyclerView.Adapter {

    private ActDetailBean actDetailBean;
    private Activity mActivity;
    private OnItemClickLitener mOnItemClickLitener;
    private RecyclerView.ViewHolder holder;

    public ActDetailAdapter(Activity mActivity, ActDetailBean actDetailBean) {
        this.mActivity = mActivity;
        this.actDetailBean = actDetailBean;
    }

    public void setLikeNumber(ClickNumBean likeNumber) {
        if (holder != null) {
            ViewHolder0 holder = (ViewHolder0) this.holder;
            holder.zanciv1.setVisibility(View.VISIBLE);
            holder.zanciv2.setVisibility(View.VISIBLE);
            holder.zanciv3.setVisibility(View.VISIBLE);
            holder.zanNumber.setVisibility(View.VISIBLE);
            if (likeNumber.getData().getList() != null) {
                switch (likeNumber.getData().getList().size()) {
                    case 0:
                        holder.zanciv1.setVisibility(View.GONE);
                        holder.zanciv2.setVisibility(View.GONE);
                        holder.zanciv3.setVisibility(View.GONE);
                        holder.zanNumber.setVisibility(View.GONE);
                        break;
                    case 1:
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(0).getAvatar()).into(holder.zanciv1);
                        holder.zanciv2.setVisibility(View.GONE);
                        holder.zanciv3.setVisibility(View.GONE);
                        break;
                    case 2:
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(0).getAvatar()).into(holder.zanciv1);
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(1).getAvatar()).into(holder.zanciv2);
                        holder.zanciv2.setVisibility(View.GONE);
                        break;
                    case 3:
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(0).getAvatar()).into(holder.zanciv1);
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(1).getAvatar()).into(holder.zanciv2);
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(2).getAvatar()).into(holder.zanciv3);
                        break;
                    default:
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(0).getAvatar()).into(holder.zanciv1);
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(1).getAvatar()).into(holder.zanciv2);
                        Glide.with(mActivity).load(likeNumber.getData().getList().get(2).getAvatar()).into(holder.zanciv3);
                        break;
                }
            } else {
                holder.zanciv1.setVisibility(View.GONE);
                holder.zanciv2.setVisibility(View.GONE);
                holder.zanciv3.setVisibility(View.GONE);
                holder.zanNumber.setVisibility(View.GONE);
            }
            holder.zanNumber.setText("等" + likeNumber.getData().getList().size() + "人点了赞");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ViewHolder0(LayoutInflater.from(mActivity).inflate(R.layout.item_actdetail_head, parent, false));
        } else if (viewType == 1) {
            return new ViewHolder1(LayoutInflater.from(mActivity).inflate(R.layout.item_actdetail_middle, parent, false));
        } else if (viewType == 2) {
            return new ViewHolder2(LayoutInflater.from(mActivity).inflate(R.layout.item_actdetail_comment, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(view -> {
                if (position > 1)
                    mOnItemClickLitener.onItemClick(view, position);
            });
        }
        if (holder instanceof ViewHolder0) {
            this.holder = holder;
            Glide.with(mActivity).load(actDetailBean.getData().getAvatar()).into(((ViewHolder0) holder).ivHead);
            ((ViewHolder0) holder).tvName.setText(actDetailBean.getData().getUsername());
            ((ViewHolder0) holder).tvTime.setText(actDetailBean.getData().getTime());
            ((ViewHolder0) holder).tvContent.setText(actDetailBean.getData().getContent());

            ((ViewHolder0) holder).tvSee.setText(actDetailBean.getData().getRead_num());
            if (TextUtils.isEmpty(actDetailBean.getData().getCircle_name())) {
                ((ViewHolder0) holder).tvPublishtitle.setVisibility(View.GONE);
                ((ViewHolder0) holder).tvCirclename.setVisibility(View.GONE);
            } else {
                ((ViewHolder0) holder).tvPublishtitle.setVisibility(View.VISIBLE);
                ((ViewHolder0) holder).tvCirclename.setVisibility(View.VISIBLE);
                ((ViewHolder0) holder).tvCirclename.setText(actDetailBean.getData().getCircle_name());
            }
            ((ViewHolder0) holder).tvCirclename.setText(actDetailBean.getData().getCircle_name());
            if (TextUtils.isEmpty(actDetailBean.getData().getContent())) {
                ((ViewHolder0) holder).tvContent.setVisibility(View.GONE);
            }

            if (actDetailBean.getData().isIs_follow()) {
                ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
            } else {
                if (actDetailBean.getData().isIs_owner()) {
                    ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
                } else {
                    ((ViewHolder0) holder).tvFocus.setVisibility(View.VISIBLE);
                }
            }

            if (actDetailBean.getData().isIs_owner()) {

            }

            if (actDetailBean.getData().isHas_like()) {
                ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_nor);
            } else {
                ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_n);
            }
            ((ViewHolder0) holder).tvFocus.setOnClickListener(v -> {
                FocusPresenter focusPresenter = new FocusPresenter();
                if (((ActDetailActivity) mActivity).getToken().isEmpty()) {
                    Tools.toastInBottom(mActivity, "请先登录");
                    Intent goLogin = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(goLogin);
                    return;
                }
                actDetailBean.getData().setIs_follow(!actDetailBean.getData().isIs_follow());
                focusPresenter.focusUser(mActivity, Tools.getSpu(mActivity).getToken(),
                        actDetailBean.getData().isIs_follow() ? "1" : "0",
                        actDetailBean.getData().getUid());

                if (actDetailBean.getData().isIs_follow()) {
                    ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
                } else {
                    if (actDetailBean.getData().isIs_owner()) {
                        ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
                    } else {
                        ((ViewHolder0) holder).tvFocus.setVisibility(View.VISIBLE);
                    }
                }

            });

            if (actDetailBean.getData().getCover().size() == 0) {
                ((ViewHolder0) holder).gv.setVisibility(View.GONE);
            } else {
                NineImageGridListActAdapter gvadapter = new NineImageGridListActAdapter(mActivity, actDetailBean.getData().getCover());
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((ViewHolder0) holder).gv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(mActivity);
                if (actDetailBean.getData().getType() == 19) {
                    ((ViewHolder0) holder).gv.setNumColumns(3);
                    ((ViewHolder0) holder).gv.setHorizontalSpacing(6);
                    ((ViewHolder0) holder).gv.setVerticalSpacing(6);
                    params.width = sw - Tools.dip2px(mActivity, 15 + 15);
                    params.width = sw;
                } else if (actDetailBean.getData().getType() == 14) {//四宫格
                    ((ViewHolder0) holder).gv.setNumColumns(2);
                    ((ViewHolder0) holder).gv.setHorizontalSpacing(6);
                    ((ViewHolder0) holder).gv.setVerticalSpacing(6);
//                    int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
//                    params.width = w * 2 + Tools.dip2px(mActivity, 6);//设置当前控件布局的高度
                    params.width = sw;
                } else {//单图
//                    params.width = sw - Tools.dip2px(mActivity, 6 + 6);
                    params.width = sw;
                }

                ((ViewHolder0) holder).gv.setLayoutParams(params);
                ((ViewHolder0) holder).gv.setAdapter(gvadapter);
                gvadapter.notifyDataSetChanged();
                ((ViewHolder0) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, actDetailBean.getData().getCover(), i));
            }


            ((ViewHolder0) holder).ivZan.setOnClickListener(v -> {
                if (actDetailBean.getData().isHas_like()) {
                    ((ActDetailActivity) mActivity).clickLike(false);
                    ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_n);
                } else {
                    ((ActDetailActivity) mActivity).clickLike(true);
                    ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_nor);
                }
                actDetailBean.getData().setHas_like(!actDetailBean.getData().isHas_like());
            });
            ((ViewHolder0) holder).ivComment.setOnClickListener(v -> {
                ((ActDetailActivity) mActivity).showComment();
            });
            ((ViewHolder0) holder).tvCirclename.setOnClickListener(view -> {
                Intent goCircle = new Intent(mActivity, CircleDetailActivity.class);
                goCircle.putExtra("id", actDetailBean.getData().getCircle_id());
                mActivity.startActivity(goCircle);
            });

            ((ViewHolder0) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", actDetailBean.getData().isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", actDetailBean.getData().getUid());
                mActivity.startActivity(go);
            });
            ((ViewHolder0) holder).tvName.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", actDetailBean.getData().isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", actDetailBean.getData().getUid());
                mActivity.startActivity(go);
            });
            if (TextUtils.isEmpty(actDetailBean.getData().getLocation())) {
                ((ViewHolder0) holder).tvAddres.setVisibility(View.GONE);
            } else {
                ((ViewHolder0) holder).tvAddres.setVisibility(View.VISIBLE);
                ((ViewHolder0) holder).tvAddres.setText(actDetailBean.getData().getLocation());
            }
        }
        if (holder instanceof ViewHolder1) {
            if (actDetailBean.getData().getComment_num() == 0) {
                ((ViewHolder1) holder).itemView.setVisibility(View.GONE);
            } else {
                ((ViewHolder1) holder).tvComm.setText(String.format("%s条评论", actDetailBean.getData().getComment_num()));
                ((ViewHolder1) holder).itemView.setVisibility(View.VISIBLE);
            }

        }
        if (holder instanceof ViewHolder2) {
            ActDetailBean.DataBean.CommentListBean bean = actDetailBean.getData().getComment_list().get(position - 2);
            ((ViewHolder2) holder).tvName.setText(bean.getUsername());
            ((ViewHolder2) holder).tvTime.setText(bean.getTime());

            if (actDetailBean.getData().isIs_delete()) {
                ((ViewHolder2) holder).ivdelete.setVisibility(View.VISIBLE);
                ((ViewHolder2) holder).ivdelete.setOnClickListener(v -> {
                    mOnItemClickLitener.onItemClick(v, position);
                });
            } else {
                ((ViewHolder2) holder).ivdelete.setVisibility(View.GONE);
            }

            if (TextUtils.isEmpty(bean.getTo_username())) {
                ((ViewHolder2) holder).tvContent.setText(bean.getContent());
            } else {
                SpannableString s1 = new SpannableString("回复" + bean.getTo_username() + ": " + bean.getContent());
                s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mActivity, R.color.text_blue)),
                        2, 2 + bean.getTo_username().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                s1.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
//                        Intent go = new Intent(mActivity, UserCardActivity.class);
//                        go.putExtra("num", bean.isTo_news() ? "3" : "2");
//                        go.putExtra("index", 0);
//                        go.putExtra("id", bean.getTo_uid());
//                        mActivity.startActivity(go);
                        new Handler().postDelayed(() -> {
                            ((ActDetailActivity) mActivity).toId = bean.getUid();
                            ((ActDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                            ((ActDetailActivity) mActivity).showKeyborad();
                        }, 0);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(ContextCompat.getColor(mActivity, R.color.text_blue));//设置颜色
                        ds.setUnderlineText(false);//去掉下划线
                    }

                }, 2, 2 + bean.getTo_username().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((ViewHolder2) holder).tvContent.setMovementMethod(LinkMovementMethod.getInstance());

                ((ViewHolder2) holder).tvContent.setText(s1);
            }


            ((ViewHolder2) holder).tvContent.setOnClickListener(v -> {
                ((ActDetailActivity) mActivity).toId = bean.getUid();
                ((ActDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                ((ActDetailActivity) mActivity).showKeyborad();
            });
            Glide.with(mActivity).load(bean.getAvatar()).into(((ViewHolder2) holder).ivHead);
            ((ViewHolder2) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            ((ViewHolder2) holder).tvName.setOnClickListener(view -> {
                new Handler().postDelayed(() -> {
                    ((ActDetailActivity) mActivity).toId = bean.getUid();
                    ((ActDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                    ((ActDetailActivity) mActivity).showKeyborad();
                }, 500);
            });

            ((ViewHolder2) holder).commentreply.setLayoutManager(new LinearLayoutManager(mActivity));
//            ((ViewHolder2) holder).commentreply.setAdapter(new CommentReplyAdapter(mActivity, R.layout.item_commen_reply, bean.getReplys().getList()));
        }

    }

    @Override
    public int getItemCount() {
        return 2 + actDetailBean.getData().getComment_list().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 1)
            return 2;
        else
            return position;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_focus)
        TextView tvFocus;
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.iv_onwer)
        ImageView ivOnwer;
        @BindView(R.id.zan_civ1)
        ImageView zanciv1;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.zan_civ2)
        ImageView zanciv2;
        @BindView(R.id.zan_civ3)
        ImageView zanciv3;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_owner)
        TextView tvOwner;
        @BindView(R.id.zan_number)
        TextView zanNumber;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gv)
        GridViewForScrollView gv;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_publishtitle)
        TextView tvPublishtitle;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;


        ViewHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_commentnum)
        TextView tvComm;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.iv_delete)
        RelativeLayout ivdelete;
        @BindView(R.id.comment_reply)
        RecyclerView commentreply;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
