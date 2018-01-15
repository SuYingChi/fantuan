package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteHomeActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invatehome);
        ButterKnife.bind(this);
        tvTitle.setText("邀请有礼");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back,R.id.tv_cashout,R.id.tv_inviteqr,R.id.tv_invitehistory,
            R.id.tv_authentication,R.id.tv_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_cashout:
                Tools.goActivity(mContext,MyWalletActivity.class);
                break;
            case R.id.tv_inviteqr:
                Tools.goActivity(mContext,InviteQRActivity.class);
                break;
            case R.id.tv_invitehistory:
                Tools.goActivity(mContext,InviteHistoryActivity.class);
                break;
            case R.id.tv_authentication:
                Tools.goWeb(mContext,"https://www.baidu.com");
                break;
            case R.id.tv_rule:
                Tools.goWeb(mContext,"https://www.baidu.com");
                break;
        }
    }

}
