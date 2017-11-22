package com.wetime.fanc.home.act;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.HomeSearchResult;
import com.wetime.fanc.home.iviews.IGetHomeSearchResultView;
import com.wetime.fanc.home.presenter.GetHomeSearchResultPresenter;
import com.wetime.fanc.shopcenter.adapter.CategoryItemAdapter;
import com.wetime.fanc.shopcenter.adapter.LoaItemAdapter;
import com.wetime.fanc.shopcenter.adapter.SCategoruItemAdapter;
import com.wetime.fanc.shopcenter.adapter.SLoaItemAdapter;
import com.wetime.fanc.shopcenter.adapter.SearchShopListAdapter;
import com.wetime.fanc.shopcenter.adapter.SortMethordItemAdapter;
import com.wetime.fanc.shopcenter.bean.MerchantsBean;
import com.wetime.fanc.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchResultActivity extends BaseActivity implements IGetHomeSearchResultView, OnRefreshListener, OnLoadmoreListener {


    @BindView(R.id.iv_back)
    ImageView ivBack;

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
    @BindView(R.id.lv_11)
    ListView lv11;
    @BindView(R.id.lv_12)
    ListView lv12;
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
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private GetHomeSearchResultPresenter getHomeSearchResultPresenter;
    private SearchShopListAdapter adapter;
    private List<MerchantsBean> list = new ArrayList<>();
    private LoaItemAdapter adapter11;
    private CategoryItemAdapter adapter21;
    private SortMethordItemAdapter adapter3;

    //    private int opper = 0;// 0  刷新 1 加载更多
    private int page = 1;
    private String sortMethod = "0";//排列方式： 0=智能排序，1=距离优先，2=人均
    //    private String floorId = "";
//    private String cenerId = "";
    private String cid = ""; //分类大id
    private String sid = "";// 子分类Id

    private String mid = ""; //Loa分类大id
    private String smid = "";//Loa 子分类Id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homesearch_result);
        ButterKnife.bind(this);

        etSearch.setText(getIntent().getStringExtra("key"));
        etSearch.setInputType(InputType.TYPE_NULL);
        adapter = new SearchShopListAdapter(list, mContext);
        rcvLsit.setLayoutManager(new LinearLayoutManager(this));
        rcvLsit.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);


        getHomeSearchResultPresenter = new GetHomeSearchResultPresenter(this);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tools.toastInBottom(this, "new intetne");
        etSearch.setText(intent.getStringExtra("key"));
        etSearch.setInputType(InputType.TYPE_NULL);
        adapter = new SearchShopListAdapter(list, mContext);
        rcvLsit.setLayoutManager(new LinearLayoutManager(this));
        rcvLsit.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);


        getHomeSearchResultPresenter = new GetHomeSearchResultPresenter(this);
        refreshLayout.autoRefresh();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.ll_1, R.id.ll_3, R.id.ll_2, R.id.et_search,R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.et_search:
                Intent gosearch = new Intent(this, HomeSearchActivity.class);
                gosearch.putExtra("key", etSearch.getText().toString());
                startActivity(gosearch);
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
    public String getWord() {
        return etSearch.getText().toString();
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
        return smid;
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
    public void onGetHomeSearchResultBean(final HomeSearchResult bean) {
        if (page == 1)
            list.clear();

        list.addAll(bean.getData().getMerchants());
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickLitener(new SearchShopListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                goWeb(list.get(position).getDetail_url());
            }
        });

        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();

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

        adapter11 = new LoaItemAdapter(mContext, bean.getData().getMalls());
        adapter11.setSelectedId(mid);
        lv11.setAdapter(adapter11);
        adapter11.notifyDataSetChanged();
        lv11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                adapter11.setSelectedId(bean.getData().getMalls().get(i).getId());
                mid = bean.getData().getMalls().get(i).getId();
                if (mid.equals("")) {
                    smid = "";
                    v1.setVisibility(View.GONE);
                    tv1.setText(bean.getData().getMalls().get(i).getName());

                    tv1.setTag(null);
                    tv1.setTextColor(Color.parseColor("#666666"));
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tv1.setCompoundDrawables(null, null, drawable, null);

                    SLoaItemAdapter adapter12 = new SLoaItemAdapter(mContext, bean.getData().getMalls().get(i).getSubcates());
                    adapter12.setCid(mid);
                    adapter12.setSelectedId(smid);
                    lv12.setAdapter(adapter12);
                    adapter12.notifyDataSetChanged();

                    page = 1;
                    refreshLayout.autoRefresh();
                } else {
                    SLoaItemAdapter adapter12 = new SLoaItemAdapter(mContext, bean.getData().getMalls().get(i).getSubcates());
                    adapter12.setCid(mid);
                    adapter12.setSelectedId(smid);
                    lv12.setAdapter(adapter12);
                    adapter12.notifyDataSetChanged();
                    lv12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int ii, long l) {
                            smid = bean.getData().getMalls().get(i).getSubcates().get(ii).getId();
                            v1.setVisibility(View.GONE);
                            if (ii == 0) {
                                tv1.setText(bean.getData().getMalls().get(i).getName());
                            } else {
                                tv1.setText(bean.getData().getMalls().get(i).getSubcates().get(ii).getName());
                            }


                            tv1.setTag(null);
                            tv1.setTextColor(Color.parseColor("#666666"));
                            Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                            tv1.setCompoundDrawables(null, null, drawable, null);

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

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        Log.e("zk", "referessssss");
        page = 1;
        getHomeSearchResultPresenter.getShopCenterList();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getHomeSearchResultPresenter.getShopCenterList();
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }
}
