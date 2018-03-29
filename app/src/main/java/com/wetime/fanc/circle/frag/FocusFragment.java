package com.wetime.fanc.circle.frag;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.bean.FocusListBean;
import com.wetime.fanc.circle.iviews.IGetMyFocusView;
import com.wetime.fanc.circle.presenter.GetMyFocusPresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;


public class FocusFragment extends BaseLazyFragment implements OnRefreshListener, IGetMyFocusView {
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    private HomeItemAdapter adapter;
    private int page = 1;
    private GetMyFocusPresenter getMyFocusPresenter;
    private ArrayList<HomeItemBean> list = new ArrayList<>();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_focus;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
//        rclHome.setFocusableInTouchMode(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        getMyFocusPresenter = new GetMyFocusPresenter(this);
    }

    @Override
    protected void initData() {
        getMyFocusPresenter.getMyFocus();
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        getMyFocusPresenter.getMyFocus();
//        mAutoLoadMoreAdapter.enable();
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            rclHome.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onMyFocus(FocusListBean bean) {
        refreshLayout.finishRefresh();
        if (adapter == null) {
            rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new HomeItemAdapter(list, getActivity(), true);
            mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    getMyFocusPresenter.getMyFocus();
                }
            });
            rclHome.setAdapter(mAutoLoadMoreAdapter);
        }


        if (page == 1) {
            list.clear();
        } else {
            mAutoLoadMoreAdapter.finishLoading();
        }

        list.addAll(bean.getData().getList());
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }
        if (list.size() > 0) {
            rlEmpty.setVisibility(View.GONE);
        } else {
            rlEmpty.setVisibility(View.VISIBLE);
        }
        mAutoLoadMoreAdapter.notifyDataSetChanged();

    }

    @Override
    public int getPage() {
        return page;
    }

}
