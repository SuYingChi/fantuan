package com.wetime.fanc.news.adapter;

import android.content.Context;
import android.view.View;

import com.wetime.fanc.R;
import com.wetime.fanc.customview.GoodView;
import com.wetime.fanc.news.bean.CommentTestBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CommentListAdapter extends CommonAdapter<CommentTestBean> {

    private Context context;
    private GoodView mGoodView;

    public CommentListAdapter(Context context, int layoutId, List<CommentTestBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
        mGoodView = new GoodView(context);
    }

    @Override
    protected void convert(ViewHolder holder, CommentTestBean commentTestBean, int position) {
        holder.setText(R.id.comment_name, commentTestBean.getName());
        holder.setText(R.id.comment_content, commentTestBean.getContent());
        holder.setText(R.id.comment_number, commentTestBean.getRecard() + "条回复");
        holder.setText(R.id.comment_good, commentTestBean.getNumber() + "");
        holder.setText(R.id.comment_time, commentTestBean.getTime() + "");
        holder.setOnClickListener(R.id.comment_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoodView.setText("+1");
                mGoodView.show(v);
            }
        });
    }
}
