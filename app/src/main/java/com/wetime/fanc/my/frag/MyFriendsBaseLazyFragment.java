package com.wetime.fanc.my.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseNoLazyFragment;
import com.wetime.fanc.my.adapter.MyFriendsAdapter;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.my.bean.MyFriendsBaseBean;
import com.wetime.fanc.my.iviews.IGetMyFriendsView;
import com.wetime.fanc.my.presenter.GetMyFriendPresenter;

import java.util.ArrayList;

import butterknife.BindView;


public abstract class MyFriendsBaseLazyFragment extends BaseNoLazyFragment
        implements OnLoadMoreListener, OnRefreshListener, IGetMyFriendsView {
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @BindView(R.id.myfriend_base_textview)
    TextView baseTextview;
    private ArrayList<MyFriendsBaseBean.DataBean.ListBean> data = new ArrayList<>();
    private MyFriendsAdapter adapter;
    private boolean isRefresh = true;
    private GetMyFriendPresenter getMyFriendPresenter;
    private int page = 1;

    protected abstract int getType();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_myfriendbase;
    }

    @Override
    protected void initView() {

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        rlEmpty.setVisibility(View.GONE);
        rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));

        switch (getType()) {
            case 0:
                baseTextview.setText("还没有互相关注的好友哦~");
                break;
            case 1:
                baseTextview.setText("你还没有关注任何人");
                break;
            case 2:
                baseTextview.setText("你还没有粉丝，快去发动态吸粉吧~");
                break;
        }
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    protected void initData() {
        getMyFriendPresenter = new GetMyFriendPresenter(this);
        getMyFriendPresenter.getFriends(page);

    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        isRefresh = false;
        page++;
        getMyFriendPresenter.getFriends(page);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        isRefresh = true;
        page = 1;
        getMyFriendPresenter.getFriends(page);
    }

    @Override
    public void onGetUserFriend(MyFriendsBaseBean bean) {

        if (bean.getData().getList().size() == 0) {
            rlEmpty.setVisibility(View.VISIBLE);
        } else {
            rlEmpty.setVisibility(View.GONE);
        }

        if (isRefresh) {
            data = new ArrayList<>();
            data.addAll(bean.getData().getList());
            refreshLayout.finishRefresh();
        } else {
            data.addAll(bean.getData().getList());
            refreshLayout.finishLoadMore();
        }
        if (page >= Integer.parseInt(bean.getData().getPaging().getTotal_page())) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(true);
        }
        adapter = new MyFriendsAdapter(getContext(), R.layout.item_myfriendsbase, data, getType(), getMyFriendPresenter);
        rclCircle.setAdapter(adapter);
    }

    @Override
    public void onAttention(AttentionBean bean) {
        Toast.makeText(mActivity, bean.getMsg(), Toast.LENGTH_SHORT).show();
        onRefresh(refreshLayout);
    }

    @Override
    public String onGetType() {
        return String.valueOf(getType() + 1);
    }

}
