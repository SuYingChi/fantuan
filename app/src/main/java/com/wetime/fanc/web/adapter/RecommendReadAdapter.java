package com.wetime.fanc.web.adapter;

import android.content.Context;

import com.wetime.fanc.R;
import com.wetime.fanc.web.NewsDetailWebActivity;
import com.wetime.fanc.web.bean.NewsWebBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RecommendReadAdapter extends CommonAdapter<NewsWebBean.DataBean.ListBean> {

    public RecommendReadAdapter(Context context, int layoutId, List<NewsWebBean.DataBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NewsWebBean.DataBean.ListBean listBean, int position) {
        holder.setText(R.id.item_text, listBean.getName());
        holder.setOnClickListener(R.id.item_text, v -> {
            NewsDetailWebActivity.starToWeb(mContext, listBean.getArticle_url(), listBean.getId(), listBean.getLikeNum(), false);
        });
    }
}
