package com.wetime.fanc.order.act;

import android.os.Bundle;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.order.frag.OrderFragment;

import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        ButterKnife.bind(this);
        OrderFragment orderFragment = new OrderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, orderFragment, "f1")
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
