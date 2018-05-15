package com.wetime.fanc.home.frag;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeActAdapterV2;
import com.wetime.fanc.home.bean.HomeItemBeanV2;
import com.wetime.fanc.home.bean.HomeListBeanV2;
import com.wetime.fanc.home.iviews.IGetHomeFocusV2View;
import com.wetime.fanc.home.presenter.GetHomeFocusV2Presenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFocusLazyFragmentV2 extends BaseLazyFragment implements OnRefreshListener, IGetHomeFocusV2View {


    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;

    private GetHomeFocusV2Presenter getHomeFocusV2Presenter;
    private int page = 1;
    private HomeActAdapterV2 adapter;
    private List<HomeItemBeanV2> list = new ArrayList<>();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_focus_v2;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
        Tools.showEmptyLoading(rlContent);
    }

    @Override
    protected void initData() {

        adapter = new HomeActAdapterV2(list, getActivity());

        getHomeFocusV2Presenter = new GetHomeFocusV2Presenter(this);
        getHomeFocusV2Presenter.getHomeFocusList();
        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getHomeFocusV2Presenter.getHomeFocusList();
            }
        });

        rclHome.setAdapter(mAutoLoadMoreAdapter);
        adapter.setOnZanClickLitener((view, position) -> {
            HomeItemBeanV2 beanV2 = list.get(position);
            int i= beanV2.isHas_like()?-1:1;
            beanV2.setLike_num((Integer.valueOf(beanV2.getLike_num())+i)+"");
            beanV2.setHas_like(!beanV2.isHas_like());
            mAutoLoadMoreAdapter.notifyItemChanged(position);
        });

        ((DefaultItemAnimator)rclHome.getItemAnimator()).setSupportsChangeAnimations(false);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        getHomeFocusV2Presenter.getHomeFocusList();
    }

    @Override
    public void onGetHomeFocus(HomeListBeanV2 bean) {
        Tools.hideEmptyLoading(rlContent);
        refreshLayout.finishRefresh();
        if (page == 1) {
            list.clear();
        }
        list.addAll(bean.getData().getList());
        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }else{
            mAutoLoadMoreAdapter.enable();
        }
        mAutoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public int getPage() {
        return page;
    }
}
