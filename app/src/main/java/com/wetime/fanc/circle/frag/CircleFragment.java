package com.wetime.fanc.circle.frag;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.AllCircleActivity;
import com.wetime.fanc.circle.act.CircleDetailActivity;
import com.wetime.fanc.circle.adapter.CircleHomeAdapter;
import com.wetime.fanc.circle.adapter.HeadCircleAdapter;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.iviews.IGetCircleHomeView;
import com.wetime.fanc.circle.presenter.GetCircleHomePresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.event.RefreshRedNumEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CircleFragment extends BaseLazyFragment implements OnRefreshListener, IGetCircleHomeView {

    @BindView(R.id.rcl_home)
    RecyclerView rclHome;



    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;


    private CircleHomeAdapter adapter;
    private List<HomeItemBean> mList = new ArrayList<>();


    private String[] sort = {"1", "2"};//    ort : 1 = 按热度排序， 2 = 按发布时间排序
    private int sortPos = 0;
    private GetCircleHomePresenter getCircleHomePresenter;
    private int page = 1;




    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        getCircleHomePresenter = new GetCircleHomePresenter(this);
        getCircleHomePresenter.getCircleHome();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshRedNumEvent event) {
//        qBadgeMsg.setBadgeNumber(event.getNum());
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getCircleHomePresenter.getCircleHome();
        mAutoLoadMoreAdapter.enable();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onGetCircleHome(CircleHomeListBean bean) {
        refreshLayout.finishRefresh();
        if (adapter == null) {
            //列表
            rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new CircleHomeAdapter(bean.getData().getCircles(),mList, getActivity());
            mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    getCircleHomePresenter.getCircleHome();
                }
            });
            rclHome.setAdapter(mAutoLoadMoreAdapter);
//            adapter.notifyDataSetChanged();
        }

        if (page == 1) {
            mList.clear();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }
        mList.addAll(bean.getData().getList());
        mAutoLoadMoreAdapter.notifyDataSetChanged();


        rlEmpty.setVisibility(View.GONE);
        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getSort() {
        return sort[sortPos];
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            rclHome.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }
}
