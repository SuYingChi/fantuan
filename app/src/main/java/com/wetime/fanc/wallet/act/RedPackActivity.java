package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RedPackActivity extends BaseActivity {


    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_know)
    TextView tvKnow;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redpack);
        ButterKnife.bind(this);
        tvNum.setText(String.format("¥%s", getIntent().getStringExtra("num")));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_close, R.id.tv_know})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.tv_know:
                onBackPressed();
                break;

        }
    }

}
