package com.wetime.fanc.circle.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LocListAdapter;
import com.wetime.fanc.circle.bean.LocItemBean;
import com.wetime.fanc.circle.bean.SelectLocListBean;
import com.wetime.fanc.circle.iviews.IGetLocListView;
import com.wetime.fanc.circle.presenter.GetLocListPresenter;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectLocActivity extends BaseActivity implements IGetLocListView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rcl_loc)
    RecyclerView rclLoc;
    private GetLocListPresenter getLocListPresenter;
    private int page = 1;
    private LocListAdapter adapter;
    private ArrayList<LocItemBean> list = new ArrayList<>();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;
    private final int SEARCH_LOC = 1000;

    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loc);
        ButterKnife.bind(this);
        tvTitle.setText("附近位置");
        rclLoc.setLayoutManager(new LinearLayoutManager(mContext));
        LocItemBean bean = (LocItemBean) getIntent().getSerializableExtra("loc");

        //不显示位置bean
        LocItemBean nolocbean = new LocItemBean();
        if (TextUtils.isEmpty(bean.getTitle())) {
            nolocbean.setSelected(true);
        }
        list.add(nolocbean);


        if (!TextUtils.isEmpty(bean.getTitle())) {
            list.add(bean);
        }
        adapter = new LocListAdapter(mContext, list);
        adapter.setOnItemClickLitener((view, position) -> {
            list.get(position).setSelected(false);
            Intent data = new Intent();
            data.putExtra("loc", list.get(position));
            setResult(RESULT_OK, data);
            finish();
        });

        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(mContext, adapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getLocListPresenter.getLoclist();
            }
        });
        rclLoc.setAdapter(mAutoLoadMoreAdapter);

        mAutoLoadMoreAdapter.notifyDataSetChanged();

        getLocListPresenter = new GetLocListPresenter(this);
        if (!TextUtils.isEmpty(getJd()) && !TextUtils.isEmpty(getWd())){
            getLocListPresenter.getLoclist();
        }else{
            initLoaction();
        }



    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick({R.id.iv_back, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ll_search:
                Intent goSearch = new Intent(mContext, SearchLocActivity.class);
                goSearch.putExtra("loctitle", getLocTitle());
                startActivityForResult(goSearch, SEARCH_LOC);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_LOC) {
            if (resultCode == RESULT_OK) {
                Intent d = new Intent();
                d.putExtra("loc", data.getSerializableExtra("loc"));
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }

    @Override
    public void onGetLocList(SelectLocListBean bean) {
        list.addAll(bean.getData().getList());
        mAutoLoadMoreAdapter.notifyDataSetChanged();
        mAutoLoadMoreAdapter.finishLoading();
        if (bean.getData().getList().size() == 0)
            mAutoLoadMoreAdapter.disable();
    }

    @Override
    public String getKeyWord() {
        return "";
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getLocTitle() {
        return ((LocItemBean) getIntent().getSerializableExtra("loc")).getTitle();
    }

    private void initLoaction() {


        mLocationClient = new AMapLocationClient(getApplicationContext());

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


        mLocationListener = amapLocation -> {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。

                    Log.e("zk 纬度", String.valueOf(amapLocation.getLatitude()));
                    Log.e("zk 经度", String.valueOf(amapLocation.getLongitude()));


                    spu.setValue("wd", String.valueOf(amapLocation.getLatitude()));
                    spu.setValue("jd", String.valueOf(amapLocation.getLongitude()));
                    getLocListPresenter.getLoclist();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("zk", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    spu.setValue("wd", "");
                    spu.setValue("jd", "");
                    Tools.toastInBottom(mContext, "获取位置信息失败");
                }
            }
            mLocationClient.stopLocation();
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();

    }
}
