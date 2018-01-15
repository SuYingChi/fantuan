package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteHomeActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        ButterKnife.bind(this);
        tvTitle.setText("邀请有礼");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

        }
    }

}
