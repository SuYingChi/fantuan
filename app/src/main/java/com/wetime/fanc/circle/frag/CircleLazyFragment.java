package com.wetime.fanc.circle.frag;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.wetime.fanc.circle.act.PublishActActivity;
import com.wetime.fanc.circle.adapter.HeadCircleAdapter;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.iviews.IGetCircleHomeView;
import com.wetime.fanc.circle.presenter.GetCircleHomePresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.home.event.RefreshRedNumEvent;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;


public class CircleLazyFragment extends BaseLazyFragment implements OnRefreshListener, IGetCircleHomeView {


    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.tablayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;

    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    private int temp = 9;


    private HomeItemAdapter adapter;
    private List<HomeItemBean> mList = new ArrayList<>();

    private QBadgeView qBadgeMsg;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"热门动态", "最新动态"};


    private String[] sort = {"1", "2"};//    ort : 1 = 按热度排序， 2 = 按发布时间排序
    private int sortPos = 0;
    private GetCircleHomePresenter getCircleHomePresenter;
    private int page = 1;

    private List<CircleHomeListBean.DataBean.CirclesBean> circllist = new ArrayList<>();
    private HeadCircleAdapter circleAdapter;

    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        qBadgeMsg = new QBadgeView(getContext());
        qBadgeMsg.setBadgeTextSize(11, true);
        qBadgeMsg.bindTarget(ivMsg);

        for (String mTitle : mTitles) {
            mTabEntities.add(new TabEntity(mTitle));
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setCurrentTab(0);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                sortPos = position;
                onRefresh(refreshLayout);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadmore(false);
        //头部
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        rclCircle.setLayoutManager(manager);
        circleAdapter = new HeadCircleAdapter(circllist, getContext());
        rclCircle.setAdapter(circleAdapter);
        circleAdapter.setOnItemClickLitener((view, position) -> {
            if (circllist.get(position).getId().equals("0")) {
                Intent goAll = new Intent(getContext(), AllCircleActivity.class);
                startActivity(goAll);
            } else {
                Intent goCircle = new Intent(getContext(), CircleDetailActivity.class);
                goCircle.putExtra("id", circllist.get(position).getId());
                startActivity(goCircle);
            }

        });

        //列表
        rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeItemAdapter(mList, getActivity(), true);

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
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void initData() {
        getCircleHomePresenter = new GetCircleHomePresenter(this);
        getCircleHomePresenter.getCircleHome();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshRedNumEvent event) {
        qBadgeMsg.setBadgeNumber(event.getNum());
    }

    @OnClick({R.id.iv_msg, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                Tools.goWeb(getContext(), Const.MSG_URL);
                break;
            case R.id.iv_edit:
                if (spu.getToken().equals("")) {
                    Intent gologin = new Intent(getContext(), LoginActivity.class);
                    startActivity(gologin);
                } else {
                    Intent goPublish = new Intent(getContext(), PublishActActivity.class);
                    startActivity(goPublish);
                }
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getCircleHomePresenter.getCircleHome();
        mAutoLoadMoreAdapter.enable();
    }

//    @Override
//    public void onLoadmore(RefreshLayout refreshlayout) {
//        page++;
//        getCircleHomePresenter.getCircleHome();
//
//    }

    @Override
    public void onGetCircleHome(CircleHomeListBean bean) {
//        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();

        if (page == 1) {
            mList.clear();
            circllist.clear();
            circllist.addAll(bean.getData().getCircles());
            circleAdapter.notifyDataSetChanged();
        }
//        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
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

}
