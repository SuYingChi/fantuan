package com.wetime.fanc.wallet.act;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashOutActivity extends BaseActivity {


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

    private int digits = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashout);
        ButterKnife.bind(this);
        tvTitle.setText("余额提现");
        tvWxname.setText(String.format("微信昵称：%s", getIntent().getStringExtra("wxname")));
        tvBalance.setText(String.format("￥%s", getIntent().getStringExtra("balance")));
        initView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back,R.id.tv_cashall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Tools.hideSoftInput(this);
                onBackPressed();
                break;
            case R.id.tv_cashall:
                etNum.setText(getIntent().getStringExtra("balance"));
                break;

        }
    }
    private void initView(){
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + digits + 1);
                        etNum.setText(s);
                        etNum.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etNum.setText(s);
                    etNum.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etNum.setText(s.subSequence(0, 1));
                        etNum.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
