package com.wetime.fanc.home.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.batterytest.fbaselib.LogoutEvent;
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.view.CustomViewPager;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HomeFragmentPagerAdapter;
import com.wetime.fanc.home.frag.HomeFragment;
import com.wetime.fanc.home.frag.MyFragment;
import com.wetime.fanc.home.frag.OrderFragment;
import com.wetime.fanc.home.frag.SortFragment;
import com.wetime.fanc.home.iviews.IBindPushView;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.tencent.android.tpush.XGIOperateCallback;
//import com.tencent.android.tpush.XGPushManager;
//import com.tencent.android.tpush.service.XGPushServiceV3;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

//        initXG();
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);


    }

    private void initView() {
        HomeFragment f0 = new HomeFragment();
        SortFragment f1 = new SortFragment();
        OrderFragment f2 = new OrderFragment();
        MyFragment f3 = new MyFragment();
        list_fragment.add(f0);
        list_fragment.add(f1);
        list_fragment.add(f2);
        list_fragment.add(f3);

        vp.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), list_fragment));

        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(3);

        vp.setScanScroll(false);
        vp.setPageTransformer(true, null);
    }

//    private void initXG() {
////        XGPushConfig.enableDebug(this, true);
//        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
//        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
//        // 具体可参考详细的开发指南
//        // 传递的参数为ApplicationContext
//        Context context = getApplicationContext();
//        XGPushManager.registerPush(context, new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object o, int i) {
////                Handler mHandler = new Handler();
////                mHandler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        BindPushPresenter pushPresenter = new BindPushPresenter(MainActivity.this);
////                        pushPresenter.bindPush(XGPushConfig.getToken(MainActivity.this));
////                    }
////                });
//
//            }
//
//            @Override
//            public void onFail(Object o, int i, String s) {
//                Log.d("TPush", "xg fail s = " + s);
//                Log.d("TPush", "xg fail i = " + i);
//            }
//        });
//
//        Intent service = new Intent(context, XGPushServiceV3.class);
//        context.startService(service);
//    }

    @OnClick({R.id.ll_tab0, R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab0:
                if (vp.getCurrentItem() != 0) {
                    initBottom(0);
                    vp.setCurrentItem(0, false);
                }
                break;
            case R.id.ll_tab1:
                if (vp.getCurrentItem() != 1) {
                    initBottom(1);
                    vp.setCurrentItem(1, false);

                }
                break;
            case R.id.ll_tab2:
                if (vp.getCurrentItem() != 2) {
                    vp.setCurrentItem(2, false);
                    initBottom(2);
                }
                break;
            case R.id.ll_tab3:
                if (vp.getCurrentItem() != 3) {
                    vp.setCurrentItem(3, false);
                    initBottom(3);
                }
                break;
        }
    }

    private void initBottom(int item) {
        if (item == 0) {
            ivTab0.setImageResource(R.drawable.bot_0_on);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(R.drawable.bot_3_off);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 1) {
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_on);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(R.drawable.bot_3_off);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 2) {
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_on);
            ivTab3.setImageResource(R.drawable.bot_3_off);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
        }
        if (item == 3) {
            ivTab0.setImageResource(R.drawable.bot_0_off);
            ivTab1.setImageResource(R.drawable.bot_1_off);
            ivTab2.setImageResource(R.drawable.bot_2_off);
            ivTab3.setImageResource(R.drawable.bot_3_on);

            tvTab0.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab1.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab2.setTextColor(ContextCompat.getColor(this, R.color.bot_gray));
            tvTab3.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {

//        Tools.toastInBottom(this, getString(R.string.tips_timeout));
//        Tools.logout(this);
//        FApp.getInstance().removeALLActivity();
//
//        Intent loginIntent = new Intent(this, LoginActivity.class);
//        startActivity(loginIntent);
//        finish();
    }
}
