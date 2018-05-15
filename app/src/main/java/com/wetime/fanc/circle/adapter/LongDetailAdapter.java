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
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.act.CliclLikeActivity;
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.circle.bean.LongTextBean;
import com.wetime.fanc.circle.bean.ReplyCommBean;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.customview.SpaceItemDecoration;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhoukang on 2018/3/12.
 */

public class LongDetailAdapter extends RecyclerView.Adapter {

    private LongBean actDetailBean;
    private Activity mActivity;
    private OnItemClickLitener mOnItemClickLitener;
    private List<LongTextBean> datas = new ArrayList<>();
    private List<CommentReplyAdapter> holderList = new ArrayList<CommentReplyAdapter>();
    private List<List<ReplyCommBean.DataBean.ListBean>> beanList = new ArrayList<List<ReplyCommBean.DataBean.ListBean>>();
    private int pn = 0;
    private RecyclerView.ViewHolder holder0;

    public LongDetailAdapter(Activity mActivity, LongBean actDetailBean) {
        this.mActivity = mActivity;
        this.actDetailBean = actDetailBean;
        if (actDetailBean == null || actDetailBean.getData().getContents() == null) return;
        for (int i = 0; i < actDetailBean.getData().getContents().size(); i++) {
            LongTextBean longTextBean = actDetailBean.getData().getContents().get(i);
            if (longTextBean.getType().equals("1") && TextUtils.isEmpty(longTextBean.getContent())) {
            } else {
                datas.add(longTextBean);
            }

        }
    }

