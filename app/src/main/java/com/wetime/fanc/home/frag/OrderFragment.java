package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wetime.fanc.main.frag.BaseFragment;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Tools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.OrderAdapter;
import com.wetime.fanc.home.adapter.OrderTypeAdapter;
import com.wetime.fanc.home.bean.OrderPageBean;
import com.wetime.fanc.home.iviews.IDeleteOrderView;
import com.wetime.fanc.home.presenter.DeleteOrderPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.order.act.CommentOrderActivity;
import com.wetime.fanc.order.event.RefreshOrderEvent;
import com.wetime.fanc.shopcenter.iviews.IGetOrderListView;
import com.wetime.fanc.shopcenter.presenter.GetOrderPagePresenter;
import com.wetime.fanc.web.WebActivity;

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


public class OrderFragment extends BaseFragment implements IGetOrderListView, OnLoadmoreListener, OnRefreshListener, IDeleteOrderView {
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    Unbinder unbinder;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_type)
    ListView lvType;
    @BindView(R.id.rl_type)
    RelativeLayout rlType;
    @BindView(R.id.rcl_order)
    RecyclerView rclOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_no_order)
    RelativeLayout rlNoOrder;

    private GetOrderPagePresenter getOrderPagePresenter;
    private int page = 1;
    private String type = "0";
    private String filter = "0";
    private QBadgeView qBadgeViewUnPay;
    private QBadgeView qBadgeViewUnUse;
    private QBadgeView qBadgeViewUnComment;
    private OrderTypeAdapter orderTypeAdapter;
    private List<OrderPageBean.DataBean.ListBean> ordelist = new ArrayList<>();
    private OrderAdapter adapter;
    private DeleteOrderPresenter deleteOrderPresenter;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View v = inflater.inflate(R.layout.fragment_order, null);
        unbinder = ButterKnife.bind(this, v);
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        adapter = new OrderAdapter(ordelist, getContext());
        rclOrder.setAdapter(adapter);
        rclOrder.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        deleteOrderPresenter = new DeleteOrderPresenter(this);
        initView();
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_title, R.id.rl_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                Intent go = new Intent(getContext(), LoginActivity.class);
                startActivity(go);
                break;
            case R.id.tv_title:
            case R.id.rl_type:
                changeTileState();
                break;

        }
    }

    private void changeTileState() {
        if (tvTitle.getTag() == null) {
            tvTitle.setTag("");
            rlType.setVisibility(View.VISIBLE);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_head_up_gray);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
            tvTitle.setCompoundDrawables(null, null, drawable, null);
        } else {
            tvTitle.setTag(null);
            rlType.setVisibility(View.GONE);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_head_down);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
            tvTitle.setCompoundDrawables(null, null, drawable, null);
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public String getPage() {
        return page + "";
    }

    @Override
    public void onGetOrderPage(final OrderPageBean bean, String ty, String fi) {
//        tvTitle.setText(bean.getData().getType_config().get(0).getName());
        if (!ty.equals(type) || !fi.equals(filter))
            return;
        if (bean.getData().getList().size() == 0 && page == 1) {
            rlNoOrder.setVisibility(View.VISIBLE);
        } else {
            rlNoOrder.setVisibility(View.GONE);
        }

        if (bean.getData().getUnpaid_num() > 0) {
            qBadgeViewUnPay.setBadgeNumber(bean.getData().getUnpaid_num());
        } else {
            qBadgeViewUnPay.hide(true);
        }
        if (bean.getData().getUnused_num() > 0) {
            qBadgeViewUnUse.setBadgeNumber(bean.getData().getUnused_num());
        } else {
            qBadgeViewUnUse.hide(true);
        }
        if (bean.getData().getUnreview_num() > 0) {
            qBadgeViewUnComment.setBadgeNumber(bean.getData().getUnreview_num());
        } else {
            qBadgeViewUnComment.hide(true);
        }

        if (orderTypeAdapter == null) {
            orderTypeAdapter = new OrderTypeAdapter(getContext(), bean.getData().getType_config());
            lvType.setAdapter(orderTypeAdapter);
            View footer = LayoutInflater.from(getContext()).inflate(R.layout.item_footer_shadow, null, false);
            lvType.addFooterView(footer);
            orderTypeAdapter.notifyDataSetChanged();
        }
        lvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type = bean.getData().getType_config().get(i).getVal() + "";
                tvTitle.setText(bean.getData().getType_config().get(i).getName());
                changeTileState();
                orderTypeAdapter.setSelectedId(i);

                page = 1;
                ordelist.clear();
                adapter.notifyDataSetChanged();

                refreshLayout.autoRefresh();
            }
        });
        if (page == 1) {
            ordelist.clear();
        }
        ordelist.addAll(bean.getData().getList());
        adapter.setType(type);
        adapter.notifyDataSetChanged();
        rclOrder.scrollTo(0, 0);

        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        refreshLayout.setEnableLoadmore(!bean.getData().getPaging().isIs_end());
    }

    @Override
    public void onNetError() {
        super.onNetError();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }

    private void initView() {
        if (spu.getToken().equals("")) {
            rlEmpty.setVisibility(View.VISIBLE);
            return;
        } else {
            rlEmpty.setVisibility(View.GONE);
        }

        String[] str = {"全部", "待付款", "待使用", "待评价"};

        for (int i = 0; i < str.length; i++) {
            if (tab.getTabAt(i).getCustomView() == null) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tab_with_red, null);
                TextView tv = view.findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#333333"));
                tv.setText(str[i]);
                tab.getTabAt(i).setCustomView(tv);
                if (i == 0) {
                    tv.setTextColor(Color.parseColor("#ff3f53"));
                }
            }
        }
        if (qBadgeViewUnPay == null) {
            qBadgeViewUnPay = new QBadgeView(getContext());
            qBadgeViewUnPay.bindTarget(tab.getTabAt(1).getCustomView()).setBadgeBackgroundColor(0xffff3f53)
                    .setBadgeTextSize(11, true).setBadgeGravity(Gravity.TOP | Gravity.END);
        }
        if (qBadgeViewUnUse == null) {
            qBadgeViewUnUse = new QBadgeView(getContext());
            qBadgeViewUnUse.bindTarget(tab.getTabAt(2).getCustomView()).setBadgeBackgroundColor(0xffff3f53)
                    .setBadgeTextSize(11, true).setBadgeGravity(Gravity.TOP | Gravity.END);
        }
        if (qBadgeViewUnComment == null) {
            qBadgeViewUnComment = new QBadgeView(getContext());
            qBadgeViewUnComment.bindTarget(tab.getTabAt(3).getCustomView())
                    .setBadgeTextSize(11, true).setBadgeBackgroundColor(0xffff3f53).setBadgeGravity(Gravity.TOP | Gravity.END);
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (refreshLayout.isRefreshing())
//                    refreshLayout.finishRefresh();
                page = 1;
                ordelist.clear();
                adapter.notifyDataSetChanged();
                TextView tv = tab.getCustomView().findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#ff3f53"));
                filter = tab.getPosition() + "";
                getOrderPagePresenter.getOrderList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tv = tab.getCustomView().findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#333333"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        adapter.setOnItemClickLitener(new OrderAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                goWeb(ordelist.get(position).getOrder_detail_url());
            }
        });

        adapter.setOnActionClickLitener(new OrderAdapter.OnActionClickLitener() {
            @Override
            public void onItemClick(View view, final int position) {
                OrderPageBean.DataBean.ListBean bean = ordelist.get(position);
                if (bean.getAction_type_name().equals("去付款")) {
                    goWeb(bean.getAction_url());
                } else if (bean.getAction_type_name().equals("查看券码")) {
                    goWeb(bean.getAction_url());
                } else if (bean.getAction_type_name().equals("去评价")) {
                    Intent goComment = new Intent(getContext(), CommentOrderActivity.class);
                    goComment.putExtra("id", bean.getOrder_id());
                    startActivity(goComment);
                } else if (bean.getAction_type_name().equals("删除订单")) {
                    Tools.showTipsDialog(getContext(), "", "确认要删除订单吗？", null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteOrderPresenter.deleteOrder(ordelist.get(position).getOrder_id());
                        }
                    });
                }
            }
        });

        getOrderPagePresenter = new GetOrderPagePresenter(this);
        getOrderPagePresenter.getOrderList();
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(getContext(), WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshOrderEvent event) {
        page = 1;
        ordelist.clear();
        adapter.notifyDataSetChanged();

        refreshLayout.autoRefresh();
    }

    @Override
    public void onTimeOut() {
//        super.onTimeOut();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        getOrderPagePresenter.getOrderList();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        getOrderPagePresenter.getOrderList();

    }

    @Override
    public void onDeleteOrder(BaseBean bean) {
        page = 1;
        ordelist.clear();
        adapter.notifyDataSetChanged();

        refreshLayout.autoRefresh();
    }
}
