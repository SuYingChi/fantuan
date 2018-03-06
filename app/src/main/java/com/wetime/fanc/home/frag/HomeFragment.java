package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.HeadHomeAdapter;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.home.iviews.IGetHomePageView;
import com.wetime.fanc.home.presenter.GetHomePagePresenter;
import com.wetime.fanc.main.frag.BaseFragment;
import com.wetime.fanc.qr.ScanActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements OnRefreshListener, IGetHomePageView, OnLoadmoreListener {
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

    private Timer time;
    private TimerTask tk;
    private int i = 0;
    private Handler mHandler;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"热门", "最新"};
    private String[] sort = {"1", "2"};//    ort : 1 = 按热度排序， 2 = 按发布时间排序
    private int sortPos = 0;
    private HeadHomeAdapter headAdapter;
    private List<HomePageBean.DataBean.BigcatesBean> headlist = new ArrayList<>();
    private List<HomeItemBean> list = new ArrayList<>();
    private HomeItemAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        initLoaction();
        mHandler = new Handler();
        View v = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);
        getHomePagePresenter = new GetHomePagePresenter(HomeFragment.this);

        rclHome.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new HomeItemAdapter(list,getActivity(),true);
        rclHome.setAdapter(adapter);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        initlocview();
        initView();
        return v;
    }

    private void initView() {
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
        time = new Timer();
        tk = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                mHandler.post(() -> {
                    if (i % 3 == 0) {
                        tvLoc.setText(".");
                    } else if (i % 3 == 1) {
                        tvLoc.setText("..");
                    } else if (i % 3 == 2) {
                        tvLoc.setText("...");
                    }
                    i++;
                });

            }
        };
        time.schedule(tk, 0, 500);

    }

    @OnClick({R.id.iv_scan, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent goscan = new Intent(getContext(), ScanActivity.class);
                startActivityForResult(goscan, REQUEST_CODE);
                break;
            case R.id.ll_search:
                Intent gosearch = new Intent(getContext(), HomeSearchActivity.class);
                startActivity(gosearch);
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
        }
        if(page==1){
            list.clear();
        }
        list.addAll(bean.getData().getList());
        adapter.notifyDataSetChanged();

        refreshLayout.finishLoadmore();
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


                getHomePagePresenter.getHomePage();
                llloc.setVisibility(View.GONE);
                time.cancel();
                tk.cancel();
                mLocationClient.stopLocation();
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getHomePagePresenter.getHomePage();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mLocationClient.onDestroy();
    }

}
