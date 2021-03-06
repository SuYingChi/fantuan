package com.wetime.fanc.home.frag;


import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;


import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;

import com.wetime.fanc.home.adapter.HomePageCirclesAdapter;
import com.wetime.fanc.home.bean.HomePageCircleBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.iviews.IHomePageCircleView;
import com.wetime.fanc.home.presenter.HomePageCircleFragmentPresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2018/5/3.
 */

public class HomePageCircleFragment extends BaseLazyFragment implements IHomePageCircleView, OnRefreshListener {

    @BindView(R.id.refresh_circle_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.circles_recommend)
    RecyclerView hotCircleRcl;
    @BindView(R.id.homepage_root)
    RelativeLayout homepage_root;
    HomePageCircleFragmentPresenter homePageCircleFragmentPresenter;
    List<HomePageCircleBean.DataBean.ListBean> myCircles = new ArrayList<HomePageCircleBean.DataBean.ListBean>();
    List<HomePageCircleBean.DataBean.NotmissBean> hotCircles = new ArrayList<HomePageCircleBean.DataBean.NotmissBean>();
    HomePageCirclesAdapter homePageCirclesAdapter;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_page_circle_layout;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        Tools.showEmptyLoading(homepage_root);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        homePageCircleFragmentPresenter = new HomePageCircleFragmentPresenter(this);
    }

    @Override
    protected void initData() {
        homePageCircleFragmentPresenter.getCircles();
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        Tools.hideEmptyLoading(homepage_root);
        if(refreshLayout!=null){
            refreshLayout.finishRefresh();
        }
    }
    @Override
    public void onGetCircle(HomePageCircleBean homePageCircleBean) {
        refreshLayout.finishRefresh(1000);
        Tools.hideEmptyLoading(homepage_root);
        if (homePageCirclesAdapter == null) {
            hotCircleRcl.setLayoutManager(new LinearLayoutManager(getContext()));
            homePageCirclesAdapter = new HomePageCirclesAdapter(hotCircles, myCircles, getActivity());
            hotCircleRcl.setAdapter(homePageCirclesAdapter);
        }
        myCircles.clear();
        hotCircles.clear();
        myCircles.addAll(homePageCircleBean.getData().getList());
        hotCircles.addAll(homePageCircleBean.getData().getNotmiss());
        homePageCirclesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        homePageCircleFragmentPresenter.getCircles();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishRefresh(1000);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            hotCircleRcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishLoadMore(1000);
        Tools.hideEmptyLoading(homepage_root);
    }


}
