package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.batterytest.fbaselib.main.BaseFragment;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.shopcenter.bean.OrderPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetOrderListView;
import com.wetime.fanc.shopcenter.presenter.GetOrderPagePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class OrderFragment extends BaseFragment implements IGetOrderListView {
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;
    Unbinder unbinder;
    @BindView(R.id.tab)
    TabLayout tab;


    private GetOrderPagePresenter getOrderPagePresenter;
    private int page = 1;
    private String type = "0";
    private String filter = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View v = inflater.inflate(R.layout.fragment_order, null);
        unbinder = ButterKnife.bind(this, v);
        initView();
        return v;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                Intent go = new Intent(getContext(), LoginActivity.class);
                startActivity(go);
                break;

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
    public void onGetOrderPage(OrderPageBean bean) {

    }

    private void initView() {
        if (spu.getToken().equals("")) {
            rlEmpty.setVisibility(View.VISIBLE);
        } else {
            rlEmpty.setVisibility(View.GONE);
            getOrderPagePresenter = new GetOrderPagePresenter(this);
            getOrderPagePresenter.getOrderList();
        }


        String[] str = {"全部", "待付款", "待使用", "待评价"};
        for (int i = 0; i < str.length; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tab_with_red, null);
            TextView tv = view.findViewById(R.id.tv_title);
            tv.setText(str[i]);
            tab.getTabAt(i).setCustomView(tv);
            if(i==0){
                tv.setTextColor(Color.parseColor("#ff3f53"));
            }

//        new QBadgeView(getContext()).bindTarget(tab.getTabAt(0).getCustomView())
//                .setBadgeNumber(5).setBadgeTextSize(11,true).setBadgeGravity(Gravity.TOP|Gravity.END);

        }

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Tools.toastInBottom(getContext(), tab.getPosition() + "");
                TextView tv =tab.getCustomView().findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#ff3f53"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tv =tab.getCustomView().findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#333333"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
