package com.wetime.fanc.circle.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.bean.ReplyCommBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class CommentReplyAdapter extends CommonAdapter<ReplyCommBean.DataBean.ListBean> {

    private String commentId = "";
    private int position;

    public CommentReplyAdapter(Context context, int layoutId, List<ReplyCommBean.DataBean.ListBean> datas, String id, int position) {
        super(context, layoutId, datas);
        commentId = id;
        this.position = position;
    }


    @Override
    protected void convert(ViewHolder holder, ReplyCommBean.DataBean.ListBean listBean, int position) {

        TextView textView = (TextView) holder.getView(R.id.reply_tv);
        if (listBean.getUid().equals("yuxun")) {
            textView.setText("查看更多");
            Drawable top = mContext.getResources().getDrawable(R.drawable.icon_v2_more);
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, top, null);
            textView.setTextColor(Color.parseColor("#1EB0FD"));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LongDetailActivity) mContext).getCommReply(commentId, CommentReplyAdapter.this.position);
                }
            });
            return;
        }else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            textView.setTextColor(Color.parseColor("#333333"));
        }

        if (TextUtils.isEmpty(listBean.getPusername())) {
            SpannableString s1 = new SpannableString(listBean.getUsername() + ": " + listBean.getContent());
            s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.text_blue)), 0, listBean.getUsername().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View arg0) {
                    if (mContext instanceof LongDetailActivity) {
                        new Handler().postDelayed(() -> {
                            ((LongDetailActivity) mContext).toId = listBean.getUid();
                            ((LongDetailActivity) mContext).etContent.setHint("回复 " + listBean.getUsername());
                            ((LongDetailActivity) mContext).showKeyborad();
                        }, 0);
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }

            }, 0, listBean.getUsername().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(s1);
        } else {
            SpannableString s1 = new SpannableString(listBean.getUsername() + "回复" + listBean.getPusername() + ": " + listBean.getContent());
            s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.text_blue)),
                    0, listBean.getUsername().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View arg0) {
                    if (mContext instanceof LongDetailActivity) {
                        new Handler().postDelayed(() -> {
                            ((LongDetailActivity) mContext).toId = listBean.getUid();
                            ((LongDetailActivity) mContext).etContent.setHint("回复 " + listBean.getUsername());
                            ((LongDetailActivity) mContext).showKeyborad();
                        }, 0);
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }

            }, 0, listBean.getUsername().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.text_blue)),
                    listBean.getUsername().length() + 2, listBean.getUsername().length() + 2 + listBean.getPusername().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            s1.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View arg0) {
                    if (mContext instanceof LongDetailActivity) {
                        new Handler().postDelayed(() -> {
                            ((LongDetailActivity) mContext).toId = listBean.getPuid();
                            ((LongDetailActivity) mContext).etContent.setHint("回复 " + listBean.getPusername());
                            ((LongDetailActivity) mContext).showKeyborad();
                        }, 0);
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ContextCompat.getColor(mContext, R.color.text_blue));//设置颜色
                    ds.setUnderlineText(false);//去掉下划线
                }

            }, listBean.getUsername().length() + 2, listBean.getUsername().length() + 2 + listBean.getPusername().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(s1);

        }

    }
}
