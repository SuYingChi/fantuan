package com.wetime.fanc.news.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
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
import com.wetime.fanc.home.iviews.IGetNewsTypeView;
import com.wetime.fanc.home.presenter.GetNewsTypePresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.news.act.RecomentFocusActivity;
import com.wetime.fanc.news.bean.NewsListBean;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.SimpleCatchKey;
import com.wetime.fanc.utils.Tools;

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

public class NewsTypeLazyFragment extends BaseLazyFragment implements IGetNewsTypeView, OnRefreshListener {
    @BindView(R.id.rl_no_focus)
    RelativeLayout rlNoFocus;
    Unbinder unbinder;
    @BindView(R.id.rl_no_login)
    RelativeLayout rlNoLogin;
    private String type;
    private GetNewsTypePresenter getNewsTypePresenter;
    private String total = "";
    private int page = 1;
    private RecyclerView rcl;
    private List<HomeItemBean> list = new ArrayList<>();
    //    private List<SpecialTopicBean.DataBean.ListBean> mlist = new ArrayList<>();
    private HomeItemAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private TextView tvRec;
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
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
        //预先加载缓存
        String catchStr = spu.getValue(SimpleCatchKey.catchkey_news + type);
        if (!TextUtils.isEmpty(catchStr)) {
            NewsListBean bean = GsonUtils.getGsonInstance().fromJson(catchStr, NewsListBean.class);
            onGetNews(bean);
        }

    }


    @Override
    protected void initData() {
        if (type.equals("-1") && TextUtils.isEmpty(spu.getToken())) {
            if (rlNoLogin != null) rlNoLogin.setVisibility(View.VISIBLE);
            mRootView.findViewById(R.id.tv_gologin).setOnClickListener(v -> {
                Intent go = new Intent(getContext(), LoginActivity.class);
                startActivity(go);
            });
        } else {
            if (Tools.isNetworkAvailable(mActivity))
                refreshLayout.autoRefresh();
        }

    }

    @Override
    public void onGetNews(NewsListBean bean) {
        if (bean.getError() != 0) {
            refreshLayout.finishRefresh(2000);
            return;
        }
        // 推荐空页面
        if (type.equals("-1")) {
            if (rlNoLogin != null) rlNoLogin.setVisibility(View.GONE);
            if (bean.getData().getList().size() == 0) {
                if (rlNoFocus != null)
                    rlNoFocus.setVisibility(View.VISIBLE);
                mRootView.findViewById(R.id.tv_gofocus).setOnClickListener(v -> {
                    Intent goFocus = new Intent(getContext(), RecomentFocusActivity.class);
                    startActivity(goFocus);
                });
            } else {
                if (rlNoFocus != null)
                    rlNoFocus.setVisibility(View.GONE);
            }
        }

        refreshLayout.finishRefresh(1000);

        if (page == 1) {
            list.clear();
            //推荐 特殊的头部
            if (type.equals("-1")) {
                HomeItemBean headbean = new HomeItemBean();
                headbean.setType(9000);
                list.add(headbean);
            }
            mAutoLoadMoreAdapter.enable();
            // 缓存
            spu.setValue(SimpleCatchKey.catchkey_news + type, GsonUtils.getGsonInstance().toJson(bean));
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
                getContext(), R.anim.anim_header_news);
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
    public void onMessageEvent(ReFreshNewsEvent event) {
        if (mIsVisible) {
            rcl.scrollToPosition(0);
            refreshLayout.autoRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        if (type.equals("-1"))
            refreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        if (type.equals("-1")) {
            if (rlNoLogin != null) rlNoLogin.setVisibility(View.VISIBLE);
            mRootView.findViewById(R.id.tv_gologin).setOnClickListener(v -> {
                Intent go = new Intent(getContext(), LoginActivity.class);
                startActivity(go);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
