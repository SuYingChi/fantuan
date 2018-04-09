package com.wetime.fanc.news.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.photoview.MyViewPager;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.my.bean.AttentionBean;
import com.wetime.fanc.news.adapter.TabHomeAdapter;
import com.wetime.fanc.news.bean.GalleryItemBean;
import com.wetime.fanc.news.frag.GalleryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description:  图集
 */
public class GalleryActivity extends BaseActivity implements View.OnClickListener,
        MyViewPager.OnNeedScrollListener, GalleryFragment.OnPhotoTapListener, WbShareCallback {

    @BindView(R.id.viewpager_photos)
    MyViewPager viewPager;
    @BindView(R.id.back_layout)
    View backLl;
    @BindView(R.id.iv_back)
    ImageView mBackBtn;
    @BindView(R.id.activity_gallery_root)
    View mRootView;
    @BindView(R.id.friend_base_head)
    CircleImageView friendBaseHead;
    @BindView(R.id.friend_base_title)
    TextView friendBaseTitle;
    @BindView(R.id.friend_base_text)
    TextView friendBaseText;
    @BindView(R.id.friend_base_TextView)
    TextView friendBaseTextView;
    @BindView(R.id.friend_base_LinearLayout)
    LinearLayout friendBaseLinearLayout;
    @BindView(R.id.activity_net_error_stub)
    ViewStub activityNetErrorStub;

    private List<Fragment> fragments;
    private TabHomeAdapter adapter;
    private GalleryItemBean gallery;
    private boolean isHideView;
    private Fragment mCurrFragment;
    private GalleryItemBean bean;

    public static void startToGallery(Context context, String galleryId) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra("gallery", galleryId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
//        ImmersionBar.with(this).statusBarColor(R.color._1b1b1b).navigationBarColor(R.color._1b1b1b).navigationBarAlpha(1f).fullScreen(true).statusBarDarkFont(true, 1f).fitsSystemWindows(true).init();
//        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#1b1b1b"), false);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initStateBar() {
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).init();
    }

    private void initView() {
        mBackBtn.setOnClickListener(this);
        friendBaseLinearLayout.setOnClickListener(this);
        friendBaseHead.setOnClickListener(this);
        friendBaseTitle.setOnClickListener(this);
        friendBaseText.setOnClickListener(this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnNeedScrollListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (fragments != null) {
                    mCurrFragment = fragments.get(position);
                    switchTitle(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initData() {
        refresh();
    }

    private void refresh() {
        createFragmentList();
        updateViewPager();
    }

    private void createFragmentList() {
        fragments = new ArrayList<>();
        Bundle bundlePhotos = new Bundle();
        bundlePhotos.putString("galleryId", getIntent().getStringExtra("gallery"));
        GalleryFragment galleryFragment = GalleryFragment.newInstance(bundlePhotos);
        galleryFragment.setOnPhotoTapListener(this);
        fragments.add(galleryFragment);
        mCurrFragment = fragments.get(0);
    }

    private void updateViewPager() {
        adapter = new TabHomeAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    private void switchTitle(int position) {
        switch (position) {
            case 0:
                if (isHideView) {
                    //若离开图集时，是隐藏状态，回去时还是隐藏状态
                    onDismissView();
                }
                break;
            case 1:
                if (isHideView) {
                    //在图集隐藏了上方的view时，到图集推荐时需要显示出来
                    backLl.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (((GalleryFragment) mCurrFragment).isShowInput) {
                    ((GalleryFragment) mCurrFragment).hideInput();
                } else {
                    finish();
                }
                break;
            case R.id.friend_base_head:
            case R.id.friend_base_title:
            case R.id.friend_base_text:
                Intent go = new Intent(this, UserCardActivity.class);
                go.putExtra("num", "3");
                go.putExtra("index", 0);
                go.putExtra("id", bean.getData().getUid());
                this.startActivity(go);
                break;
            case R.id.friend_base_LinearLayout:
                if (spu.getToken().equals("")) {
                    Intent go1 = new Intent(this, LoginActivity.class);
                    startActivity(go1);
                } else {
                    ((GalleryFragment) mCurrFragment).AttentionFriends();
                }

                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((GalleryFragment) mCurrFragment).onNewIntent(intent);
    }

    public void drawingView(GalleryItemBean bean) {
        this.bean = bean;
        Glide.with(this).load(bean.getData().getAvatar()).into(friendBaseHead);
        friendBaseTitle.setText(bean.getData().getNews_name());
        friendBaseText.setText(bean.getData().getFollower_num() + "粉丝");
        if (bean.getData().isIs_following()) {
            friendBaseLinearLayout.setBackground(getResources().getDrawable(R.drawable.icon_attention));
            friendBaseTextView.setText("已关注");
            friendBaseTextView.setVisibility(View.GONE);
        } else {
            friendBaseLinearLayout.setBackground(getResources().getDrawable(R.drawable.bg_circle_gallery));
            friendBaseTextView.setText("关注");
            friendBaseTextView.setVisibility(View.VISIBLE);
        }
    }

    public String getTextString() {
        return String.valueOf(friendBaseTextView.getText());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean needScroll() {

        return true;
    }

    @Override
    public void onShowView() {
        isHideView = false;
        backLl.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDismissView() {
        isHideView = true;
        backLl.setVisibility(View.INVISIBLE);
    }

    public void drawingAttring(AttentionBean bean) {
        if (String.valueOf(friendBaseTextView.getText()).equals("关注")) {
            friendBaseLinearLayout.setBackground(getResources().getDrawable(R.drawable.icon_attention));
            friendBaseTextView.setText("已关注");
            friendBaseTextView.setVisibility(View.GONE);
        } else {
            friendBaseLinearLayout.setBackground(getResources().getDrawable(R.drawable.bg_circle_gallery));
            friendBaseTextView.setText("关注");
            friendBaseTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
        ((GalleryFragment) mCurrFragment).hidePop();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
        ((GalleryFragment) mCurrFragment).hidePop();
    }


    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ", Toast.LENGTH_LONG).show();
        ((GalleryFragment) mCurrFragment).hidePop();
    }
}
