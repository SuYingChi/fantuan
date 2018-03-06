package com.wetime.fanc.circle.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.TestAdapter;
import com.wetime.fanc.main.frag.BaseLazyFragment;

import butterknife.BindView;


public class CircleDetailLazyFragment extends BaseLazyFragment {
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle_detail;
    }

    @Override
    protected void initView() {
            rclCircle.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        TestAdapter adapter = new TestAdapter(getContext());
        rclCircle.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }
}
