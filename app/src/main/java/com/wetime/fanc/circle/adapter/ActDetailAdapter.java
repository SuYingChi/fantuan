package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.bean.ActDetailBean;
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

    public ActDetailAdapter(Activity mActivity, ActDetailBean actDetailBean) {
        this.mActivity = mActivity;
        this.actDetailBean = actDetailBean;
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
            Glide.with(mActivity).load(actDetailBean.getData().getAvatar()).into(((ViewHolder0) holder).ivHead);
            ((ViewHolder0) holder).tvName.setText(actDetailBean.getData().getUsername());
            ((ViewHolder0) holder).tvTime.setText(actDetailBean.getData().getTime());
            ((ViewHolder0) holder).tvContent.setText(actDetailBean.getData().getContent());

            ((ViewHolder0) holder).tvSee.setText(actDetailBean.getData().getRead_num());
            ((ViewHolder0) holder).tvCirclename.setText(actDetailBean.getData().getCircle_name());
            if (TextUtils.isEmpty(actDetailBean.getData().getContent())) {
                ((ViewHolder0) holder).tvContent.setVisibility(View.GONE);
            }
            if (actDetailBean.getData().isIs_owner()) {
                ((ViewHolder0) holder).tvFocus.setVisibility(View.GONE);
            } else {
                ((ViewHolder0) holder).tvFocus.setVisibility(View.VISIBLE);
            }

            if (actDetailBean.getData().isIs_follow()) {
                ((ViewHolder0) holder).tvFocus.setText("已关注");
                ((ViewHolder0) holder).tvFocus.setTextColor(ContextCompat.getColor(mActivity, R.color.text_hint));
                ((ViewHolder0) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_gray_circle);
            } else {
                ((ViewHolder0) holder).tvFocus.setText("关注");
                ((ViewHolder0) holder).tvFocus.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                ((ViewHolder0) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_red_corner);
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
                    ((ViewHolder0) holder).tvFocus.setText("已关注");
                    ((ViewHolder0) holder).tvFocus.setTextColor(ContextCompat.getColor(mActivity, R.color.text_hint));
                    ((ViewHolder0) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                } else {
                    ((ViewHolder0) holder).tvFocus.setText("关注");
                    ((ViewHolder0) holder).tvFocus.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                    ((ViewHolder0) holder).tvFocus.setBackgroundResource(R.drawable.bg_btn_red_corner);
                }
            });

            if (actDetailBean.getData().getCover().size() == 0) {
                ((ViewHolder0) holder).gv.setVisibility(View.GONE);
            } else {
                NineImageGridListAdapter gvadapter = new NineImageGridListAdapter(mActivity, actDetailBean.getData().getCover());
                //九宫格
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((ViewHolder0) holder).gv.getLayoutParams();
                //获取当前控件的布局对象
                int sw = Tools.getScreenW(mActivity);
                if (actDetailBean.getData().getType() == 19) {
                    ((ViewHolder0) holder).gv.setNumColumns(3);
                    params.width = sw - Tools.dip2px(mActivity, 15 + 15);
                } else if (actDetailBean.getData().getType() == 14) {//四宫格
                    ((ViewHolder0) holder).gv.setNumColumns(2);
                    int w = (sw - Tools.dip2px(mActivity, 15 + 15 + 6 + 6)) / 3;
                    params.width = w * 2 + Tools.dip2px(mActivity, 6);//设置当前控件布局的高度
                } else {//单图
                    params.width = sw - Tools.dip2px(mActivity, 6 + 6);
                }

                ((ViewHolder0) holder).gv.setLayoutParams(params);
                ((ViewHolder0) holder).gv.setAdapter(gvadapter);
//            gvadapter.notifyDataSetChanged();
                ((ViewHolder0) holder).gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mActivity, actDetailBean.getData().getCover(), i));
            }
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
            ((ViewHolder1) holder).tvZannum.setText(String.format("%s人点赞", actDetailBean.getData().getLike_num()));
            CircleImageGridAdapter adapter = new CircleImageGridAdapter(mActivity, actDetailBean.getData().getLike_list());
            ((ViewHolder1) holder).gv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (actDetailBean.getData().getComment_num() == 0) {
                ((ViewHolder1) holder).tvComm.setText("暂无评论");
                ((ViewHolder1) holder).llEmpty.setVisibility(View.VISIBLE);
            } else {
                ((ViewHolder1) holder).tvComm.setText(String.format("%s条评论", actDetailBean.getData().getComment_num()));
                ((ViewHolder1) holder).llEmpty.setVisibility(View.GONE);
            }

        }
        if (holder instanceof ViewHolder2) {
            ActDetailBean.DataBean.CommentListBean bean = actDetailBean.getData().getComment_list().get(position - 2);
            ((ViewHolder2) holder).tvName.setText(bean.getUsername());
            ((ViewHolder2) holder).tvTime.setText(bean.getTime());
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
                        }, 500);
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
            Glide.with(mActivity).load(bean.getAvatar()).into(((ViewHolder2) holder).ivHead);
            ((ViewHolder2) holder).ivHead.setOnClickListener(view -> {
                Intent go = new Intent(mActivity, UserCardActivity.class);
                go.putExtra("num", actDetailBean.getData().isIs_news() ? "3" : "2");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getUid());
                mActivity.startActivity(go);
            });
            ((ViewHolder2) holder).tvName.setOnClickListener(view -> {
//                Intent go = new Intent(mActivity, UserCardActivity.class);
//                go.putExtra("num", actDetailBean.getData().isIs_news() ? "3" : "2");
//                go.putExtra("index", 0);
//                go.putExtra("id", bean.getUid());
//                mActivity.startActivity(go);
                new Handler().postDelayed(() -> {
                    ((ActDetailActivity) mActivity).toId = bean.getUid();
                    ((ActDetailActivity) mActivity).etContent.setHint("回复 " + bean.getUsername());
                    ((ActDetailActivity) mActivity).showKeyborad();
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
        @BindView(R.id.tv_time)
        TextView tvTime;
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
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_commentnum)
        TextView tvComm;
        @BindView(R.id.gv)
        GridViewForScrollView gv;
        @BindView(R.id.ll_empty)
        LinearLayout llEmpty;

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

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
