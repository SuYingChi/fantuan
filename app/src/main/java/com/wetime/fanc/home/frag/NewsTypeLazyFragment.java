package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.NewsAdapter;
import com.wetime.fanc.home.bean.NewsListBean;
import com.wetime.fanc.home.event.ReFreshNewsTypeEvent;
import com.wetime.fanc.home.iviews.IGetNewsTypeView;
import com.wetime.fanc.home.presenter.GetNewsTypePresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoukang on 2018/1/29.
 */

public class NewsTypeLazyFragment extends BaseLazyFragment implements IGetNewsTypeView, OnRefreshListener, OnLoadmoreListener {
    private String type;
    private GetNewsTypePresenter getNewsTypePresenter;
    private String total = "";
    private int page = 1;
    private RecyclerView rcl;
    private List<NewsListBean.DataBean.ListBean> list;
    private NewsAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private TextView tvRec;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_newstype;
    }

    @Override
    protected void initView() {
        tvRec = mRootView.findViewById(R.id.tv_recoment);
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        adapter = new NewsAdapter(list, getContext());
        rcl = mRootView.findViewById(R.id.rcl_news);
        rcl.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl.setAdapter(adapter);
        adapter.setOnItemClickLitener((view, position) -> {
            Intent goweb = new Intent(getContext(), WebActivity.class);
            goweb.putExtra("url", list.get(position).getArticle_url());
            goweb.putExtra("type", "2");
            goweb.putExtra("title", list.get(position).getNews_name());
            startActivity(goweb);
        });
        getNewsTypePresenter = new GetNewsTypePresenter(this);
    }


    @Override
    protected void initData() {

        refreshLayout.autoRefresh();
    }

    @Override
    public void onGetNews(NewsListBean bean) {
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();

        if (page == 1) {
            list.clear();
        }
        page++;
        total = bean.getData().getPaging().getTotal();
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();


        if (bean.getData().getUpdate_num().equals("0"))
            return;
        tvRec.setVisibility(View.VISIBLE);
        tvRec.setText(String.format("范团为您推荐了%s条新内容", bean.getData().getUpdate_num()));
        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(
                getContext(), R.anim.anim_header);
        tvRec.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvRec.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public String getCid() {
        return type;
    }

    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public String getTotal() {
        return total;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getNewsTypePresenter.getNews();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getNewsTypePresenter.getNews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshNewsTypeEvent event) {
        if (event.getType().equals(type)) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }
}
