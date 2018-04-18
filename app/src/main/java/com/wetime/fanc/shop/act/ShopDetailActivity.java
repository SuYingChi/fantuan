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
import com.wetime.fanc.login.event.LoginEvent;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.order.MyRatingBar;
import com.wetime.fanc.shop.adapter.ShopDetailActAdapter;
import com.wetime.fanc.shop.adapter.ShopDetailDaijinquanAdapter;
import com.wetime.fanc.shop.adapter.ShopDetailTaocanAdapter;
import com.wetime.fanc.shop.adapter.ShopDetailYouhuiquanAdapter;
import com.wetime.fanc.shop.bean.ShopDetailBean;
import com.wetime.fanc.shop.event.ShopFoucsChangeEvent;
import com.wetime.fanc.shop.iviews.IFocusShopView;
import com.wetime.fanc.shop.iviews.IGetShopDetailView;
import com.wetime.fanc.shop.iviews.IGetShopYouhuiquanView;
import com.wetime.fanc.shop.presenter.FocusShopPresenter;
import com.wetime.fanc.shop.presenter.GetShopDetailPresenter;
import com.wetime.fanc.shop.presenter.GetShopYouhuiquanPresenter;
import com.wetime.fanc.shop.presenter.UnFocusShopPresenter;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.bottomdialog.BottomDialog;

