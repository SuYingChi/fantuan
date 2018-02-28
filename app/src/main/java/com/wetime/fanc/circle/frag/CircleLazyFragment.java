package com.wetime.fanc.circle.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.TestAdapter;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;


public class CircleLazyFragment extends BaseLazyFragment {


    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;
    @BindView(R.id.tablayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    Unbinder unbinder;

    private QBadgeView qBadgeMsg;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"热门动态", "最新动态"};
    private int temp = 9;

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

                Tools.toastInBottom(getContext(), position + "");
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
        TestAdapter adapter = new TestAdapter(getContext());
        rclHome.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
