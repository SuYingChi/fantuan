package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.king.batterytest.fbaselib.main.BaseFragment;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.MyInfoBean;
import com.wetime.fanc.home.event.RefreshRedNunEvent;
import com.wetime.fanc.home.iviews.IGetMyInfoView;
import com.wetime.fanc.home.presenter.GetUserInfoPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.setting.act.SettingActivity;
import com.wetime.fanc.setting.event.ChangeUserInfoEvent;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import q.rorbin.badgeview.QBadgeView;


public class MyFragment extends BaseFragment implements IGetMyInfoView {


    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_fanpiao)
    TextView tvFanpiao;
    @BindView(R.id.tv_youhuiquan)
    TextView tvYouhuiquan;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_waimai)
    TextView tvWaimai;
    Unbinder unbinder;
    @BindView(R.id.tv_rednum)
    TextView tvRednum;
    @BindView(R.id.ll_call)
    LinearLayout llCall;

    private GetUserInfoPresenter getUserInfoPresenter;
    private MyInfoBean bean;
    private QBadgeView QBred;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View v = inflater.inflate(R.layout.fragment_my, null);
        unbinder = ButterKnife.bind(this, v);

        QBred = new QBadgeView(getContext());
        QBred.bindTarget(tvRednum).setBadgeBackgroundColor(0xffff3f53)
                .setBadgeTextSize(11, true).setBadgeGravity(Gravity.CENTER);
        QBred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMessage();
            }
        });

        getUserInfoPresenter = new GetUserInfoPresenter(this);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.ll_call, R.id.iv_setting, R.id.ll_login, R.id.tv_fanpiao, R.id.tv_youhuiquan, R.id.tv_guanzhu, R.id.tv_message, R.id.tv_comment, R.id.tv_waimai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:400-3663-2552"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_setting:
                if (bean != null) {
                    Intent goSetting = new Intent(getContext(), SettingActivity.class);
                    startActivity(goSetting);
                } else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }

                break;
            case R.id.ll_login:
                if (spu.getToken().equals("")) {
                    Intent go = new Intent(getContext(), LoginActivity.class);
                    startActivity(go);
                }

                break;
            case R.id.tv_fanpiao:
                if (bean != null)
                    goWeb(bean.getData().getLink().getReward().getUrl());
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.tv_youhuiquan:
                if (bean != null)
                    goWeb(bean.getData().getLink().getCoupon().getUrl());
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.tv_guanzhu:
                if (bean != null)
                    goWeb(bean.getData().getLink().getFocus().getUrl());
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.tv_message:
                goMessage();
                break;
            case R.id.tv_comment:
                if (bean != null)
                    goWeb(bean.getData().getLink().getReview().getUrl());
                else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.tv_waimai:

                break;
        }
    }
    private void goMessage(){
        if (bean != null)
            goWeb(bean.getData().getLink().getNotice().getUrl());
        else {
            Tools.toastInBottom(getContext(), "请先登录");
            Intent goLogin = new Intent(getContext(), LoginActivity.class);
            startActivity(goLogin);
        }
    }
    private void goWeb(String url) {
        if (spu.getToken().equals("")) {
            Tools.toastInBottom(getContext(), "请先登录");
            Intent goLogin = new Intent(getContext(), LoginActivity.class);
            startActivity(goLogin);
            return;
        }

        if (url.equals("")) {
            url = "http://www.baidu.com";
        }
        Intent goweb = new Intent(getContext(), WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }

    @Override
    public void onGetUserInfo(MyInfoBean bean) {
        this.bean = bean;
        Glide.with(getActivity().getApplicationContext())
                .load(bean.getData().getUser().getAvatar()).into(civHead);
        tvName.setText(bean.getData().getUser().getUsername());


        QBred.setBadgeNumber(bean.getData().getNotice_num());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!spu.getToken().equals(""))
            getUserInfoPresenter.getUserInfo();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        getUserInfoPresenter.getUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshRedNunEvent event) {
        if (!spu.getToken().equals(""))
            getUserInfoPresenter.getUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangeUserInfoEvent event) {
        getUserInfoPresenter.getUserInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogoutEvent event) {
        this.bean = null;
        tvName.setText("登录/注册");
//        civHead.setImageResource(R.drawable.ic_head_default);
        Glide.with(this).load(R.drawable.ic_head_default).apply(new RequestOptions().placeholder(R.drawable.ic_head_default)).into(civHead);
    }
}
