package com.wetime.fanc.my.act;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.home.adapter.HomeItemAdapter;
import com.wetime.fanc.home.bean.HomeItemBean;
import com.wetime.fanc.home.bean.TabEntity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.bean.UserCardBean;
import com.wetime.fanc.my.iviews.IGetUserCardView;
import com.wetime.fanc.my.presenter.GetUserCardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserCardActivity extends BaseActivity implements OnLoadMoreListener, IGetUserCardView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tablayout)
    CommonTabLayout commonTabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rcl_home)
    RecyclerView rclHome;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_news)
    ImageView ivNews;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.ll_focus)
    LinearLayout llFocus;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
//    @BindView(R.id.iv_empty)
//    ImageView ivEmpty;
//    @BindView(R.id.tv_empty)
//    TextView tvEmpty;
//    @BindView(R.id.rl_empty)
//    RelativeLayout rlEmpty;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles3 = {"动态", "头条", "点评"};
    private String[] type3 = {"1", "2", "3"};
    private String[] mTitles2 = {"动态", "点评"};
    private String[] type2 = {"1", "3"};

    private GetUserCardPresenter getUserCardPresenter;
    private int page = 1;
    private int index = 0;
    private List<HomeItemBean> list = new ArrayList<>();
    private HomeItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycard);
        ButterKnife.bind(this);
        // 区分几个头部
        if (getIntent().getStringExtra("num").equals("2")) {
            for (String mTitle : mTitles2) {
                mTabEntities.add(new TabEntity(mTitle));
            }
        } else {
            for (String mTitle : mTitles3) {
                mTabEntities.add(new TabEntity(mTitle));
            }
        }
        commonTabLayout.setTabData(mTabEntities);
        index = getIntent().getIntExtra("index", 0);
        commonTabLayout.setCurrentTab(index);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                page = 1;
                index = position;
                getUserCardPresenter.getUserInfo();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(this);

        rclHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rclHome.setFocusableInTouchMode(false);
        adapter = new HomeItemAdapter(list, this);
        rclHome.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getUserCardPresenter = new GetUserCardPresenter(this);
        getUserCardPresenter.getUserInfo();

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

    private Context getContext() {
        return this;
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getUserCardPresenter.getUserInfo();
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onGetUserCard(UserCardBean bean) {
        if (page == 1) {
            list.clear();
            tvName.setText(bean.getData().getUser().getUsername());
            tvTitle.setText(bean.getData().getUser().getUsername());
            Glide.with(this).load(bean.getData().getUser().getAvatar()).into(ivHead);
            tvDes.setText(bean.getData().getUser().getIntro());
            if (!bean.getData().getUser().isIs_new()) {
                ivNews.setVisibility(View.GONE);
            } else {
                ivNews.setVisibility(View.VISIBLE);
            }
            if (!bean.getData().getUser().isOwner()) {
                llFocus.setVisibility(View.VISIBLE);
            }
            if (bean.getData().getUser().isFollow()) {
                tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
                tvFocus.setText("已关注");
                Drawable drawable = getResources().getDrawable(R.drawable.ic_focus_bottom_off);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tvFocus.setCompoundDrawables(drawable, null, null, null);
            } else {
                tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.text_commen));
                tvFocus.setText("加关注");
                Drawable drawable = getResources().getDrawable(R.drawable.ic_focus_bottom_on);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                tvFocus.setCompoundDrawables(drawable, null, null, null);
            }
            tvFocus.setOnClickListener(v -> {
                bean.getData().getUser().setFollow(!bean.getData().getUser().isFollow());

                FocusPresenter focusPresenter = new FocusPresenter();
                focusPresenter.focusUser(getToken(),
                        bean.getData().getUser().isFollow() ? "1" : "0",
                        bean.getData().getUser().getId());
                if (bean.getData().getUser().isFollow()) {
                    tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.text_hint));
                    tvFocus.setText("已关注");
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_focus_bottom_off);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tvFocus.setCompoundDrawables(drawable, null, null, null);
                } else {
                    tvFocus.setTextColor(ContextCompat.getColor(mContext, R.color.text_commen));
                    tvFocus.setText("加关注");
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_focus_bottom_on);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                    tvFocus.setCompoundDrawables(drawable, null, null, null);
                }

            });
            tvFansNum.setText(bean.getData().getUser().getFans_num());
            tvFollowNum.setText(bean.getData().getUser().getFollow_num());

        }
        list.addAll(bean.getData().getList());

        if (list.size() == 0) {
            HomeItemBean emptybean = new HomeItemBean();
            emptybean.setType(1000);
            emptybean.setEmptyType(getType());
            list.add(emptybean);
        }
        adapter.notifyDataSetChanged();
        refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getType() {
        if (getIntent().getStringExtra("num").equals("2")) {
            return type2[index];
        } else {
            return type3[index];
        }
    }

    @Override
    public String getUid() {
        return getIntent().getStringExtra("id");
    }
}
