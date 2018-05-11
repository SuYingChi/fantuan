package com.wetime.fanc.circle.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.CircleListAdapter;
import com.wetime.fanc.circle.bean.CircleDetailListBean;
import com.wetime.fanc.circle.bean.CircleListBean;
import com.wetime.fanc.circle.iviews.IGetCircleDetailListView;
import com.wetime.fanc.circle.presenter.GetCircleDetailListPresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CircleDetailLazyFragment extends BaseLazyFragment implements IGetCircleDetailListView, OnLoadMoreListener, OnRefreshListener {
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private GetCircleDetailListPresenter getCircleDetailListPresenter;

    private List<CircleListBean.DataBean.ListBean> list = new ArrayList<>();
    private CircleListAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle_detail;
    }

    @Override
    protected void initView() {
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CircleListAdapter(list, getActivity(), true);
        rclCircle.setAdapter(adapter);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        getCircleDetailListPresenter = new GetCircleDetailListPresenter(this);
        getCircleDetailListPresenter.getCircleDetailList();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onGetCircleList(CircleListBean bean) {
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }


    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getType() {
        return getArguments().getString("type");
    }

    @Override
    public String getCircleId() {
        return getArguments().getString("id");
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getCircleDetailListPresenter.getCircleDetailList();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getCircleDetailListPresenter.getCircleDetailList();
    }
}
