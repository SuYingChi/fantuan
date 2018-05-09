package com.wetime.fanc.my.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.about.act.AboutActivity;
import com.wetime.fanc.main.frag.BaseLazyFragment;
import com.wetime.fanc.my.act.MyCollectActivity;
import com.wetime.fanc.my.bean.MyProfileBean;
import com.wetime.fanc.my.iviews.IGetMyprofileView;
import com.wetime.fanc.my.presenter.GetMyProfilePresenter;
import com.wetime.fanc.setting.act.SettingActivity;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends BaseLazyFragment implements IGetMyprofileView {
    @BindView(R.id.tv_actnum)
    TextView tvActnum;
    @BindView(R.id.tv_focusnum)
    TextView tvFocusnum;
    @BindView(R.id.tv_fansnum)
    TextView tvFansnum;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_beauthor)
    TextView tvBeauthor;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_news)
    ImageView ivNews;
    @BindView(R.id.iv_reddot)
    ImageView ivRedDot;
    @BindView(R.id.ll_beauthor)
    LinearLayout llBeauthor;


//    private QBadgeView qBadgeMsg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        GetMyProfilePresenter getMyProfilePresenter = new GetMyProfilePresenter(this);
        getMyProfilePresenter.getUserInfo();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_collect, R.id.tv_setting, R.id.tv_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collect:
                MyCollectActivity.goMyCollectAct(getContext());
                break;
            case R.id.tv_setting:
                SettingActivity.goSettingAct(getContext());
                break;
            case R.id.tv_about:
                Intent goAbout = new Intent(getContext(), AboutActivity.class);
                startActivity(goAbout);
                break;
        }
    }

    @Override
    public void onGetMyProfile(MyProfileBean bean) {
        Context context = getContext();
        if (context == null)
            return;

        Glide.with(context).load(bean.getData().getAvatar()).into(civHead);
        tvName.setText(bean.getData().getUsername());
        tvActnum.setText(bean.getData().getDynamic_num());
        tvFocusnum.setText(bean.getData().getFollow_num());
        tvFansnum.setText(bean.getData().getFans_num());
        if (!TextUtils.isEmpty(bean.getData().getToutiao_apply_url())) {
            tvBeauthor.setOnClickListener(v -> Tools.goWeb(getContext(), bean.getData().getToutiao_apply_url()));
        }
        if (bean.getData().isIs_news()) {
            ivNews.setVisibility(View.VISIBLE);
            llBeauthor.setVisibility(View.GONE);
        } else {
            ivNews.setVisibility(View.GONE);
            llBeauthor.setVisibility(View.VISIBLE);
        }
        if (bean.getData().isHas_new_fans()) {
            ivRedDot.setVisibility(View.VISIBLE);
        } else {
            ivRedDot.setVisibility(View.GONE);
        }

    }
}
