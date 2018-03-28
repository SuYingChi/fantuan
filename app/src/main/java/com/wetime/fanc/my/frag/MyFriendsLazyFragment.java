package com.wetime.fanc.my.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.bean.MyNewsListBean;
import com.wetime.fanc.my.iviews.IGetMyNewsView;
import com.wetime.fanc.my.presenter.GetMyNewsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MyFriendsLazyFragment extends MyFriendsBaseLazyFragment{

    @Override
    protected int getType() {
        return 0;
    }
}
