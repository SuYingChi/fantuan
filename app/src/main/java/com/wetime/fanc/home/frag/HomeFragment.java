package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.LongTextEditActivity;
import com.wetime.fanc.circle.adapter.HeadHomeAdapter;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.act.SortActivity;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.home.event.RefreshRedNumEvent;
import com.wetime.fanc.home.iviews.IGetHomePageView;
import com.wetime.fanc.home.presenter.GetHomePagePresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.frag.BaseFragment;
import com.wetime.fanc.qr.ScanActivity;
import com.wetime.fanc.shopcenter.act.ShopCenterActivity;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements OnRefreshListener, IGetHomePageView {
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    @BindView(R.id.ll_loc)
    LinearLayout llloc;
    Unbinder unbinder;
    @BindView(R.id.tv_loc)
    TextView tvLoc;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.tablayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    private String TAG = "zkhomefrag";


    private int REQUEST_CODE = 10000;
    private GetHomePagePresenter getHomePagePresenter;


    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;

    private int page = 1;

    //    private Timer time;
//    private TimerTask tk;
//    private int i = 0;
    private Handler mHandler;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"最新","热门" };
    private String[] sort = {"1", "2"};//    ort : 1 = 按热度排序， 2 = 按发布时间排序
    private int sortPos = 0;
    private HeadHomeAdapter headAdapter;
    private List<HomePageBean.DataBean.BigcatesBean> headlist = new ArrayList<>();
    private List<HomeItemBean> list = new ArrayList<>();
    private HomeItemAdapter adapter;
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;
    private QBadgeView qBadgeMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        EventBus.getDefault().register(this);
        initLoaction();
        mHandler = new Handler();
        View v = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);
        getHomePagePresenter = new GetHomePagePresenter(HomeFragment.this);

        rclHome.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new HomeItemAdapter(list, getActivity(), true);
        rclHome.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        initlocview();
        initView();

        getHomePagePresenter.getHomePage();
        return v;
    }

    private void initView() {
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
        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(getContext(), adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getHomePagePresenter.getHomePage();
            }
        });

        rclHome.setAdapter(mAutoLoadMoreAdapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getHomePagePresenter.getHomePage();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data == null || data.getStringExtra("key").equals("")) {
//                Intent te = new Intent(getContext(), InputCodeSelfActivity.class);
//                getContext().startActivity(te);
            } else {
                if (Patterns.WEB_URL.matcher(data.getStringExtra("key")).matches()) {
                    goWeb(data.getStringExtra("key") + "&type=andriod");
                } else {
                    Tools.toastInBottom(getContext(), data.getStringExtra("key"));
                }
            }

        }
    }

    private void initlocview() {
//        time = new Timer();
//        tk = new TimerTask() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                mHandler.post(() -> {
//                    if (i % 3 == 0) {
//                        tvLoc.setText(".");
//                    } else if (i % 3 == 1) {
//                        tvLoc.setText("..");
//                    } else if (i % 3 == 2) {
//                        tvLoc.setText("...");
//                    }
//                    i++;
//                });
//
//            }
//        };
//        time.schedule(tk, 0, 500);

    }

    @OnClick({R.id.iv_scan, R.id.ll_search, R.id.iv_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                AndPermission.with(this)
                        .permission(Permission.Group.CAMERA)
                        .onGranted(permissions -> {
                            Intent goscan = new Intent(getContext(), ScanActivity.class);
                            startActivityForResult(goscan, REQUEST_CODE);

                        }).onDenied(permissions -> {
                    Tools.toastInBottom(getContext(), "请赋予权限");
                })
                        .start();

                break;
            case R.id.ll_search:
                Intent gosearch = new Intent(getContext(), HomeSearchActivity.class);
                startActivity(gosearch);
                break;
            case R.id.iv_msg:
                if (!TextUtils.isEmpty(spu.getToken()))
                    Tools.goWeb(getContext(), Const.MSG_URL);
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
        }
    }


    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public void onGetHomePage(HomePageBean bean) {
        if (headAdapter == null) {
            GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
            rclCircle.setLayoutManager(manager);


            headlist.addAll(bean.getData().getBigcates());
            headAdapter = new HeadHomeAdapter(headlist, getContext());
            rclCircle.setAdapter(headAdapter);
            headAdapter.notifyDataSetChanged();
            rlEmpty.setVisibility(View.GONE);
            headAdapter.setOnItemClickLitener((view, position) -> {
                HomePageBean.DataBean.BigcatesBean bigcatesBean = bean.getData().getBigcates().get(position);
                if (bigcatesBean.getId().equals("-1")) {
                    Intent gomore = new Intent(getContext(), ShopCenterActivity.class);
                    startActivity(gomore);
                    return;
                }
                if (bigcatesBean.getId().equals("-2")) {
                    Intent goSort = new Intent(getContext(), SortActivity.class);
                    startActivity(goSort);
                    return;
                }
                Tools.goWeb(getContext(), bigcatesBean.getUrl());
            });
        }
        if (page == 1) {
            list.clear();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }
        list.addAll(bean.getData().getList());
        mAutoLoadMoreAdapter.notifyDataSetChanged();


        llloc.setVisibility(View.GONE);
        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public String getSort() {
        return sort[sortPos];
    }


    private void goWeb(String url) {
        Intent goweb = new Intent(getContext(), WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }

    private void initLoaction() {

        mLocationClient = new AMapLocationClient(getContext().getApplicationContext());

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationOption);


        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。

                        Log.e("zk 纬度", String.valueOf(amapLocation.getLatitude()));
                        Log.e("zk 经度", String.valueOf(amapLocation.getLongitude()));


                        spu.setValue("wd", String.valueOf(amapLocation.getLatitude()));
                        spu.setValue("jd", String.valueOf(amapLocation.getLongitude()));
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("zk", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        spu.setValue("wd", "");
                        spu.setValue("jd", "");
                        Tools.toastInBottom(getContext(), "获取位置信息失败");
                    }
                }


                llloc.setVisibility(View.GONE);
//                time.cancel();
//                tk.cancel();
                mLocationClient.stopLocation();
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshRedNumEvent event) {
        qBadgeMsg.setBadgeNumber(event.getNum());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        qBadgeMsg.setBadgeNumber(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mLocationClient.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
