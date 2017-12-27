package com.wetime.fanc.shop.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.ListViewForScrollView;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.bean.ShopDetailBean;
import com.wetime.fanc.shop.iviews.IGetShopDetailView;
import com.wetime.fanc.shop.presenter.GetShopDetailPresenter;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopDetailActivity extends BaseActivity implements IGetShopDetailView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rb_socre)
    MyRatingBar rbSocre;
    @BindView(R.id.tv_spend)
    TextView tvSpend;
    @BindView(R.id.tv_seal)
    TextView tvSeal;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_headcentername)
    TextView tvHeadcentername;
    @BindView(R.id.tv_dis)
    TextView tvDis;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.ll_act)
    LinearLayout llAct;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.ll_headinfo)
    LinearLayout llHeadinfo;
    @BindView(R.id.lv_act)
    ListViewForScrollView lvAct;
    @BindView(R.id.lv_youhuiquan)
    ListViewForScrollView lvYouhuiquan;
    @BindView(R.id.tv_more_youhuiquan)
    TextView tvMoreYouhuiquan;
    @BindView(R.id.ll_youhui)
    LinearLayout llYouhui;
    @BindView(R.id.lv_taocan)
    ListViewForScrollView lvTaocan;
    @BindView(R.id.tv_more_taocan)
    TextView tvMoreTaocan;
    @BindView(R.id.ll_taocan)
    LinearLayout llTaocan;
    @BindView(R.id.lv_daijinquan)
    ListViewForScrollView lvDaijinquan;
    @BindView(R.id.tv_more_daijinquan)
    TextView tvMoreDaijinquan;
    @BindView(R.id.ll_daijinquan)
    LinearLayout llDaijinquan;
    @BindView(R.id.iv_center_cover)
    ImageView ivCenterCover;
    @BindView(R.id.tv_centername)
    TextView tvCentername;
    @BindView(R.id.ll_shopcenter)
    LinearLayout llShopcenter;
    @BindView(R.id.tv_commentnum)
    TextView tvCommentnum;
    @BindView(R.id.tv_morecomment)
    TextView tvMorecomment;
    @BindView(R.id.ic_comment_head)
    CircleImageView icCommentHead;
    @BindView(R.id.tv_comment_username)
    TextView tvCommentUsername;
    @BindView(R.id.tv_comment_time)
    TextView tvCommentTime;
    @BindView(R.id.rb_socre_comment)
    MyRatingBar rbSocreComment;
    @BindView(R.id.tv_commnent_score)
    TextView tvCommnentScore;
    @BindView(R.id.tv_commnent_content)
    TextView tvCommnentContent;
    @BindView(R.id.iv_commnet0)
    ImageView ivCommnet0;
    @BindView(R.id.iv_commnet1)
    ImageView ivCommnet1;
    @BindView(R.id.iv_commnet2)
    ImageView ivCommnet2;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.tv_moredongtai)
    TextView tvMoredongtai;
    @BindView(R.id.tv_dongtaicontent)
    TextView tvDongtaicontent;
    @BindView(R.id.tv_dongtaitime)
    TextView tvDongtaitime;
    @BindView(R.id.ll_dongtai)
    LinearLayout llDongtai;
    @BindView(R.id.tv_gonggao)
    TextView tvGonggao;
    @BindView(R.id.ll_gonggao)
    LinearLayout llGonggao;
    @BindView(R.id.tv_businesstime)
    TextView tvBusinesstime;
    @BindView(R.id.ll_businesstime)
    LinearLayout llBusinesstime;
    @BindView(R.id.ll_moreinfo)
    LinearLayout llMoreinfo;
    @BindView(R.id.ntscrollview)
    NestedScrollView ntscrollview;
    @BindView(R.id.tv_loc)
    TextView tvLoc;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    private GetShopDetailPresenter getShopDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        tvTitle.setText("商家详情");
        rlContent.setVisibility(View.INVISIBLE);
        getShopDetailPresenter = new GetShopDetailPresenter(this);
        getShopDetailPresenter.getShopDetail();
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
    public String getMId() {
        return "55";
    }

    @Override
    public void onGetShopDetail(final ShopDetailBean bean) {
        Glide.with(this).load(bean.getData().getMerchant().getLogo()).into(ivCover);
        ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWeb(bean.getData().getMerchant().getPost_url());
            }
        });
        tvName.setText(bean.getData().getMerchant().getName());
        rbSocre.setStar(Float.valueOf(bean.getData().getMerchant().getScore()));
        tvSpend.setText(bean.getData().getMerchant().getAverage_spend());
        tvSeal.setText(bean.getData().getMerchant().getSales());
        tvType.setText(bean.getData().getMerchant().getCategory_name());
        tvHeadcentername.setText(bean.getData().getMerchant().getMall());
        tvDis.setText(bean.getData().getMerchant().getDistance());
        if (bean.getData().isFocus()) {
            tvGuanzhu.setText("已关注");
            tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
        } else {
            tvGuanzhu.setText("+ 关注");
            tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner);
        }
        tvLoc.setText(bean.getData().getMerchant().getAddress());
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.getData().getMerchant().getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        ntscrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > tvName.getBottom() + Tools.dip2px(mContext, 15)) {
                    tvTitle.setText(bean.getData().getMerchant().getName());
                } else {
                    tvTitle.setText("商家详情");
                }
            }
        });
        // 底部
        if (TextUtils.isEmpty(bean.getData().getMerchant().getAffiche())) {
            llGonggao.setVisibility(View.GONE);
        } else {
            tvGonggao.setText(bean.getData().getMerchant().getAffiche());
        }
        tvBusinesstime.setText(bean.getData().getMerchant().getBusiness_hours());
        if (bean.getData().getMerchant().getSpider().equals("1")) {
            tvBottom.setVisibility(View.GONE);
        }else {
            tvBottom.setText(bean.getData().getMerchant().getDiscounts());
        }


        rlContent.setVisibility(View.VISIBLE);
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }
}
