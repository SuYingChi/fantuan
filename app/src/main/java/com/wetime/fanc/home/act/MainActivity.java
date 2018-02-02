package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.CustomViewPager;
import com.wetime.fanc.home.adapter.HomeFragmentPagerAdapter;
import com.wetime.fanc.home.event.RefreshRedNunEvent;
import com.wetime.fanc.home.event.SwichFragEvent;
import com.wetime.fanc.home.frag.HomeFragment;
import com.wetime.fanc.home.frag.MyFragment;
import com.wetime.fanc.home.frag.NewsLazyFragment;
import com.wetime.fanc.home.frag.OrderFragment;
import com.wetime.fanc.home.iviews.IBindPushView;
import com.wetime.fanc.home.presenter.BindPushPresenter;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.push.event.RegistPushSuccessEvent;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity implements IBindPushView {
    @BindView(R.id.vp)
    CustomViewPager vp;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.iv_tab1)
    ImageView ivTab1;
    @BindView(R.id.iv_tab0)
    ImageView ivTab0;
    @BindView(R.id.tv_tab0)
    TextView tvTab0;
    @BindView(R.id.tv_tab1)
    TextView tvTab1;
    @BindView(R.id.ll_tab1)
    LinearLayout llTab1;
    @BindView(R.id.iv_tab2)
    ImageView ivTab2;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.ll_tab2)
    LinearLayout llTab2;
    @BindView(R.id.iv_tab3)
    ImageView ivTab3;
    @BindView(R.id.tv_tab3)
    TextView tvTab3;
    @BindView(R.id.ll_tab3)
    LinearLayout llTab3;
    @BindView(R.id.container)
    LinearLayout container;


    private List<Fragment> list_fragment = new ArrayList<>();
    private int mOnId = R.drawable.bot_3_on;
    private int mOffId = R.drawable.bot_3_off;
    private HomeFragment f0;
    private NewsLazyFragment f1;
//    private SortFragment f1;
    private OrderFragment f2;
    private MyFragment f3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();


        initView();
        //推送逻辑
        if (getIntent().getExtras() != null && !TextUtils.isEmpty(getIntent().getExtras().getString("url"))) {
            Intent mIntent = new Intent(this, WebActivity.class);
            mIntent.putExtra("url", getIntent().getExtras().getString("url"));
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mIntent);
        }
        if (!TextUtils.isEmpty(JPushInterface.getRegistrationID(this))) {
            BindPushPresenter pushPresenter = new BindPushPresenter(MainActivity.this);
            pushPresenter.bindPush(JPushInterface.getRegistrationID(this));
        }
    }

    @Override
    protected void addToActManager() {

    }

    @Override
    public void initStateBar() {
//        super.initStateBar();
    }

    @Override
    protected void onDestroy() {
        list_fragment.clear();
        f0 = null;
        f1 = null;
        f2 = null;
        f3 = null;
        EventBus.getDefault().unregister(this);
        System.gc();
        super.onDestroy();
    }




    private void initView() {
        f0 = new HomeFragment();
//        f1 = new SortFragment();
        f1 = new NewsLazyFragment();
        f2 = new OrderFragment();
        f3 = new MyFragment();
        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);
        list_fragment.add(f3);

        vp.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), list_fragment));

        vp.setCurrentItem(Integer.valueOf(spu.getValue("citem")));
        initBottom(Integer.valueOf(spu.getValue("citem")));
//        vp.setOffscreenPageLimit(3);

        vp.setScanScroll(false);
        vp.setPageTransformer(true, null);
    }


    @OnClick({R.id.ll_tab0, R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab0:
                if (vp.getCurrentItem() != 0) {
                    initBottom(0);
                    vp.setCurrentItem(0, false);
                    ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
                    spu.setValue("citem", "0");
                }
                break;
            case R.id.ll_tab1:
                if (vp.getCurrentItem() != 1) {
                    initBottom(1);
                    vp.setCurrentItem(1, false);
                    ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
                    spu.setValue("citem", "1");
                }
                break;
            case R.id.ll_tab2:
                if (vp.getCurrentItem() != 2) {
                    vp.setCurrentItem(2, false);
                    initBottom(2);
                    ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
                    spu.setValue("citem", "2");
                }
                break;
            case R.id.ll_tab3:
                if (vp.getCurrentItem() != 3) {
                    vp.setCurrentItem(3, false);
                    initBottom(3);
                    ImmersionBar.with(this)
                            .transparentStatusBar()
                            .statusBarDarkFont(false)
                            .fitsSystemWindows(false).init();
                    spu.setValue("citem", "3");
                }
                break;
        }
    }

    private void initBottom(int item) {
        if (item == 0) {
            ivTab0.setImageResource(R.drawable.bot_0_on);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(mOffId);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 1) {
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_on);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(mOffId);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 2) {
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_on);
            ivTab3.setImageResource(mOffId);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 3) {
            EventBus.getDefault().post(new RefreshRedNunEvent());
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(mOnId);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RegistPushSuccessEvent event) {
        BindPushPresenter pushPresenter = new BindPushPresenter(MainActivity.this);
        pushPresenter.bindPush(event.getId());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SwichFragEvent event) {
        vp.setCurrentItem(event.getPos(), false);
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        if (!TextUtils.isEmpty(JPushInterface.getRegistrationID(this))) {
            BindPushPresenter pushPresenter = new BindPushPresenter(MainActivity.this);
            pushPresenter.bindPush(JPushInterface.getRegistrationID(this));
        }
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(LogoutEvent event) {
//
////        Tools.toastInBottom(this, getString(R.string.tips_timeout));
////        Tools.logout(this);
////        FApp.getInstance().removeALLActivity();
////
////        Intent loginIntent = new Intent(this, LoginActivity.class);
////        startActivity(loginIntent);
////        finish();
//    }
}
