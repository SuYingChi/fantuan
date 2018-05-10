package com.wetime.fanc.web.adapter;

import android.content.Context;

import com.wetime.fanc.R;
import com.wetime.fanc.news.bean.NewsListItemBean;
import com.wetime.fanc.web.NewsDetailWebActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class RecommendReadAdapter extends CommonAdapter<NewsListItemBean> {

    public RecommendReadAdapter(Context context, int layoutId, List<NewsListItemBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NewsListItemBean listBean, int position) {
        holder.setText(R.id.item_text, listBean.getName());
        holder.setOnClickListener(R.id.item_text, v -> {
//            NewsDetailWebActivity.starToWeb(mContext,
//                    listBean.getArticle_url(),
//                    listBean.getId(),
//                    listBean.getId(),
//                    listBean.getName(),
//                    listBean.getAuthor(),
//                    listBean.getAuthor(),
//                    listBean.getArticle_url(),
//                    listBean.getCovers().get(position).getCompress(),
//                    listBean.getLikeNum(),
//                    false);
            NewsDetailWebActivity.starToWeb(mContext, listBean,"null");
        });
    }
}
