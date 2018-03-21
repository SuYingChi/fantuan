package com.wetime.fanc.my.frag;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.adapter.MyShopAdapter;
import com.wetime.fanc.my.bean.MyShopListBean;
import com.wetime.fanc.my.iviews.IGetMyShopView;
import com.wetime.fanc.my.presenter.GetMyShopPresenter;
import com.wetime.fanc.shop.act.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyCollectShopLazyFragment extends BaseLazyFragment
        implements OnLoadMoreListener, OnRefreshListener, IGetMyShopView {

    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;


    private int page = 1;
    private GetMyShopPresenter getMyShopPresenter;
    private List<MyShopListBean.DataBean.ListBean> list;
    private MyShopAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_myshop;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initData() {
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new MyShopAdapter(list, getActivity());
        rclCircle.setAdapter(adapter);
        adapter.setOnItemClickLitener((view, position) -> {
            Intent go = new Intent(getContext(), ShopDetailActivity.class);
            go.putExtra("mid", list.get(position).getMid());
            startActivity(go);
        });
        getMyShopPresenter = new GetMyShopPresenter(this);
        getMyShopPresenter.getMyShop();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getMyShopPresenter.getMyShop();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getMyShopPresenter.getMyShop();
    }


    @Override
    public void onGetMyShop(MyShopListBean bean) {
        if (page == 1) {
            list.clear();

        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        if (bean.getData().getPaging().getTotal() != 0) {
            rlEmpty.setVisibility(View.GONE);
        }else {
            rlEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }
}
