package com.wetime.fanc.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.wetime.fanc.R;
import com.wetime.fanc.customview.recycview.NestedScrollingLinearLayoutManager;
import com.wetime.fanc.customview.recycview.NestedScrollingRecyclerView;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.iviews.IGetAllCommentView;
import com.wetime.fanc.news.presenter.GetAllCommentPresenter;
import com.wetime.fanc.web.adapter.NewsWebAdapter;
import com.wetime.fanc.web.bean.NewsWebBean;
import com.wetime.fanc.web.iviews.IGetRecommenDarticles;
import com.wetime.fanc.web.presenter.GetAllRecommenDarticles;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsDetailWebActivity extends BaseActivity implements IGetRecommenDarticles, IGetAllCommentView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    public String url = "";
    public String id = "";
    public String like_num = "";
    public boolean has_like;
    @BindView(R.id.web_recyclerview)
    NestedScrollingRecyclerView webRecyclerview;
    private GetAllRecommenDarticles getAllRecommenDarticles;
    private GetAllCommentPresenter getAllCommentPresenter;
    private NewsWebAdapter adapter;

    public static void starToWeb(Context context, String url, String id, String like_num, boolean has_like) {
        Intent intent = new Intent(context, NewsDetailWebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("id", id);
        intent.putExtra("like_num", like_num);
        intent.putExtra("has_like", has_like);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetailweb);
        ButterKnife.bind(this);
        initView();
        iniData();
    }

    private void iniData() {
        getAllRecommenDarticles = new GetAllRecommenDarticles(this);
        getAllRecommenDarticles.getRecommenDarticles(id);
        getAllCommentPresenter = new GetAllCommentPresenter(this);
        getAllCommentPresenter.getAllComment(id, String.valueOf(1));

    }

    private void initView() {
        url = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        like_num = getIntent().getStringExtra("like_num");
        has_like = getIntent().getBooleanExtra("has_like", false);
        webRecyclerview.setLayoutManager(new NestedScrollingLinearLayoutManager(this));

        adapter = new NewsWebAdapter(this,
                "https://www.baidu.com/",
                null, null);
        webRecyclerview.setAdapter(adapter);
    }


    @Override
    public void onRecommenDarticles(NewsWebBean bean) {
        if (bean.getError() == 0) {
            adapter.setData(bean.getData().getList());
        } else {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetAllComment(CommentBean bean) {
        if (bean.getError() != 0) {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.setDataComm(bean.getData().getList());
    }

    @Override
    public void onClickLike(CommentBean bean) {

    }

    @Override
    public void onSendCommont(GalleryCommentBean bean) {

    }

    @Override
    public void onDeleteCommont(ErrorBean bean) {

    }
}
