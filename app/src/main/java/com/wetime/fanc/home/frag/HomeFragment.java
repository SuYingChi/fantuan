package com.wetime.fanc.home.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.king.batterytest.fbaselib.customview.GridViewForScrollView;
import com.king.batterytest.fbaselib.customview.ListViewForScrollView;
import com.king.batterytest.fbaselib.main.BaseFragment;
import com.king.batterytest.fbaselib.main.ScanActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.adapter.CenterAdapter;
import com.wetime.fanc.home.adapter.HomeGridAdapter;
import com.wetime.fanc.home.adapter.HomeShopListAdapter;
import com.wetime.fanc.home.bean.HomePageBean;
import com.wetime.fanc.home.iviews.IGetHomePageView;
import com.wetime.fanc.home.presenter.GetHomePagePresenter;
import com.wetime.fanc.shopcenter.act.ShopCenterActivity;
import com.wetime.fanc.web.WebActivity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements OnRefreshListener, IGetHomePageView, OnLoadmoreListener {
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    Unbinder unbinder;
    @BindView(R.id.rcv_center)
    RecyclerView rcvCenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.gv)
    GridViewForScrollView gv;
    @BindView(R.id.lv_shop)
    ListViewForScrollView lvShop;
    @BindView(R.id.ll_loc)
    LinearLayout llloc;
    private String TAG = "zkhomefrag";


    private int REQUEST_CODE = 10000;
    private GetHomePagePresenter getHomePagePresenter;


    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;

    private int page = 1;
    private HomeShopListAdapter homeShopListAdapter;
    private List<HomePageBean.DataBean.MerchantsBean> mMerchanetlist =  new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        initLoaction();

        View v = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);

        homeShopListAdapter = new HomeShopListAdapter(getContext(), mMerchanetlist);
        lvShop.setAdapter(homeShopListAdapter);

        lvShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goWeb(mMerchanetlist.get(i).getDetail_url());
            }
        });
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        return v;
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
    public void onGetHomePage(final HomePageBean bean) {

        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(getContext(), bean.getData().getBigcates());
        gv.setAdapter(homeGridAdapter);
        homeGridAdapter.notifyDataSetChanged();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goWeb(bean.getData().getBigcates().get(i).getUrl());
            }
        });


        CenterAdapter adapter = new CenterAdapter(getContext(), R.layout.item_home_shopcenter, bean.getData().getMalls());
        rcvCenter.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                goWeb(bean.getData().getMalls().get(position).getUrl());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.item_seemore, null);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomore = new Intent(getContext(), ShopCenterActivity.class);
                startActivity(gomore);
            }
        });
        mHeaderAndFooterWrapper.addFootView(footer);

        rcvCenter.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();


        Glide.with(this).load(bean.getData().getPromotion_area().getBanner()).into(ivBanner);
        ivBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goWeb(bean.getData().getPromotion_area().getUrl());
            }
        });
        mMerchanetlist.clear();
        mMerchanetlist.addAll(bean.getData().getMerchants());

        homeShopListAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh(1000);
    }

    @Override
    public void onLoadMoreHomePage(HomePageBean bean) {

        mMerchanetlist.addAll(bean.getData().getMerchants());
        homeShopListAdapter.notifyDataSetChanged();
        if(bean.getData().getPaging().isIs_end()){
            refreshLayout.setEnableLoadmore(false);
        }
        refreshLayout.finishLoadmore();
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
                    }
                }

                getHomePagePresenter = new GetHomePagePresenter(HomeFragment.this);
                getHomePagePresenter.getHomePage();
                llloc.setVisibility(View.GONE);
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
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged: "+hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }
}
