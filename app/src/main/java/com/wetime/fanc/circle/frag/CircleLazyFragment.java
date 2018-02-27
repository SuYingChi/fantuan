package com.wetime.fanc.circle.frag;

import android.view.View;
import android.widget.ImageView;

import com.wetime.fanc.R;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;


public class CircleLazyFragment extends BaseLazyFragment {


    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_edit)
    ImageView ivEdit;

    private QBadgeView qBadgeMsg;

    private int temp = 9;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initView() {
        qBadgeMsg = new QBadgeView(getContext());
        qBadgeMsg.setBadgeTextSize(11, true);
        qBadgeMsg.bindTarget(ivMsg);
//        qBadgeMsg.setBadgeNumber(temp++);
//        qBadgeMsg.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_msg, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                Tools.toastInBottom(getContext(),"aaaaa");
//                qBadgeMsg.setVisibility(View.VISIBLE);
                qBadgeMsg.setBadgeNumber(temp++);
                break;
            case R.id.iv_edit:
                qBadgeMsg.setBadgeNumber(temp+100);
                break;
        }
    }
}
