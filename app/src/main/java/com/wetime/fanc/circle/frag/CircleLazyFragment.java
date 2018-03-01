package com.wetime.fanc.circle.frag;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.HeadCircleAdapter;
import com.wetime.fanc.circle.bean.CircleHomeListBean;
import com.wetime.fanc.circle.iviews.IGetCircleHomeView;
import com.wetime.fanc.circle.presenter.GetCircleHomePresenter;
import com.wetime.fanc.home.adapter.TestAdapter;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;


public class CircleLazyFragment extends BaseLazyFragment implements OnRefreshListener, OnLoadmoreListener, IGetCircleHomeView {


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




    private QBadgeView qBadgeMsg;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"热门动态", "最新动态"};


    private int temp = 9;
    private TestAdapter adapter;
    private String[] sort = {"1", "2"};//    ort : 1 = 按热度排序， 2 = 按发布时间排序
    private int sortPos = 0;
    private GetCircleHomePresenter getCircleHomePresenter;
    private int page = 1;

    private List<CircleHomeListBean.DataBean.CirclesBean> circllist = new ArrayList<>();
    private HeadCircleAdapter circleAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initView() {
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
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        //头部
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        rclCircle.setLayoutManager(manager);
        circleAdapter = new HeadCircleAdapter(circllist, getContext());
        rclCircle.setAdapter(circleAdapter);
        circleAdapter.setOnItemClickLitener(new HeadCircleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Tools.toastInBottom(getContext(),circllist.get(position).getId());
            }
        });


        rclHome.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TestAdapter(getContext());
        rclHome.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {


        getCircleHomePresenter = new GetCircleHomePresenter(this);
        refreshLayout.autoRefresh();
    }

    @OnClick({R.id.iv_msg, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                Tools.toastInBottom(getContext(), "aaaaa");
//                qBadgeMsg.setVisibility(View.VISIBLE);
                qBadgeMsg.setBadgeNumber(temp++);
                break;
            case R.id.iv_edit:
                qBadgeMsg.setBadgeNumber(temp + 100);
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getCircleHomePresenter.getCircleHome();


        adapter.reFresh();
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        adapter.loadMore();
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onGetCircleHome(CircleHomeListBean bean) {

        if (page == 1) {
            circllist.clear();
            circllist.addAll(bean.getData().getCircles());
            circleAdapter.notifyDataSetChanged();
        }
        rlEmpty.setVisibility(View.GONE);
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
