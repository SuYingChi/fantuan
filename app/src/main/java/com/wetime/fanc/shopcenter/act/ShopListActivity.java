package com.wetime.fanc.shopcenter.act;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wetime.fanc.main.act.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.shop.act.ShopDetailActivity;
import com.wetime.fanc.shopcenter.adapter.CategoryItemAdapter;
import com.wetime.fanc.shopcenter.adapter.FloorItemAdapter;
import com.wetime.fanc.shopcenter.adapter.SCategoruItemAdapter;
import com.wetime.fanc.shopcenter.adapter.SearchShopListAdapter;
import com.wetime.fanc.shopcenter.adapter.SortMethordItemAdapter;
import com.wetime.fanc.shopcenter.bean.CenterListPageBean;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterListView;
import com.wetime.fanc.shopcenter.presenter.GetShopCenterListPresenter;
import com.wetime.fanc.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopListActivity extends BaseActivity implements IGetShopCenterListView, OnRefreshListener, OnLoadMoreListener, SearchShopListAdapter.OnItemClickLitener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rcv_lsit)
    RecyclerView rcvLsit;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.lv_1)
    ListView lv1;
    @BindView(R.id.v_1)
    View v1;

    @BindView(R.id.lv_3)
    ListView lv3;
    @BindView(R.id.v_3)
    View v3;
    @BindView(R.id.lv_21)
    ListView lv21;
    @BindView(R.id.lv_22)
    ListView lv22;
    @BindView(R.id.v_2)
    View v2;

    private GetShopCenterListPresenter getShopCenterListPresenter;
    private SearchShopListAdapter adapter;
    private List<MerchantsBean> list = new ArrayList<>();
    private FloorItemAdapter adapter1;
    private CategoryItemAdapter adapter21;
    private SortMethordItemAdapter adapter3;

    //    private int opper = 0;// 0  刷新 1 加载更多
    private int page = 1;
    private String sortMethod = "0";//排列方式： 0=智能排序，1=距离优先，2=人均
    private String floorId = "";
    private String cenerId = "24";
    private String cid = "";
    private String sid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerlist);
        ButterKnife.bind(this);
//        gom.putExtra("title", title);
//        gom.putExtra("floorId", floorId);
//        gom.putExtra("cenerId", cenerId);
        tvTitle.setText(getIntent().getStringExtra("title"));
        floorId = getIntent().getStringExtra("floorId");
        cenerId = getIntent().getStringExtra("cenerId");


        if (!TextUtils.isEmpty(getIntent().getStringExtra("floorName")))
            tv1.setText(getIntent().getStringExtra("floorName"));
        adapter = new SearchShopListAdapter(list, mContext);
        rcvLsit.setLayoutManager(new LinearLayoutManager(this));
        rcvLsit.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);


        getShopCenterListPresenter = new GetShopCenterListPresenter(this);
        refreshLayout.autoRefresh();
