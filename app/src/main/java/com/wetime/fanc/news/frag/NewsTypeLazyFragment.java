package com.wetime.fanc.news.frag;

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

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.event.ReFreshNewsTypeEvent;
import com.wetime.fanc.home.iviews.IGetNewsTypeView;
import com.wetime.fanc.home.presenter.GetNewsTypePresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.news.bean.NewsListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoukang on 2018/1/29.
 */

public class NewsTypeLazyFragment extends BaseLazyFragment implements IGetNewsTypeView, OnRefreshListener {
    private String type;
    private GetNewsTypePresenter getNewsTypePresenter;
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
        type = bundle.getString("type");
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
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(this);

        list = new ArrayList<>();
        adapter = new HomeItemAdapter(list, getActivity());
        rcl = mRootView.findViewById(R.id.rcl_news);
        rcl.setLayoutManager(new LinearLayoutManager(getContext()));

        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getNewsTypePresenter.getNews();
            }
        });

        rcl.setAdapter(mAutoLoadMoreAdapter);
        adapter.notifyDataSetChanged();

//        rcl.setAdapter(adapter);
//        adapter.setOnItemClickLitener((view, position) -> {
//            Intent goweb = new Intent(getContext(), WebActivity.class);
//            goweb.putExtra("url", list.get(position).getArticle_url());
//            goweb.putExtra("type", "2");
//            goweb.putExtra("title", list.get(position).getNews_name());
//            startActivity(goweb);
//        });
        getNewsTypePresenter = new GetNewsTypePresenter(this);
    }


    @Override
    protected void initData() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void onGetNews(NewsListBean bean) {
        refreshLayout.finishRefresh();
        if (page == 1) {
            list.clear();
        }
        total = bean.getData().getPaging().getTotal();
        list.addAll(bean.getData().getList());
        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }
        mAutoLoadMoreAdapter.notifyDataSetChanged();


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
        refreshLayout.finishRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshNewsTypeEvent event) {
        if (event.getType().equals(type)) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }
}
