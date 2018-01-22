package com.wetime.fanc.wallet.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;;

import com.wetime.fanc.R;
import com.wetime.fanc.customview.Keyboard;
import com.wetime.fanc.customview.PayEditText;
import com.wetime.fanc.main.act.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputPwdActivity extends BaseActivity {


    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.pay_et)
    PayEditText payEt;
    @BindView(R.id.keyboard_pay)
    Keyboard keyboard;
    @BindView(R.id.ll_outside)
    LinearLayout llOutside;

    public static final int REQUSTCODEPWD = 9000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pwd);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvNum.setText(String.format("¥%s", getIntent().getStringExtra("num")));
        initKeyBorad();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_close, R.id.ll_outside, R.id.tv_forgetpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.ll_outside:
                onBackPressed();
                break;
            case R.id.tv_forgetpwd:
                onBackPressed();
                Intent go = new Intent(mContext, VerfyPhoneNumActivity.class);
                go.putExtra("phone", getIntent().getStringExtra("phone"));
                startActivity(go);
                break;

        }
    }

    private void initKeyBorad() {
        //设置键盘
        keyboard.setKeyboardKeys(Keyboard.KEY);
        //键盘键的点击事件
        keyboard.setOnClickKeyboardListener(new Keyboard.OnClickKeyboardListener() {
            @Override
            public void onKeyClick(int position, String value) {
                if (position < 11 && position != 9) {
                    payEt.add(value);
                } else if (position == 9) {

                } else if (position == 11) {
                    payEt.remove();
//                    //当点击d键盘上的完成时候，也可以通过payEditText.getText()获取密码，此时不应该注册OnInputFinishedListener接口
//                    Toast.makeText(getApplication(), "您的密码是：" + payEditText.getText(), Toast.LENGTH_SHORT).show();
//                    finish();
                }
            }
        });

        //当密码输入完成时的回调
        payEt.setOnInputFinishedListener(new PayEditText.OnInputFinishedListener() {
            @Override
            public void onInputFinished(String password) {
//                Toast.makeText(getApplication(), "您的密码是：" + password, Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent();
                mIntent.putExtra("pwd", password);
                setResult(RESULT_OK, mIntent);
                finish();
            }
        });
    }
}