    public void setLikeNumber(ClickNumBean likeNumber) {
        if (holder0 != null && holder0 instanceof ViewHolder0) {
            ViewHolder0 holder = (ViewHolder0) this.holder0;
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
                        holder.zanciv3.setVisibility(View.GONE);
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
            holder.zanciv1.setOnClickListener(v -> CliclLikeActivity.startToClickLike(mActivity, ((LongDetailActivity) mActivity).getId()));
            holder.zanciv2.setOnClickListener(v -> CliclLikeActivity.startToClickLike(mActivity, ((LongDetailActivity) mActivity).getId()));
            holder.zanciv3.setOnClickListener(v -> CliclLikeActivity.startToClickLike(mActivity, ((LongDetailActivity) mActivity).getId()));
            holder.zanNumber.setOnClickListener(v -> CliclLikeActivity.startToClickLike(mActivity, ((LongDetailActivity) mActivity).getId()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ViewHolder0(LayoutInflater.from(mActivity).inflate(R.layout.item_longdetail_head, parent, false));
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
            this.holder0 = ((ViewHolder0) holder);
            ((ViewHolder0) holder).recyc.setLayoutManager(new LinearLayoutManager(mActivity));


            ((ViewHolder0) holder).recyc.setAdapter(new LongDetailItemAdapte(mActivity, R.layout.item_longdetail, datas));
            int spacingInPixels = mActivity.getResources().getDimensionPixelSize(R.dimen.space);
            ((ViewHolder0) holder).recyc.addItemDecoration(new SpaceItemDecoration(spacingInPixels));


            Glide.with(mActivity).load(actDetailBean.getData().getAvatar()).into(((ViewHolder0) holder).ivHead);
            ((ViewHolder0) holder).tvName.setText(actDetailBean.getData().getUsername());
            ((ViewHolder0) holder).tvTime.setText(actDetailBean.getData().getTime());
            ((ViewHolder0) holder).title_long.setVisibility(View.VISIBLE);
            ((ViewHolder0) holder).title_long.setText(actDetailBean.getData().getTitle());

            ((ViewHolder0) holder).tvSee.setText(actDetailBean.getData().getRead_num() + "次浏览");
            if (TextUtils.isEmpty(actDetailBean.getData().getCircle_name())) {
                ((ViewHolder0) holder).tvPublishtitle.setVisibility(View.GONE);
                ((ViewHolder0) holder).tvCirclename.setVisibility(View.GONE);
            } else {
                ((ViewHolder0) holder).tvPublishtitle.setVisibility(View.VISIBLE);
                ((ViewHolder0) holder).tvCirclename.setVisibility(View.VISIBLE);
                ((ViewHolder0) holder).tvCirclename.setText(actDetailBean.getData().getCircle_name());
            }

            if (actDetailBean.getData().isHas_like()) {
                ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_nor);
            } else {
                ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_n);
            }

            ((ViewHolder0) holder).ivZan.setOnClickListener(v -> {
                if (actDetailBean.getData().isHas_like()) {
                    ((LongDetailActivity) mActivity).clickLike(false);
                    ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_n);
                } else {
                    ((LongDetailActivity) mActivity).clickLike(true);
                    ((ViewHolder0) holder).ivZan.setImageResource(R.drawable.icon_zan_nor);
                }
                actDetailBean.getData().setHas_like(!actDetailBean.getData().isHas_like());
            });
            ((ViewHolder0) holder).ivComment.setOnClickListener(v -> {
                ((LongDetailActivity) mActivity).showComment();
            });


            if (actDetailBean.getData().isIs_owner()) {
                ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
            } else {
                ((ViewHolder0) holder).tvFocus.setVisibility(View.VISIBLE);
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

            ((ViewHolder0) holder).tvFocus.setOnClickListener(v -> {
                FocusPresenter focusPresenter = new FocusPresenter();
                if (((LongDetailActivity) mActivity).getToken().isEmpty()) {
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
            ((ViewHolder0) holder).galleryShare.setOnClickListener(view -> {
                ((LongDetailActivity) mActivity).showPop();
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
            if (actDetailBean.getData().getComment_num().equals("0")) {
                ((ViewHolder1) holder).itemView.setVisibility(View.GONE);
            } else {
                ((ViewHolder1) holder).itemView.setVisibility(View.VISIBLE);
                ((ViewHolder1) holder).tvComm.setText(String.format("%s条评论", actDetailBean.getData().getComment_num()));
            }

        }
        if (holder instanceof ViewHolder2) {
            ActDetailBean.DataBean.CommentListBean bean = actDetailBean.getData().getComment_list().get(position - 2);
            ((ViewHolder2) holder).tvName.setText(bean.getUsername());
            ((ViewHolder2) holder).tvTime.setText(bean.getTime());

            if (actDetailBean.getData().isIs_manager()) {
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
                            ((LongDetailActivity) mActivity).toId = bean.getUid();
                            ((LongDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                            ((LongDetailActivity) mActivity).showKeyborad();
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
            ((ViewHolder2) holder).tvContent.setOnClickListener(v -> new Handler().postDelayed(() -> {
                ((LongDetailActivity) mActivity).toId = bean.getUid();
                ((LongDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                ((LongDetailActivity) mActivity).showKeyborad();
            }, 0));
            Glide.with(mActivity).load(bean.getAvatar()).into(((ViewHolder2) holder).ivHead);

//
//            ((LongDetailActivity) mActivity).getCommReply(bean.getId(), "1", "10", position);

            if (bean.getReplys().getList().size() != 0) {
                ((ViewHolder2) holder).commentreply.setVisibility(View.VISIBLE);
                ((ViewHolder2) holder).commentreply.setLayoutManager(new LinearLayoutManager(mActivity));
                List<ReplyCommBean.DataBean.ListBean> list = bean.getReplys().getList();
                beanList.add(list);
                if (!bean.getReplys().getPaging().isIs_end()) {
                    ReplyCommBean.DataBean.ListBean e = new ReplyCommBean.DataBean.ListBean();
                    e.setUid("yuxun");
                    list.add(e);
                    bean.getReplys().getPaging().setIs_end(true);
                }
                CommentReplyAdapter adapter = new CommentReplyAdapter(mActivity, R.layout.item_commen_reply, list, bean.getId(), pn);
                if (!holderList.contains(adapter)) {
                    holderList.add(adapter);
                    ((ViewHolder2) holder).commentreply.setAdapter(adapter);
                    pn++;
                }
            } else {
                ((ViewHolder2) holder).commentreply.setVisibility(View.GONE);
            }


            ((ViewHolder2) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", bean.isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            ((ViewHolder2) holder).tvName.setOnClickListener(view -> {
                new Handler().postDelayed(() -> {
                    ((LongDetailActivity) mActivity).toId = bean.getUid();
                    ((LongDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                    ((LongDetailActivity) mActivity).showKeyborad();
                }, 500);
            });
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

    public synchronized void setRecAdapter(ReplyCommBean bean, int position) {
        CommentReplyAdapter holder2 = holderList.get(position);
        if (holder2 != null) {
            if (bean.getData().getList().size() != 0) {
                List<ReplyCommBean.DataBean.ListBean> list = bean.getData().getList();
                List<ReplyCommBean.DataBean.ListBean> listBeans = beanList.get(position);
                listBeans.remove(listBeans.size() - 1);
                listBeans.addAll(list);
                if (!bean.getData().getPaging().isIs_end()) {
                    ReplyCommBean.DataBean.ListBean e = new ReplyCommBean.DataBean.ListBean();
                    e.setUid("yuxun");
                    listBeans.add(e);
                }
                holder2.notifyDataSetChanged();
            }
        }
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
        @BindView(R.id.gallery_share)
        ImageView galleryShare;
        @BindView(R.id.tv_addres)
        TextView tvAddres;
        @BindView(R.id.iv_onwer)
        ImageView ivOnwer;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.zan_civ1)
        CircleImageView zanciv1;
        @BindView(R.id.iv_zan)
        ImageView ivZan;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.zan_civ2)
        CircleImageView zanciv2;
        @BindView(R.id.zan_civ3)
        CircleImageView zanciv3;
        @BindView(R.id.zan_number)
        TextView zanNumber;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_publishtitle)
        TextView tvPublishtitle;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.recyc)
        RecyclerView recyc;
        @BindView(R.id.title_long)
        TextView title_long;


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
