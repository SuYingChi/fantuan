package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeMyCircleAdapter;
import com.wetime.fanc.home.bean.HomeMyCircleBean;
import com.wetime.fanc.home.iviews.IGetHomeMyCircleView;
import com.wetime.fanc.home.presenter.GetHomeMyCirclePresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeCircleLazyFragmentV2 extends BaseLazyFragment implements IGetHomeMyCircleView, OnRefreshListener {


    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    private HomeMyCircleAdapter adapter;
    private GetHomeMyCirclePresenter getHomeMyCirclePresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_mycircle;
    }

    @Override
    protected void initView() {
        Tools.showEmptyLoading(rlContent);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        getHomeMyCirclePresenter =  new GetHomeMyCirclePresenter(this);
        getHomeMyCirclePresenter.getHomeMyCircle();
    }

    @Override
    public void onGetMyCircle(HomeMyCircleBean bean) {

        Tools.hideEmptyLoading(rlContent);
        adapter = new HomeMyCircleAdapter(bean, getContext());
        rclCircle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh(1000);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getHomeMyCirclePresenter .getHomeMyCircle();
    }
}
