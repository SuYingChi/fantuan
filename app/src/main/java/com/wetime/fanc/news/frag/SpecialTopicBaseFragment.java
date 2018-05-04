package com.wetime.fanc.news.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.event.ReFreshNewsEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.news.iviews.IGetSpecialNewsTypeView;
import com.wetime.fanc.news.presenter.GetSpecialNewsTypePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhoukang on 2018/1/29.
 */

public class SpecialTopicBaseFragment extends BaseLazyFragment implements OnRefreshListener, IGetSpecialNewsTypeView {
    @BindView(R.id.rl_no_focus)
    RelativeLayout rlNoFocus;
    Unbinder unbinder;
    @BindView(R.id.rl_no_login)
    RelativeLayout rlNoLogin;
    private String id = "0";
    private GetSpecialNewsTypePresenter getNewsTypePresenter;
    private String total = "";
    private int page = 1;
    private RecyclerView rcl;
    private List<HomeItemBean> list;
    private HomeItemAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private TextView tvRec;
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
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
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);

        list = new ArrayList<>();
        adapter = new HomeItemAdapter(list, getActivity());
        rcl = mRootView.findViewById(R.id.rcl_news);
        rcl.setLayoutManager(new LinearLayoutManager(getContext()));
        rcl.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getNewsTypePresenter.getNews(id, String.valueOf(page));
            }
        });

        rcl.setAdapter(mAutoLoadMoreAdapter);
        adapter.notifyDataSetChanged();

        getNewsTypePresenter = new GetSpecialNewsTypePresenter(this);

    }


    @Override
    protected void initData() {

        refreshLayout.autoRefresh();


    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getNewsTypePresenter.getNews(id, String.valueOf(page));
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshNewsEvent event) {
        if (mIsVisible) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGetSpecialNews(NewsListBean bean) {
        refreshLayout.finishRefresh();
        total = bean.getData().getPaging().getTotal();

//        if (page == 1) {
//            list.clear();
//            list.addAll(bean.getData().getList());
//        } else {
//            list.addAll(bean.getData().getList());
//        }

        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }
        mAutoLoadMoreAdapter.notifyDataSetChanged();

    }
}
