package com.wetime.fanc.my.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.adapter.MyFriendsPagerAdapter;
import com.wetime.fanc.my.frag.MyFriendsConcernLazyFragment;
import com.wetime.fanc.my.frag.MyFriendsFansLazyFragment;
import com.wetime.fanc.my.frag.MyFriendsLazyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriendsActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.view_line)
    View viewLine;

    private MyFriendsPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mChannels = new ArrayList<>();

    public static void goMyFriendsAct(Context context, int index) {
        Intent go = new Intent(context, MyFriendsActivity.class);
        go.putExtra("index", index);
        context.startActivity(go);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        viewLine.setVisibility(View.GONE);
        tvTitle.setText("我的好友");

        mChannels.add("好友");
        mChannels.add("关注");
        mChannels.add("粉丝");

        mFragments.add(new MyFriendsLazyFragment());
        mFragments.add(new MyFriendsConcernLazyFragment());
        mFragments.add(new MyFriendsFansLazyFragment());

        mAdapter = new MyFriendsPagerAdapter(getSupportFragmentManager(), mFragments, mChannels);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mAdapter);
        vp.setCurrentItem(getIntent().getIntExtra("index", 0));

        tablayout.setViewPager(vp);
        tablayout.setCurrentTab(1, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
