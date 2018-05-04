package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class CommentReplyAdapter extends CommonAdapter<ActDetailBean.DataBean.CommentListBean.ReplysBean.ListBean> {

    public CommentReplyAdapter(Context context, int layoutId, List<ActDetailBean.DataBean.CommentListBean.ReplysBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ActDetailBean.DataBean.CommentListBean.ReplysBean.ListBean listBean, int position) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(listBean.getPusername())) {
            builder.append(listBean.getUsername())
                    .append(":")
                    .append(listBean.getContent());
        } else {
            builder.append(listBean.getUsername())
                    .append("回复")
                    .append(listBean.getPusername())
                    .append(": ")
                    .append(listBean.getContent());
//            ClickableSpan clickableSpan = new ClickableSpan() {
//                @Override
//                public void onClick(View view) {
////                    ((ReplyActivity) context).sendReply(replyBean.getId(), replyBean.getComment_id(), replyBean.getUser().getUsername());
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                    super.updateDrawState(ds);
//                    ds.setColor(Color.parseColor("#05a4be"));
//                    ds.setUnderlineText(false);
//                }
//            };
//
//            s1.setSpan(clickableSpan, listBean.getUsername().length() + 2,
//                    listBean.getUsername().length() + 2 + listBean.getPusername().length(),
//                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
////            ((TextView) holder.getView(R.id.reply_tv)).setMovementMethod(LinkMovementMethod.getInstance());

        }

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
//                ((ReplyActivity) context).sendReply(replyBean.getId(), replyBean.getComment_id(), replyBean.getUser().getUsername());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor("#255A96"));
                ds.setUnderlineText(false);
            }
        };
        builder.setSpan(clickableSpan, 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) holder.getView(R.id.reply_tv)).setMovementMethod(LinkMovementMethod.getInstance());
        holder.setText(R.id.reply_tv, String.valueOf(builder));

    }
}
