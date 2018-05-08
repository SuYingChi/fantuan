package com.wetime.fanc.my.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;

import q.rorbin.badgeview.QBadgeView;


public class MyFragment extends BaseLazyFragment {


    private QBadgeView qBadgeMsg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

}
