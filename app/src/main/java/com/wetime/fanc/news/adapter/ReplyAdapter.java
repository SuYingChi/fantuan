package com.wetime.fanc.news.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.GoodView;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.act.ReplyActivity;
import com.wetime.fanc.news.bean.ReplyCommentBean;
import com.wetime.fanc.utils.DialogUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ReplyAdapter extends CommonAdapter<ReplyCommentBean.DataBean.ReplyBean> {
    private Context context;
    private GoodView mGoodView;

    public ReplyAdapter(Context context, int layoutId, List<ReplyCommentBean.DataBean.ReplyBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        mGoodView = new GoodView(context);
    }

    @Override
    protected void convert(ViewHolder holder, ReplyCommentBean.DataBean.ReplyBean replyBean, int position) {
        if (replyBean.isIs_author()) {
            holder.setText(R.id.reply_item_name, "我");
            holder.setVisible(R.id.reply_delete, true);
        } else {
            holder.setText(R.id.reply_item_name, replyBean.getUser().getUsername());
            holder.setVisible(R.id.reply_delete, false);
        }
        holder.setText(R.id.reply_item_good, replyBean.getLike_num());
        holder.setText(R.id.reply_item_time, replyBean.getTime());
        Glide.with(context).load(replyBean.getUser().getAvatar()).into((ImageView) holder.getView(R.id.reply_item_head));
        if (replyBean.isIs_like()) {
            holder.setImageResource(R.id.reply_item_image, R.drawable.ic_homeitem_zan_off_on);
//            holder.setTextColor(R.id.reply_item_good, R.color._ff3f53);
            ((TextView) holder.getView(R.id.reply_item_good)).setTextColor(Color.parseColor("#ff3f53"));
        } else {
            holder.setImageResource(R.id.reply_item_image, R.drawable.ic_homeitem_zan_off_off);
//            holder.setTextColor(R.id.reply_item_good, R.color.bot_gray);
            ((TextView) holder.getView(R.id.reply_item_good)).setTextColor(Color.parseColor("#999999"));

        }

        if (replyBean.getReply_object() != null) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(replyBean.getContent()).append(" //@").append(replyBean.getReply_object().getUser().getUsername()).append(":  ").append(replyBean.getReply_object().getContent());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    ((ReplyActivity) context).sendReply(replyBean.getId(), replyBean.getComment_id(), replyBean.getUser().getUsername());
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#05a4be"));
                    ds.setUnderlineText(false);
                }
            };
            builder.setSpan(clickableSpan, builder.toString().indexOf("@"), builder.toString().indexOf("@") + replyBean.getReply_object().getUser().getUsername().length() + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ((TextView) holder.getView(R.id.reply_item_content)).setText(builder);
            ((TextView) holder.getView(R.id.reply_item_content)).setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(replyBean.getContent());
            holder.setText(R.id.reply_item_content, String.valueOf(builder));
        }
        holder.getConvertView().setOnClickListener(v -> ((ReplyActivity) context).hideInput());
        holder.setOnClickListener(R.id.reply_item_name, v -> {
            ((ReplyActivity) context).sendReply(replyBean.getId(), replyBean.getComment_id(), replyBean.getUser().getUsername());
        });

        holder.setOnClickListener(R.id.reply_delete, v -> {
            DialogUtils.showNormalDialog(mContext, null, "确认删除该评论？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((ReplyActivity) context).deleteReply(replyBean.getId());
                    dialog.dismiss();
                }
            });

        });
        holder.setOnClickListener(R.id.reply_item_linear, v -> {//0、取消点赞1、点赞
            if (mGoodView.isShowing()) return;
            if (replyBean.isIs_like()) {
                mGoodView.setText("-1");
                mGoodView.show(v);
                replyBean.setIs_like(false);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.reply_item_good)).getText().toString());
                holder.setText(R.id.reply_item_good, String.valueOf(i - 1));
                holder.setImageResource(R.id.reply_item_image, R.drawable.ic_homeitem_zan_off_off);
                ((TextView) holder.getView(R.id.reply_item_good)).setTextColor(Color.parseColor("#999999"));
                ((ReplyActivity) context).clickLike(replyBean.getId(), "0");
            } else {
                mGoodView.setText("+1");
                mGoodView.show(v);
                replyBean.setIs_like(true);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.reply_item_good)).getText().toString());
                holder.setText(R.id.reply_item_good, String.valueOf(i + 1));
                holder.setImageResource(R.id.reply_item_image, R.drawable.ic_homeitem_zan_off_on);
                ((TextView) holder.getView(R.id.reply_item_good)).setTextColor(Color.parseColor("#ff3f53"));
                ((ReplyActivity) context).clickLike(replyBean.getId(), "1");
            }
        });
        if (replyBean.isIs_author()) {
            holder.itemView.setOnClickListener(v -> ((ReplyActivity) mContext).showMyReply(replyBean.getId(), replyBean.getComment_id(), replyBean.getUser().getUsername(), position));
        }
        holder.setOnClickListener(R.id.reply_item_head, v -> {
            Intent go = new Intent(context, UserCardActivity.class);
            go.putExtra("num", "3");
            go.putExtra("index", 0);
            go.putExtra("id", replyBean.getUser().getId());
            context.startActivity(go);
        });

    }


}