public class ShopDetailActivity extends BaseActivity implements IGetShopDetailView, IFocusShopView, IGetShopYouhuiquanView {
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
    @BindView(R.id.ll_shopdetail)
    LinearLayout llShopDetail;
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
    @BindView(R.id.ll_comment_img)
    LinearLayout llCommentIma;
    @BindView(R.id.ntscrollview)
    NestedScrollView ntscrollview;
    @BindView(R.id.tv_loc)
    TextView tvLoc;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.tv_title_youhui)
    TextView tvTitleYouhui;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_readnum)
    TextView tvReadnum;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    @BindView(R.id.ll_dongtaiact)
    LinearLayout llDongtaiact;
    @BindView(R.id.iv_newcover)
    ImageView ivNewcover;

    private GetShopDetailPresenter getShopDetailPresenter;
    private FocusShopPresenter focusShopPresenter;
    private UnFocusShopPresenter unFocusShopPresenter;
    private GetShopYouhuiquanPresenter getShopYouhuiquanPresenter;
    private BottomDialog mBottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        tvTitle.setText("商家详情");
        rlContent.setVisibility(View.INVISIBLE);
        getShopDetailPresenter = new GetShopDetailPresenter(this);
        getShopDetailPresenter.getShopDetail();
        focusShopPresenter = new FocusShopPresenter(this);
        unFocusShopPresenter = new UnFocusShopPresenter(this);
        getShopYouhuiquanPresenter = new GetShopYouhuiquanPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        return getIntent().getStringExtra("mid");
    }

    @Override
    public void onGetShopYouhuiquan(BaseBean bean) {

    }

    @Override
    public void onFocusShop(BaseBean bean) {

    }

    @Override
    public void onGetShopDetail(final ShopDetailBean bean) {
        mBottomDialog = BottomDialog.create(getSupportFragmentManager());
        Glide.with(this).load(bean.getData().getMerchant().getLogo()).into(ivCover);
        ivCover.setOnClickListener(v -> goWeb(bean.getData().getMerchant().getPicture_url()));
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
        tvGuanzhu.setOnClickListener(v -> {
            if (TextUtils.isEmpty(spu.getToken())) {
                Tools.goLogin(mContext);
                return;
            }
            if (tvGuanzhu.getText().equals("已关注")) {
                unFocusShopPresenter.unfocusShop();
                tvGuanzhu.setText("+ 关注");
                tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner);
            } else {
                focusShopPresenter.focusShop();
                tvGuanzhu.setText("已关注");
                tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
            }
        });
        tvLoc.setText(bean.getData().getMerchant().getAddress());
        ivPhone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.getData().getMerchant().getPhone()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        ntscrollview.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > tvName.getBottom() + Tools.dip2px(mContext, 15)) {
                tvTitle.setText(bean.getData().getMerchant().getName());
            } else {
                tvTitle.setText("商家详情");
            }
        });
        // 买单优惠
        tvTitleYouhui.setText(bean.getData().getCoupon().getTitle());
        ShopDetailActAdapter actItemAdapter = new ShopDetailActAdapter(mContext, bean.getData().getCoupon().getActivity());
        lvAct.setAdapter(actItemAdapter);
        actItemAdapter.notifyDataSetChanged();

        final ShopDetailYouhuiquanAdapter youhuiquanActItemAdapter = new ShopDetailYouhuiquanAdapter(mContext, bean.getData().getCoupon().getContent());
        lvYouhuiquan.setAdapter(youhuiquanActItemAdapter);
        youhuiquanActItemAdapter.setOnGetClickLitener((view, position) -> {
            if (!bean.getData().getCoupon().getContent().get(position).isIs_get()) {
                if (TextUtils.isEmpty(spu.getToken())) {

                    Tools.goLogin(mContext);
                    return;
                }
                getShopYouhuiquanPresenter.getShopYouhuiquan(bean.getData().getCoupon().getContent().get(position).getPid());
                bean.getData().getCoupon().getContent().get(position).setIs_get(true);
                youhuiquanActItemAdapter.notifyDataSetChanged();
            }
        });
        youhuiquanActItemAdapter.notifyDataSetChanged();
        if (bean.getData().getCoupon().getContent().size() == 0
                && bean.getData().getCoupon().getActivity().size() == 0) {
            llYouhui.setVisibility(View.GONE);
        }

        if (bean.getData().getCoupon().getContent().size() > 2) {
            tvMoreYouhuiquan.setText(String.format(getString(R.string.str_more_youhuiquan)
                    , bean.getData().getCoupon().getContent().size() - 2));
            tvMoreYouhuiquan.setOnClickListener(v -> {
                // dialog layout

                mBottomDialog.setLayoutRes(R.layout.item_shop_detail_bottom_youhuiquan);
                // 可以进行一些必要对View的操作
                mBottomDialog.setViewListener(v1 -> {
                    ListViewForScrollView lvbottom = v1.findViewById(R.id.lv_bottom);
                    final ShopDetailYouhuiquanAdapter bAdapter = new ShopDetailYouhuiquanAdapter(mContext, bean.getData().getCoupon().getContent());
                    bAdapter.setIszhe(false);
                    lvbottom.setAdapter(bAdapter);
                    bAdapter.setOnGetClickLitener((view, position) -> {
                        if (!bean.getData().getCoupon().getContent().get(position).isIs_get()) {
                            if (TextUtils.isEmpty(spu.getToken())) {
                                mBottomDialog.dismiss();
                                Tools.goLogin(mContext);
                                return;
                            }
                            getShopYouhuiquanPresenter.getShopYouhuiquan(bean.getData().getCoupon().getContent().get(position).getPid());
                            bean.getData().getCoupon().getContent().get(position).setIs_get(true);
                            bAdapter.notifyDataSetChanged();
                            youhuiquanActItemAdapter.notifyDataSetChanged();
                        }
                    });
                    bAdapter.notifyDataSetChanged();
                    v1.findViewById(R.id.iv_close).setOnClickListener(v11 -> mBottomDialog.dismiss());
                    TextView tvTitle = v1.findViewById(R.id.tv_bottomtitle);
                    tvTitle.setText("优惠券免费领");
                });
                mBottomDialog.show();
            });
        } else {
            tvMoreYouhuiquan.setVisibility(View.GONE);
        }
        //团购套餐
        ShopDetailTaocanAdapter taocanAdapter = new ShopDetailTaocanAdapter(mContext, bean.getData().getGroupon().getContent());
        lvTaocan.setAdapter(taocanAdapter);
        taocanAdapter.setOnBuyTaocanClickLitener((view, position) -> {
            if (TextUtils.isEmpty(spu.getToken())) {
                Tools.goLogin(mContext);
                return;
            }
            goWeb(bean.getData().getGroupon().getContent().get(position).getBuy_url());
        });
        lvTaocan.setOnItemClickListener((parent, view, position, id) -> goWeb(bean.getData().getGroupon().getContent().get(position).getDetail_url()));

        taocanAdapter.notifyDataSetChanged();

        if (bean.getData().getGroupon().getContent().size() > 2) {
            tvMoreTaocan.setText(String.format(getString(R.string.str_more_taocan)
                    , bean.getData().getGroupon().getContent().size() - 2));
            tvMoreTaocan.setOnClickListener(v -> {
                mBottomDialog.setLayoutRes(R.layout.item_shop_detail_bottom_dliver);
                // 可以进行一些必要对View的操作
                mBottomDialog.setViewListener(v12 -> {
                    ListViewForScrollView lvbottom = v12.findViewById(R.id.lv_bottom);
                    ShopDetailTaocanAdapter bAdapter = new ShopDetailTaocanAdapter(mContext, bean.getData().getGroupon().getContent());
                    bAdapter.setIszhe(false);
                    lvbottom.setAdapter(bAdapter);
                    bAdapter.setOnBuyTaocanClickLitener((view, position) -> {
                        mBottomDialog.dismiss();
                        if (TextUtils.isEmpty(spu.getToken())) {
                            Tools.goLogin(mContext);
                            return;
                        }
                        goWeb(bean.getData().getGroupon().getContent().get(position).getBuy_url());
                    });
                    bAdapter.notifyDataSetChanged();
                    lvbottom.setOnItemClickListener((parent, view, position, id) -> {
                        mBottomDialog.dismiss();
                        goWeb(bean.getData().getGroupon().getContent().get(position).getDetail_url());
                    });

                    v12.findViewById(R.id.iv_close).setOnClickListener(v121 -> mBottomDialog.dismiss());
                    TextView tvTitle = v12.findViewById(R.id.tv_bottomtitle);
                    tvTitle.setText("团购套餐");
                });
                mBottomDialog.show();
            });
        } else {
            tvMoreTaocan.setVisibility(View.GONE);
        }
        if (bean.getData().getGroupon() == null
                || bean.getData().getGroupon().getContent().size() == 0)
            llTaocan.setVisibility(View.GONE);

        //代金券
        ShopDetailDaijinquanAdapter daijinquanAdapter = new ShopDetailDaijinquanAdapter(mContext, bean.getData().getVoucher().getContent());
        lvDaijinquan.setAdapter(daijinquanAdapter);
        daijinquanAdapter.setOnBuyDaijinquanClickLitener((view, position) -> {
            if (TextUtils.isEmpty(spu.getToken())) {
                Tools.goLogin(mContext);
                return;
            }
            goWeb(bean.getData().getVoucher().getContent().get(position).getBuy_url());
        });
        daijinquanAdapter.notifyDataSetChanged();
        lvDaijinquan.setOnItemClickListener((parent, view, position, id) -> goWeb(bean.getData().getVoucher().getContent().get(position).getDetail_url()));
        if (bean.getData().getVoucher().getContent().size() > 2) {
            tvMoreDaijinquan.setText(String.format(getString(R.string.str_more_daijinquan)
                    , bean.getData().getVoucher().getContent().size() - 2));
            tvMoreDaijinquan.setOnClickListener(v -> {
                mBottomDialog.setLayoutRes(R.layout.item_shop_detail_bottom_dliver);
                // 可以进行一些必要对View的操作
                mBottomDialog.setViewListener(v13 -> {
                    ListViewForScrollView lvbottom = v13.findViewById(R.id.lv_bottom);
                    ShopDetailDaijinquanAdapter bAdapter = new ShopDetailDaijinquanAdapter(mContext, bean.getData().getVoucher().getContent());
                    bAdapter.setIszhe(false);
                    lvbottom.setAdapter(bAdapter);
                    bAdapter.setOnBuyDaijinquanClickLitener((view, position) -> {
                        mBottomDialog.dismiss();
                        if (TextUtils.isEmpty(spu.getToken())) {
                            Tools.goLogin(mContext);
                            return;
                        }
                        goWeb(bean.getData().getVoucher().getContent().get(position).getBuy_url());
                    });
                    lvbottom.setOnItemClickListener((parent, view, position, id) -> {
                        mBottomDialog.dismiss();
                        goWeb(bean.getData().getVoucher().getContent().get(position).getDetail_url());
                    });
                    bAdapter.notifyDataSetChanged();

                    v13.findViewById(R.id.iv_close).setOnClickListener(v131 -> mBottomDialog.dismiss());
                    TextView tvTitle = v13.findViewById(R.id.tv_bottomtitle);
                    tvTitle.setText("代金券");
                });
                mBottomDialog.show();
            });
        } else {
            tvMoreDaijinquan.setVisibility(View.GONE);
        }
        if (bean.getData().getVoucher() == null || bean.getData().getVoucher().getContent().size() == 0)
            llDaijinquan.setVisibility(View.GONE);

        // 购物中心
        if (bean.getData().getMall().getContent().size() > 0) {
            Glide.with(this).load(bean.getData().getMall().getContent().get(0).getLogo_url()).into(ivCenterCover);
            tvCentername.setText(bean.getData().getMall().getContent().get(0).getName());
            llShopDetail.setOnClickListener(v -> goWeb(bean.getData().getMall().getContent().get(0).getMall_url()));

        } else {
            llShopcenter.setVisibility(View.GONE);
        }
        //用户评价
        if (bean.getData().getReview().getContent() != null && bean.getData().getReview().getContent().size() > 0) {
            tvCommentnum.setText(String.format("(%s)", bean.getData().getReview().getTotals()));
            Glide.with(this).load(bean.getData().getReview().getContent().get(0).getUser().getAvatar()).into(icCommentHead);
            tvCommentUsername.setText(bean.getData().getReview().getContent().get(0).getUser().getUsername());
            tvCommentTime.setText(bean.getData().getReview().getContent().get(0).getReview_time());
            rbSocreComment.setStar(Float.valueOf(bean.getData().getReview().getContent().get(0).getScore()));
            tvCommnentScore.setText(String.format("%s分", bean.getData().getReview().getContent().get(0).getScore()));
            tvCommnentContent.setText(bean.getData().getReview().getContent().get(0).getContent());
            if (bean.getData().getReview().getContent().get(0).getImageUrl().size() > 0) {
                Glide.with(this).load(bean.getData().getReview().getContent().get(0)
                        .getImageUrl().get(0)).into(ivCommnet0);
            }
            if (bean.getData().getReview().getContent().get(0).getImageUrl().size() > 1) {
                Glide.with(this).load(bean.getData().getReview().getContent().get(0)
                        .getImageUrl().get(1)).into(ivCommnet1);
            }
            if (bean.getData().getReview().getContent().get(0).getImageUrl().size() > 2) {
                Glide.with(this).load(bean.getData().getReview().getContent().get(0)
                        .getImageUrl().get(2)).into(ivCommnet2);
            }
            if (bean.getData().getReview().getContent().get(0).getImageUrl().size() == 0) {
                llCommentIma.setVisibility(View.GONE);
            }
            tvMorecomment.setOnClickListener(v -> goWeb(bean.getData().getReview().getReview_url()));
        } else {
            llComment.setVisibility(View.GONE);
        }
        // 商家动态

        tvMoredongtai.setOnClickListener(v -> {
            Intent go = new Intent(mContext, ShopSayActivity.class);
            go.putExtra("data", bean);
            go.putExtra("mid", getMId());
            startActivity(go);
//                goWeb(bean.getData().getPost().getPost_url());
        });

        if (bean.getData().getPost().getContent().size() > 0) {
            tvDongtaicontent.setText(bean.getData().getPost().getContent().get(0).getContent());
            tvDongtaitime.setText(bean.getData().getPost().getContent().get(0).getCreate_at());
            llNews.setVisibility(View.GONE);
        } else if (bean.getData().getNews().getContent().size() > 0) {
            name.setText(bean.getData().getNews().getContent().get(0).getName());
            tvAuthor.setText(bean.getData().getNews().getContent().get(0).getNews_name());
            tvTime.setText(bean.getData().getNews().getContent().get(0).getTime());
            tvReadnum.setText(bean.getData().getNews().getContent().get(0).getRead_num());
            if (TextUtils.isEmpty(bean.getData().getNews().getContent().get(0).getNews_name())) {
                tvAuthor.setVisibility(View.GONE);
            } else {
                tvAuthor.setVisibility(View.VISIBLE);
            }
            Glide.with(mContext).load(bean.getData().getNews().getContent().get(0)
                    .getCover().get(0)).into(ivNewcover);
            llDongtaiact.setVisibility(View.GONE);
            llNews.setOnClickListener(view -> {
                Tools.goWeb(mContext,bean.getData().getNews().getContent().get(0).getArticle_url()         );
            });
        } else {
            llDongtai.setVisibility(View.GONE);
        }


        // 底部
        if (TextUtils.isEmpty(bean.getData().getMerchant().getAffiche())) {
            llGonggao.setVisibility(View.GONE);
        } else {
            tvGonggao.setText(bean.getData().getMerchant().getAffiche());
        }
        tvBusinesstime.setText(bean.getData().getMerchant().getBusiness_hours());
        if (bean.getData().getMerchant().getSpider().equals("1")) {
            tvBottom.setVisibility(View.GONE);
        } else {
            tvBottom.setText(bean.getData().getMerchant().getDiscounts());
            tvBottom.setOnClickListener(v -> {
                if (TextUtils.isEmpty(spu.getToken())) {
                    Tools.goLogin(mContext);
                    return;
                }
                Tools.goWeb(mContext, bean.getData().getMerchant().getPayment_url());
            });
        }


        rlContent.setVisibility(View.VISIBLE);
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginEvent event) {
        getShopDetailPresenter.getShopDetail();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ShopFoucsChangeEvent event) {
        if (tvGuanzhu.getText().equals("已关注")) {
            tvGuanzhu.setText("+ 关注");
            tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner);
        } else {
            tvGuanzhu.setText("已关注");
            tvGuanzhu.setBackgroundResource(R.drawable.bg_btn_red_corner_enable);
        }
    }
}
