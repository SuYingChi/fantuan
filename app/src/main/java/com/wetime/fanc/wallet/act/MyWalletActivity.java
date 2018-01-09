package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.bean.WalletBalanceBean;
import com.wetime.fanc.wallet.iviews.IGetWalletBalanceView;
import com.wetime.fanc.wallet.presenter.GetWalletBalancePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity implements IGetWalletBalanceView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_pay_pwd)
    TextView tvPayPwd;
    @BindView(R.id.tv_cashout)
    TextView tvCashout;
    private GetWalletBalancePresenter getWalletBalancePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        ButterKnife.bind(this);
        tvTitle.setText("我的钱包");
        getWalletBalancePresenter = new GetWalletBalancePresenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWalletBalancePresenter.getWalletBalance();
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

    @Override
    public void onGetWalletBalance(final WalletBalanceBean bean) {
        tvNum.setText(bean.getData().getMoney());
        tvTips.setText(String.format("余额需大于等于%s元才可提现哦~"
                , bean.getData().getWithdraw_money_min()));

        tvPayPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getData().getHas_set_pwd().equals("0")) {
                    Tools.showTipsDialog(mContext, "", "需设置支付密码后才可提现哦",
                            "取消", "设置支付密码", null,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent go = new Intent(mContext, SetPayPwdActivity.class);
                                    go.putExtra("title","设置支付密码");
                                    startActivity(go);
                                }
                            });
                } else {
                    Tools.showTipsDialog(mContext, "", "确定修改支付密码",
                            "取消", "修改", null,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent go = new Intent(mContext, VerfyPhoneNumActivity.class);
                                    go.putExtra("phone",bean.getData().getPhone());
                                    startActivity(go);
                                }
                            });
                }
            }
        });


        tvCashout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getData().getHas_bind_wechat().equals("1")) {

                } else {

                }
            }
        });
    }
}
