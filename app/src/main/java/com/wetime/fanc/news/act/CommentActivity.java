package com.wetime.fanc.news.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.news.adapter.CommentListAdapter;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.GalleryCommentBean;
import com.wetime.fanc.news.iviews.IGetAllCommentView;
import com.wetime.fanc.news.presenter.GetAllCommentPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class CommentActivity extends BaseActivity implements IGetAllCommentView, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.comment_listview)
    RecyclerView commentListview;
    @BindView(R.id.comment_refreshLayout)
    SmartRefreshLayout commentRefreshLayout;
    @BindView(R.id.gallery_linear)
    LinearLayout galleryLinear;
    @BindView(R.id.gallery_curr_EditText)
    EditText galleryCurrEditText;
    @BindView(R.id.gallery_curr_LinearLayout)
    LinearLayout galleryCurrLinearLayout;
    private ArrayList<CommentBean.DataBean.ListBean> data = new ArrayList<>();
    private GetAllCommentPresenter getAllCommentPresenter;
    private int pager = 1;
    private CommentListAdapter adapter;
    private boolean isRefresh = true;
    private String galleyId;
    private boolean isShowInput = false;


    public static void startToComment(Context context, String galleyId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("galleyId", galleyId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        init();
        initData();
        initLintsner();
    }

    private void initLintsner() {
        commentRefreshLayout.setOnRefreshListener(this);
        commentRefreshLayout.setOnLoadMoreListener(this);
    }

    private void initData() {
        galleyId = getIntent().getStringExtra("galleyId");
        getAllCommentPresenter = new GetAllCommentPresenter(this);
        getAllCommentPresenter.getAllComment(galleyId, String.valueOf(pager));
    }

    private void init() {
        tvTitle.setText("评论");
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, galleryCurrLinearLayout));
        commentListview.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideInput();
        return super.onTouchEvent(event);
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final LinearLayout contentView) {
        return () -> {
            Rect r = new Rect();
            decorView.getWindowVisibleDisplayFrame(r);

            int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
            int diff = height - r.bottom;

            if (diff > 0) {
                galleryCurrLinearLayout.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layoutParams;
                    lp.setMargins(0, 0, 0, diff);
                    contentView.setLayoutParams(lp);
                }

                galleryCurrEditText.setFocusable(true);
                galleryCurrEditText.setFocusableInTouchMode(true);
                galleryCurrEditText.requestFocus();

            } else {
                galleryCurrLinearLayout.setVisibility(View.GONE);
            }
        }

                ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onGetAllComment(CommentBean bean) {
        if (bean.getError() != 0) {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (isRefresh) {
            data = new ArrayList<>();
            data.addAll(bean.getData().getList());
            commentRefreshLayout.finishRefresh();
        } else {
            data.addAll(bean.getData().getList());
            commentRefreshLayout.finishLoadMore();
        }
        if (pager >= Integer.parseInt(bean.getData().getPaging().getTotal_page())) {
            commentRefreshLayout.setEnableLoadMore(false);
        } else {
            commentRefreshLayout.setEnableLoadMore(true);
        }
        adapter = new CommentListAdapter(this, R.layout.item_comment, data);
        commentListview.setAdapter(adapter);
    }

    @Override
    public void onClickLike(CommentBean bean) {

    }

    @Override
    public void onSendCommont(GalleryCommentBean bean) {
        if (bean.getError() == 0) {
            Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
            hideInput();
            galleryCurrEditText.setText("");
            onRefresh(commentRefreshLayout);
        }
    }

    @Override
    public void onDeleteCommont(ErrorBean bean) {
        if (bean.getError() == 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            onRefresh(commentRefreshLayout);
        }
    }

    public void clickLike(String comment_id, String like) {
        getAllCommentPresenter.clickLike(comment_id, like);
    }

    public void deleteComment(String comment_id) {
        getAllCommentPresenter.deleteCommonet(comment_id);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        isRefresh = true;
        pager = 1;
        getAllCommentPresenter.getAllComment(galleyId, String.valueOf(pager));
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        isRefresh = false;
        pager++;
        getAllCommentPresenter.getAllComment(galleyId, String.valueOf(pager));
    }


    @OnClick({R.id.iv_back, R.id.gallery_curr_TextView, R.id.gallery_linear, R.id.comment_listview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (isShowInput) {
                    hideInput();
                } else {
                    onBackPressed();
                }
                break;
            case R.id.gallery_curr_TextView:
                if (spu.getToken().equals("")) {
                    Intent go1 = new Intent(this, LoginActivity.class);
                    startActivity(go1);
                } else {
                    String s = String.valueOf(galleryCurrEditText.getText());
                    if (s == null || s.isEmpty()) {
                        Toast.makeText(this, "评论不能为空哦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    s = s.replace("\n", " ");
                    getAllCommentPresenter.sendCommonet(galleyId, s);
                }
                break;
            case R.id.gallery_linear:
                isShowInput = true;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.comment_listview:
                hideInput();
                break;
        }
    }

    public void hideInput() {
        if (isShowInput) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(galleryCurrEditText.getWindowToken(), 0);
            isShowInput = false;
        }
    }

}
