package com.wetime.fanc.news.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.GoodView;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.act.CommentActivity;
import com.wetime.fanc.news.act.ReplyActivity;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.utils.DialogUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CommentListAdapter extends CommonAdapter<CommentBean.DataBean.ListBean> {

    private Context context;
    private GoodView mGoodView;

    public CommentListAdapter(Context context, int layoutId, List<CommentBean.DataBean.ListBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        mGoodView = new GoodView(context);
    }

    @Override
    protected void convert(ViewHolder holder, CommentBean.DataBean.ListBean commentTestBean, int position) {
        if (commentTestBean.isIs_author()) {
            holder.setText(R.id.comment_name, "我");
            holder.setVisible(R.id.comment_delete, true);
        } else {
            holder.setText(R.id.comment_name, commentTestBean.getUser().getUsername());
            holder.setVisible(R.id.comment_delete, false);
        }
        holder.setText(R.id.comment_content, commentTestBean.getContent());
        if (commentTestBean.getReply_num().equals("0")) {
            holder.setText(R.id.comment_number, "回复");
        } else {
            holder.setText(R.id.comment_number, commentTestBean.getReply_num() + "条回复");
        }

        holder.setText(R.id.comment_good, commentTestBean.getLike_num());
        holder.setText(R.id.comment_time, commentTestBean.getTime());
        Glide.with(context).load(commentTestBean.getUser().getAvatar()).into((ImageView) holder.getView(R.id.comment_head));
        if (commentTestBean.isIs_like()) {
            holder.setImageResource(R.id.comment_image, R.drawable.ic_homeitem_zan_off_on);
//            holder.setTextColor(R.id.comment_good, R.color._ff3f53);
            ((TextView) holder.getView(R.id.comment_good)).setTextColor(Color.parseColor("#ff3f53"));
        } else {
            holder.setImageResource(R.id.comment_image, R.drawable.ic_homeitem_zan_off_off);
            ((TextView) holder.getView(R.id.comment_good)).setTextColor(Color.parseColor("#999999"));
//            holder.setTextColor(R.id.comment_good, R.color.bot_gray);
        }
        holder.getConvertView().setOnClickListener(v -> ((CommentActivity) context).hideInput());
        holder.setOnClickListener(R.id.comment_delete, v -> {
            DialogUtils.showNormalDialog(mContext, null, "确认删除该评论？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((CommentActivity) context).deleteComment(commentTestBean.getId());
                    dialog.dismiss();
                }
            });

        });
        holder.setOnClickListener(R.id.comment_linear, v -> {//0、取消点赞1、点赞
            if (mGoodView.isShowing()) return;
            if (commentTestBean.isIs_like()) {
                mGoodView.setText("-1");
                mGoodView.show(v);
                commentTestBean.setIs_like(false);
                holder.setImageResource(R.id.comment_image, R.drawable.ic_homeitem_zan_off_off);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.comment_good)).getText().toString());
                holder.setText(R.id.comment_good, String.valueOf(i - 1));
                ((TextView) holder.getView(R.id.comment_good)).setTextColor(Color.parseColor("#999999"));
                ((CommentActivity) context).clickLike(commentTestBean.getId(), "0");
            } else {
                mGoodView.setText("+1");
                mGoodView.show(v);
                commentTestBean.setIs_like(true);
                holder.setImageResource(R.id.comment_image, R.drawable.ic_homeitem_zan_off_on);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.comment_good)).getText().toString());
                holder.setText(R.id.comment_good, String.valueOf(i + 1));
                ((TextView) holder.getView(R.id.comment_good)).setTextColor(Color.parseColor("#ff3f53"));
                ((CommentActivity) context).clickLike(commentTestBean.getId(), "1");
            }

        });

        holder.setOnClickListener(R.id.comment_number, v -> {
            ReplyActivity.startToReplyActivity(context, commentTestBean);
        });
        holder.setOnClickListener(R.id.comment_head, v -> {
            Intent go = new Intent(context, UserCardActivity.class);
            go.putExtra("num", "3");
            go.putExtra("index", 0);
            go.putExtra("id", commentTestBean.getUser().getId());
            context.startActivity(go);
        });
        holder.getConvertView().setOnClickListener(v -> {
            ReplyActivity.startToReplyActivity(context, commentTestBean);
        });

    }
}
