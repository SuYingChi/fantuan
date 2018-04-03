package com.wetime.fanc.news.adapter;

import android.content.Context;
import android.content.Intent;
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
        holder.setText(R.id.comment_name, commentTestBean.getUser().getUsername());
        holder.setText(R.id.comment_content, commentTestBean.getContent());
        holder.setText(R.id.comment_number, commentTestBean.getReply_num() + "条回复");
        holder.setText(R.id.comment_good, commentTestBean.getLike_num());
        holder.setText(R.id.comment_time, commentTestBean.getTime());
        Glide.with(context).load(commentTestBean.getUser().getAvatar()).into((ImageView) holder.getView(R.id.comment_head));
        if (commentTestBean.isIs_like()) {
            holder.setImageResource(R.id.comment_image, R.drawable.good_checked);
        } else {
            holder.setImageResource(R.id.comment_image, R.drawable.good);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommentActivity) context).hideInput();
            }
        });
        holder.setOnClickListener(R.id.comment_linear, v -> {//0、取消点赞1、点赞
            if (mGoodView.isShowing()) return;
            if (commentTestBean.isIs_like()) {
                mGoodView.setText("-1");
                mGoodView.show(v);
                commentTestBean.setIs_like(false);
                holder.setImageResource(R.id.comment_image, R.drawable.good);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.comment_good)).getText().toString());
                holder.setText(R.id.comment_good, String.valueOf(i - 1));
                ((CommentActivity) context).clickLike(commentTestBean.getId(), "0");
            } else {
                mGoodView.setText("+1");
                mGoodView.show(v);
                commentTestBean.setIs_like(true);
                holder.setImageResource(R.id.comment_image, R.drawable.good_checked);
                int i = Integer.parseInt(((TextView) holder.getView(R.id.comment_good)).getText().toString());
                holder.setText(R.id.comment_good, String.valueOf(i + 1));
                ((CommentActivity) context).clickLike(commentTestBean.getId(), "1");
            }

        });

        holder.setOnClickListener(R.id.comment_number, v -> {
            ReplyActivity.startToReplyActivity(context, commentTestBean);
        });
        holder.setOnClickListener(R.id.comment_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(context, UserCardActivity.class);
                go.putExtra("num", "3");
                go.putExtra("index", 0);
                go.putExtra("id", commentTestBean.getUser().getUsername());
                context.startActivity(go);
            }
        });
        holder.getConvertView().setOnClickListener(v -> {
            ReplyActivity.startToReplyActivity(context, commentTestBean);

        });

    }
}
