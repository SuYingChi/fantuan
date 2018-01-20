package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.utils.TwoDigitsTextWatcher;
import com.wetime.fanc.wallet.iviews.ICashOutView;
import com.wetime.fanc.wallet.presenter.CashOutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashOutActivity extends BaseActivity implements ICashOutView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_wxname)
    TextView tvWxname;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_cashall)
    TextView tvCashall;
    @BindView(R.id.tv_ok)
    TextView tvOk;


    private CashOutPresenter cashOutPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashout);
        ButterKnife.bind(this);
        tvTitle.setText("余额提现");
        tvWxname.setText(String.format("微信昵称：%s", getIntent().getStringExtra("wxname")));
        tvBalance.setText(String.format("￥%s", getIntent().getStringExtra("balance")));
        initView();

        cashOutPresenter = new CashOutPresenter(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_cashall, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Tools.hideSoftInput(this);
                onBackPressed();
                break;
            case R.id.tv_cashall:
                etNum.setText(getIntent().getStringExtra("balance"));
                break;
            case R.id.tv_ok:
                if (TextUtils.isEmpty(etNum.getText().toString())
                        || Double.valueOf(etNum.getText().toString()) == 0) {
                    Tools.toastInBottom(mContext, "请输入提现金额");
                } else if (Double.valueOf(etNum.getText().toString())
                        > Double.valueOf(getIntent().getStringExtra("balance"))) {
                    Tools.toastInBottom(mContext, "提现金额不能大于余额");
                } else {
                    Intent go = new Intent(mContext, InputPwdActivity.class);
                    go.putExtra("num", etNum.getText().toString());
                    go.putExtra("phone", getIntent().getStringExtra("phone"));
                    startActivityForResult(go, InputPwdActivity.REQUSTCODEPWD);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == InputPwdActivity.REQUSTCODEPWD && resultCode == RESULT_OK) {
            String pwd = data.getStringExtra("pwd");
//            cashOutPresenter.cashOut(pwd);
            Tools.toastInBottom(mContext, "还没给接口，暂时不要测试");
        }
    }

    private void initView() {
        etNum.addTextChangedListener(new TwoDigitsTextWatcher(etNum));
    }

    @Override
    public void onCashOutResult(BaseBean bean) {

    }

    @Override
    public String getNum() {
        return etNum.getText().toString();
    }
}
