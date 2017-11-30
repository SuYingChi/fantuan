package com.wetime.fanc.about.act;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.about.bean.VersionPageBean;
import com.wetime.fanc.about.iviews.IGetVersionPageView;
import com.wetime.fanc.about.presenter.GetVersionPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements IGetVersionPageView {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        tvTitle.setText("关于");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_check:
                GetVersionPresenter getVersionPresenter = new GetVersionPresenter(this);
                getVersionPresenter.getVersionResult();
                break;

        }
    }

    @Override
    public void onVersionResult(VersionPageBean bean) {
        int code = Tools.getVerCode(this);
        if (bean.getData().getVersion().getCode() > code) {
            Tools.showTipsDialog(this, "当前版本为V" + Tools.getVerName(this)
                            + ",最新版本为V" + bean.getData().getVersion().getName()
                            + ",是否更新到最新版本？",
                    "取消", "确定", null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Tools.goMarket(AboutActivity.this);
                        }
                    });

        } else {
            Tools.showTipsDialog(this, "赞一个，当前已是最新版本了哦~",
                    "", "确定", null, null);



        }
    }
}
