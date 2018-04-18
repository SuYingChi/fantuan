package com.wetime.fanc.news.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.baselib.loadmore.AutoLoadMoreAdapter;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.adapter.FcousTitleAdapter;
import com.wetime.fanc.news.adapter.FcousUserAdapter;
import com.wetime.fanc.news.bean.FocusTitleList;
import com.wetime.fanc.news.bean.RecomentFocusUserBean;
import com.wetime.fanc.news.bean.RecomentFocusUserListBean;
import com.wetime.fanc.news.iviews.IGetRecomentFocusUserView;
import com.wetime.fanc.news.iviews.IGetRecomentFocusView;
import com.wetime.fanc.news.presenter.GetRecomentFocusPresenter;
import com.wetime.fanc.news.presenter.GetRecomentFocusUserPresenter;
import com.wetime.fanc.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecomentFocusActivity extends BaseActivity implements IGetRecomentFocusView, IGetRecomentFocusUserView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rcl_title)
    RecyclerView rclTitle;
    @BindView(R.id.rcl_fcous)
    RecyclerView rclFcous;

    private FcousTitleAdapter titleAdapter;
    private GetRecomentFocusUserPresenter getRecomentFocusUserPresenter;
    private FcousUserAdapter userAdapter;
    private List<RecomentFocusUserBean> userList = new ArrayList<>();
    private FocusPresenter focusPresenter = new FocusPresenter();
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;
    private int page = 1;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomment_focus);
        ButterKnife.bind(this);
        tvTitle.setText("推荐关注");
        GetRecomentFocusPresenter getRecomentFocusPresenter = new GetRecomentFocusPresenter(this);
        getRecomentFocusPresenter.getRecomentFocus();
        getRecomentFocusUserPresenter = new GetRecomentFocusUserPresenter(this);
        userAdapter = new FcousUserAdapter(userList, mContext);
        userAdapter.setOnItemClickLitener((view, position) -> {
            Intent go = new Intent(this, UserCardActivity.class);
            go.putExtra("num", "3" );
            go.putExtra("index", 1);
            go.putExtra("id", userList.get(position).getUid());
            startActivity(go);
        });
        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(this, userAdapter);
        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getRecomentFocusUserPresenter.getRecomentFocusUser();
            }
        });
        rclFcous.setLayoutManager(new LinearLayoutManager(this));
        rclFcous.setAdapter(mAutoLoadMoreAdapter);

        userAdapter.setOnFocusClickLitener((view, position) -> {
            if (this.getToken().isEmpty()) {
                Tools.toastInBottom(this, "请先登录");
                Intent goLogin = new Intent(this, LoginActivity.class);
                this.startActivity(goLogin);
                return;
            }
            userList.get(position).setIs_follow(!userList.get(position).isIs_follow());
            mAutoLoadMoreAdapter.notifyDataSetChanged();
            focusPresenter.focusUser(this,getToken(),
                    userList.get(position).isIs_follow() ? "1" : "0",
                    userList.get(position).getUid());
        });
        mAutoLoadMoreAdapter.notifyDataSetChanged();
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
    public void onGetRecomentTitle(FocusTitleList bean) {
        titleAdapter = new FcousTitleAdapter(bean.getData(), this);
        rclTitle.setLayoutManager(new LinearLayoutManager(this));
        rclTitle.setAdapter(titleAdapter);
        if (bean.getData().size() > 0) {
            id = bean.getData().get(0).getId();
            getRecomentFocusUserPresenter.getRecomentFocusUser();
        }
        titleAdapter.setOnItemClickLitener((view, position) -> {
            page = 1;
            titleAdapter.setSelected(position);
            id = bean.getData().get(position).getId();
            getRecomentFocusUserPresenter.getRecomentFocusUser();
        });
    }

    @Override
    public void onGetRecomentUser(RecomentFocusUserListBean bean) {
        if (page == 1) {
            userList.clear();
        }
        userList.addAll(bean.getData().getList());
        if (page > 1) {
            mAutoLoadMoreAdapter.finishLoading();
        }
        if (bean.getData().getPaging().isIs_end()) {
            mAutoLoadMoreAdapter.disable();
        }else {
            mAutoLoadMoreAdapter.enable();
        }

        mAutoLoadMoreAdapter.notifyDataSetChanged();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getId() {
        return id;
    }

}
