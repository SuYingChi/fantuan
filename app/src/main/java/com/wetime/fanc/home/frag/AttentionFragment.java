package com.wetime.fanc.home.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.AttentionAdapter;
import com.wetime.fanc.home.bean.HeadAttentionBean;
import com.wetime.fanc.home.event.ReFreshCircleEvent;
import com.wetime.fanc.home.iviews.IAttentionView;
import com.wetime.fanc.home.presenter.AttentionFragmentPresenter;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2018/5/3.
 */

public class AttentionFragment extends BaseLazyFragment implements IAttentionView, OnRefreshListener {

    @BindView(R.id.rcl_home_attention)
     RecyclerView rcl;
    @BindView(R.id.refreshLayout_attention)
     SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
     RelativeLayout rlEmpty;
     AttentionFragmentPresenter attentionFragmentPresenter;
     List<HeadAttentionBean.DataBean.ListBean> list=new ArrayList<HeadAttentionBean.DataBean.ListBean>();
     AttentionAdapter adapter;
     int page=1;
     AutoLoadMoreAdapter autoLoadMoreAdapter;

 /*   @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_attention, null);
        ButterKnife.bind(this, v);
        attentionFragmentPresenter = new AttentionFragmentPresenter(this);
        rcl.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new AttentionAdapter(list, getActivity());
        rcl.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                attentionFragmentPresenter.getAttentionPage();
            }
        });

        rcl.setAdapter(autoLoadMoreAdapter);
        adapter.notifyDataSetChanged();
        return v;
    }*/

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_first_attention;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
//        rclHome.setFocusableInTouchMode(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadMore(false);
        attentionFragmentPresenter = new AttentionFragmentPresenter(this);
    }

    @Override
    protected void refresh() {
        onRefresh(refreshLayout);
    }

    @Override
    public void onGetAttention(HeadAttentionBean bean) {
/*
        if (page == 1) {
            list.clear();
            //记录缓存
            spu.setValue(SimpleCatchKey.catchkey_attention, GsonUtils.getGsonInstance().toJson(bean));
        }
        if (bean.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }
        list.addAll(bean.getData().getList());
        autoLoadMoreAdapter.notifyDataSetChanged();

        if (page > 1) {
            autoLoadMoreAdapter.finishLoading();
        }
        refreshLayout.finishRefresh(1000);
*/

        refreshLayout.finishRefresh(1000);
        if (adapter == null) {
            rcl.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new AttentionAdapter(list, getActivity());
            autoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
            autoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
                @Override
                public void onRetry() {

                }

                @Override
                public void onLoadMore() {
                    page++;
                    attentionFragmentPresenter.getAttentionPage();
                }
            });
            rcl.setAdapter(autoLoadMoreAdapter);
        }


        if (page == 1) {
            list.clear();
        } else {
            autoLoadMoreAdapter.finishLoading();
        }

        list.addAll(bean.getData().getList());
        if (bean.getData().getPaging().isIs_end()) {
            autoLoadMoreAdapter.disable();
        }
        if (list.size() > 0) {
            rlEmpty.setVisibility(View.GONE);
        } else {
            rlEmpty.setVisibility(View.VISIBLE);
        }
        autoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public String getPage() {
        return String.valueOf(page);
    }

    @Override
    public void onRefresh( RefreshLayout refreshLayout) {
        page = 1;
        attentionFragmentPresenter.getAttentionPage();
    }
    @Override
    public void onNetError() {
        super.onNetError();
       /* refreshLayout.finishRefresh(1000);
        refreshLayout.finishLoadMore();
        if (!TextUtils.isEmpty(spu.getValue(SimpleCatchKey.catchkey_attention)) && page == 1) {
            HeadAttentionBean bean = GsonUtils.getGsonInstance()
                    .fromJson(spu.getValue(SimpleCatchKey.catchkey_attention), HeadAttentionBean.class);
            onGetAttention(bean);
        }*/
        refreshLayout.finishRefresh();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReFreshCircleEvent event) {
        if (mIsVisible) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onError(String s) {
        super.onError(s);
        refreshLayout.finishRefresh();
        rlEmpty.setVisibility(View.VISIBLE);
    }
}