//        getShopCenterListPresenter.getShopCenterList();
        adapter.setOnItemClickLitener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_search, R.id.ll_1, R.id.ll_3, R.id.ll_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_search:
                Intent goSearch = new Intent(this, ShopSearchActivity.class);
                goSearch.putExtra("id",cenerId);
                goSearch.putExtra("name",getIntent().getStringExtra("title"));
                startActivity(goSearch);
                break;
            case R.id.ll_1:
                if (tv1.getTag() == null) {
                    tv1.setTag("");
                    tv1.setTextColor(Color.parseColor("#ff3f53"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_up);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv1.setCompoundDrawables(null, null, drawable, null);
                    v1.setVisibility(View.VISIBLE);


                } else {
                    tv1.setTag(null);
                    tv1.setTextColor(Color.parseColor("#666666"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv1.setCompoundDrawables(null, null, drawable, null);
                    v1.setVisibility(View.GONE);
                }
                Drawable d = getResources().getDrawable(R.drawable.ic_head_down);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getMinimumHeight());
                tv2.setCompoundDrawables(null, null, d, null);
                tv3.setCompoundDrawables(null, null, d, null);
                tv3.setTextColor(Color.parseColor("#666666"));
                tv2.setTextColor(Color.parseColor("#666666"));
                tv3.setTag(null);
                tv2.setTag(null);

                v3.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                break;
            case R.id.ll_2:
                if (tv2.getTag() == null) {
                    tv2.setTag("");
                    tv2.setTextColor(Color.parseColor("#ff3f53"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_up);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv2.setCompoundDrawables(null, null, drawable, null);
                    v2.setVisibility(View.VISIBLE);
                } else {
                    tv2.setTag(null);
                    tv2.setTextColor(Color.parseColor("#666666"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv2.setCompoundDrawables(null, null, drawable, null);
                    v2.setVisibility(View.GONE);
                }
                Drawable d2 = getResources().getDrawable(R.drawable.ic_head_down);
                d2.setBounds(0, 0, d2.getIntrinsicWidth(), d2.getMinimumHeight());
                tv1.setCompoundDrawables(null, null, d2, null);
                tv3.setCompoundDrawables(null, null, d2, null);
                tv1.setTextColor(Color.parseColor("#666666"));
                tv3.setTextColor(Color.parseColor("#666666"));
                tv3.setTag(null);
                tv1.setTag(null);
                v1.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                break;
            case R.id.ll_3:
                if (tv3.getTag() == null) {
                    tv3.setTag("");
                    tv3.setTextColor(Color.parseColor("#ff3f53"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_up);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv3.setCompoundDrawables(null, null, drawable, null);

                    v3.setVisibility(View.VISIBLE);
                } else {
                    tv3.setTag(null);
                    tv3.setTextColor(Color.parseColor("#666666"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv3.setCompoundDrawables(null, null, drawable, null);

                    v3.setVisibility(View.GONE);
                }
                Drawable d22 = getResources().getDrawable(R.drawable.ic_head_down);
                d22.setBounds(0, 0, d22.getIntrinsicWidth(), d22.getMinimumHeight());
                tv1.setCompoundDrawables(null, null, d22, null);
                tv2.setCompoundDrawables(null, null, d22, null);
                tv1.setTextColor(Color.parseColor("#666666"));
                tv2.setTextColor(Color.parseColor("#666666"));
                tv2.setTag(null);
                tv1.setTag(null);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public String getJd() {
        return spu.getValue("jd");
    }

    @Override
    public String getWd() {
        return spu.getValue("wd");
    }

    @Override
    public String getMailId() {
        return cenerId;
    }

    @Override
    public String getFloaId() {
        return floorId;
    }

    @Override
    public String getPage() {
        return "" + page;
    }

    @Override
    public String getSortMethod() {
        return sortMethod;
    }

    @Override
    public String getCId() {
        return sid;
    }

    @Override
    public void onGetShopCenterListBean(final CenterListPageBean bean) {
        if (page == 1)
            list.clear();

        list.addAll(bean.getData().getMerchants());
        adapter.notifyDataSetChanged();


        adapter1 = new FloorItemAdapter(mContext, bean.getData().getFloor());
        adapter1.setSelectedId(floorId);
        lv1.setAdapter(adapter1);


        adapter1.notifyDataSetChanged();
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter1.setSelectedId(bean.getData().getFloor().get(i).getId());
                v1.setVisibility(View.GONE);
                tv1.setText(bean.getData().getFloor().get(i).getName());

                tv1.setTag(null);
                tv1.setTextColor(Color.parseColor("#666666"));
                Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tv1.setCompoundDrawables(null, null, drawable, null);
                floorId = bean.getData().getFloor().get(i).getId();
                page = 1;
                refreshLayout.autoRefresh();

            }
        });


        adapter21 = new CategoryItemAdapter(mContext, bean.getData().getCategory());
        adapter21.setSelectedId(cid);
        lv21.setAdapter(adapter21);
        adapter21.notifyDataSetChanged();
        lv21.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                adapter21.setSelectedId(bean.getData().getCategory().get(i).getId());
                cid = bean.getData().getCategory().get(i).getId();
                if (cid.equals("")) {
                    sid = "";
                    v2.setVisibility(View.GONE);
                    tv2.setText(bean.getData().getCategory().get(i).getName());

                    tv2.setTag(null);
                    tv2.setTextColor(Color.parseColor("#666666"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv2.setCompoundDrawables(null, null, drawable, null);

                    SCategoruItemAdapter adapter22 = new SCategoruItemAdapter(mContext, bean.getData().getCategory().get(i).getSubcates());
                    adapter22.setCid(cid);
                    adapter22.setSelectedId(sid);
                    lv22.setAdapter(adapter22);
                    adapter22.notifyDataSetChanged();

                    page = 1;
                    refreshLayout.autoRefresh();
                } else {
                    SCategoruItemAdapter adapter22 = new SCategoruItemAdapter(mContext, bean.getData().getCategory().get(i).getSubcates());
                    adapter22.setCid(cid);
                    adapter22.setSelectedId(sid);
                    lv22.setAdapter(adapter22);
                    adapter22.notifyDataSetChanged();
                    lv22.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int ii, long l) {
                            sid = bean.getData().getCategory().get(i).getSubcates().get(ii).getId();
                            Log.e("zk sid=", sid);

                            v2.setVisibility(View.GONE);
                            if (ii == 0) {
                                tv2.setText(bean.getData().getCategory().get(i).getName());
                            } else {
                                tv2.setText(bean.getData().getCategory().get(i).getSubcates().get(ii).getName());
                            }
                            tv2.setTag(null);
                            tv2.setTextColor(Color.parseColor("#666666"));
                            Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                            tv2.setCompoundDrawables(null, null, drawable, null);

                            page = 1;
                            refreshLayout.autoRefresh();
                        }
                    });
                }


            }
        });


        adapter3 = new SortMethordItemAdapter(mContext, bean.getData().getSort());
        adapter3.setSelectedId(sortMethod);
        lv3.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();
        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter3.setSelectedId(bean.getData().getSort().get(i).getSort());
                v3.setVisibility(View.GONE);
                tv3.setText(bean.getData().getSort().get(i).getName());

                tv3.setTag(null);
                tv3.setTextColor(Color.parseColor("#666666"));
                Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tv3.setCompoundDrawables(null, null, drawable, null);
                sortMethod = bean.getData().getSort().get(i).getSort();
                page = 1;
                refreshLayout.autoRefresh();
            }
        });


        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        Log.e("zk", "referessssss");
        page = 1;
        getShopCenterListPresenter.getShopCenterList();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getShopCenterListPresenter.getShopCenterList();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent goShop = new Intent(mContext, ShopDetailActivity.class);
        goShop.putExtra("mid",list.get(position).getMid());
        startActivity(goShop);
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }

    public void hideDropDown(View view) {
        tv1.setTag(null);
        tv1.setTextColor(Color.parseColor("#666666"));
        Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        tv1.setCompoundDrawables(null, null, drawable, null);
        v1.setVisibility(View.GONE);


        tv2.setTag(null);
        tv2.setTextColor(Color.parseColor("#666666"));
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        tv2.setCompoundDrawables(null, null, drawable, null);
        v2.setVisibility(View.GONE);

        tv3.setTag(null);
        tv3.setTextColor(Color.parseColor("#666666"));
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
        tv3.setCompoundDrawables(null, null, drawable, null);

        v3.setVisibility(View.GONE);
    }

}
