package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.MyInfoBean;
import com.wetime.fanc.home.event.RefreshRedNunEvent;
import com.wetime.fanc.home.iviews.IGetMyInfoView;
import com.wetime.fanc.home.presenter.GetUserInfoPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.login.event.LogoutEvent;
import com.wetime.fanc.main.frag.BaseFragment;
import com.wetime.fanc.setting.act.SettingActivity;
import com.wetime.fanc.setting.event.ChangeUserInfoEvent;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.wallet.act.InviteHomeActivity;
import com.wetime.fanc.wallet.act.MyWalletActivity;
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

    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationOption = null;

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


    @OnClick({R.id.ll_call, R.id.iv_setting, R.id.ll_login, R.id.tv_fanpiao, R.id.tv_youhuiquan,
            R.id.tv_guanzhu, R.id.tv_message, R.id.tv_comment, R.id.tv_waimai, R.id.tv_wallet, R.id.ll_invite,
            R.id.ll_redpacket})
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
            case R.id.tv_wallet:
                if (bean != null) {
                    Tools.goActivity(getContext(), MyWalletActivity.class);
                } else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.ll_redpacket:
                if (bean != null) {
                    if (TextUtils.isEmpty(spu.getValue("jd")) && TextUtils.isEmpty(spu.getValue("wd"))) {
                        // 去定位
                        initLoaction();
                    } else {
                        Tools.toastInBottom(getContext(), "领红包");
                    }
                } else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.ll_invite:
                if (bean != null) {
                    if (TextUtils.isEmpty(bean.getData().getLink().getInviter().getUrl())) {
                        Tools.goActivity(getContext(), InviteHomeActivity.class);
                    } else {
                        Tools.goWeb(getContext(), bean.getData().getLink().getInviter().getUrl());
                    }

                } else {
                    Tools.toastInBottom(getContext(), "请先登录");
                    Intent goLogin = new Intent(getContext(), LoginActivity.class);
                    startActivity(goLogin);
                }

                break;
        }
    }

    private void goMessage() {
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

        Intent goweb = new Intent(getContext(), WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }

    @Override
    public void onGetUserInfo(MyInfoBean bean) {
        this.bean = bean;
        Glide.with(getContext()).load(bean.getData().getUser().getAvatar()).into(civHead);
        tvName.setText(bean.getData().getUser().getUsername());

        QBred.setBadgeNumber(Integer.valueOf(bean.getData().getNotice_num()));
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
        QBred.setBadgeNumber(0);
    }

    private void initLoaction() {

        mLocationClient = new AMapLocationClient(getContext().getApplicationContext());

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationOption);


        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。

                        Log.e("zk 纬度", String.valueOf(amapLocation.getLatitude()));
                        Log.e("zk 经度", String.valueOf(amapLocation.getLongitude()));


                        spu.setValue("wd", String.valueOf(amapLocation.getLatitude()));
                        spu.setValue("jd", String.valueOf(amapLocation.getLongitude()));
                        //进行操作 获取红包操作


                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("zk", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                        spu.setValue("wd", "");
                        spu.setValue("jd", "");
                        Tools.toastInBottom(getContext(), "获取位置信息失败");
                        Tools.showTipsDialog(getContext(), "", "需开启定位且位置为海南才可领取红包",
                                "取消", "开启定位", null, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
                                        startActivity(intent);
                                    }
                                });
                    }
                }


                mLocationClient.stopLocation();
                dismissLoading();
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
        showLoading();

    }
}
