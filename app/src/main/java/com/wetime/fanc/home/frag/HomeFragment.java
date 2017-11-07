package com.wetime.fanc.home.frag;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.king.batterytest.fbaselib.main.BaseFragment;
import com.king.batterytest.fbaselib.main.ScanActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.adapter.CenterAdapter;
import com.wetime.fanc.home.service.LocationService;
import com.wetime.fanc.shopcenter.ShopCenterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements OnRefreshListener {
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.test)
    ImageView test;
    Unbinder unbinder;
    @BindView(R.id.rcv_center)
    RecyclerView rcvCenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LocationService locationService;
    public Vibrator mVibrator;

    private int REQUEST_CODE = 10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();

        View v = inflater.inflate(R.layout.fragment_home, null);
        v.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(getContext(), ShopCenterActivity.class);
                startActivity(t);

            }
        });
        unbinder = ButterKnife.bind(this, v);
        ArrayList<String> s = new ArrayList<>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        CenterAdapter adapter = new CenterAdapter(s, getContext());
        rcvCenter.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvCenter.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(this);

        return v;
    }
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshLayout.finishRefresh(2000);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationService = new LocationService(getContext().getApplicationContext());
        mVibrator = (Vibrator) getContext().getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getContext().getApplicationContext());
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data == null || data.getStringExtra("key").equals("")) {
//                Intent te = new Intent(getContext(), InputCodeSelfActivity.class);
//                getContext().startActivity(te);
            } else {
                Tools.toastInBottom(getContext(), data.getStringExtra("key"));

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {

//                sb.append("\nlatitude : ");// 纬度
//                sb.append(location.getLatitude());
//                sb.append("\nlontitude : ");// 经度
//                sb.append(location.getLongitude());
                Log.e("zk 纬度", String.valueOf(location.getLatitude()));
                Log.e("zk 经度", String.valueOf(location.getLongitude()));


                spu.setValue("wd", String.valueOf(location.getLatitude()));
                spu.setValue("jd", String.valueOf(location.getLongitude()));
            } else {
                Tools.toastInBottom(getContext(), "定位失败");
                Log.e("zk error", "定位失败");
            }
        }

    };

}
