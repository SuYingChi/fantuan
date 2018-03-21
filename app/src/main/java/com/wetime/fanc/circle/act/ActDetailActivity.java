package com.wetime.fanc.circle.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.ActDetailAdapter;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.iviews.ICommentActView;
import com.wetime.fanc.circle.iviews.IDeleteActView;
import com.wetime.fanc.circle.iviews.IDeleteCommentView;
import com.wetime.fanc.circle.iviews.IGetActDetailView;
import com.wetime.fanc.circle.presenter.CommentActPresenter;
import com.wetime.fanc.circle.presenter.DeleteActPresenter;
import com.wetime.fanc.circle.presenter.DeleteCommentPresenter;
import com.wetime.fanc.circle.presenter.GetActDetailPresenter;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.KeyboardChangeListener;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;

public class ActDetailActivity extends BaseActivity implements IGetActDetailView, OnLoadMoreListener, KeyboardChangeListener.KeyBoardListener, ICommentActView, IDeleteCommentView, IDeleteActView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_gocomment)
    TextView tvGocomment;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.iv_memu)
    ImageView ivMemu;


    private GetActDetailPresenter getActDetailPresenter;
    private int page = 1;
    private ActDetailAdapter actDetailAdapter;
    private ActDetailBean actbean;
    private CommentActPresenter commentActPresenter;
    private String toId = "";
    private BottomDialog mCommentBottomDialog;
    private BottomDialog mDeleteBottomDialog;
    private DeleteCommentPresenter deleteCommentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSoftInPutMode();
        setContentView(R.layout.activity_actdetail);
        ButterKnife.bind(this);
        tvTitle.setText("动态详情");
        getActDetailPresenter = new GetActDetailPresenter(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setEnableRefresh(false);
        rclCircle.setLayoutManager(new LinearLayoutManager(this));
        rclCircle.setFocusableInTouchMode(false);
        KeyboardChangeListener mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        getActDetailPresenter.getActDetail();
        commentActPresenter = new CommentActPresenter(this);
        deleteCommentPresenter = new DeleteCommentPresenter(this);

    }

    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        if (!isShow) {
            rlBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.tv_gocomment, R.id.tv_zan, R.id.rl_bottom, R.id.tv_send, R.id.iv_memu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_gocomment:
                if (spu.getToken().equals("")) {
                    Intent gologin = new Intent(this, LoginActivity.class);
                    startActivity(gologin);
                } else {
                    showKeyborad();
                }
                break;
            case R.id.tv_zan:
                if (spu.getToken().equals("")) {
                    Intent gologin = new Intent(this, LoginActivity.class);
                    startActivity(gologin);
                } else {
                    ZanActPresenter presenter = new ZanActPresenter();
                    if (actbean.getData().isHas_like()) {
                        Drawable drawable = getResources().getDrawable(R.drawable.ic_homeitem_zan_off_off);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                        tvZan.setCompoundDrawables(drawable, null, null, null);
                        presenter.zanAct(actbean.getData().getId(), Tools.getSpu(mContext).getToken(), "0");

                        int num = Integer.valueOf(tvZan.getText().toString()) - 1;
                        tvZan.setText(String.format("%d", num));
                        actbean.getData().setLike_num(String.format("%d", num));
                        for (int i = 0; i < actbean.getData().getLike_list().size(); i++) {
                            if (TextUtils.equals(actbean.getData().getLike_list().get(i).getUid(), actbean.getData().getCurrent_uid())) {
                                actbean.getData().getLike_list().remove(i);
                            }

                        }
                        actDetailAdapter.notifyItemChanged(1);
                        actbean.getData().setHas_like(false);
                    } else {
                        Drawable drawable = getResources().getDrawable(R.drawable.ic_homeitem_zan_off_on);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                        tvZan.setCompoundDrawables(drawable, null, null, null);
                        presenter.zanAct(actbean.getData().getId(), Tools.getSpu(mContext).getToken(), "1");

                        int num = Integer.valueOf(tvZan.getText().toString()) + 1;
                        tvZan.setText(String.format("%d", num));
                        actbean.getData().setLike_num(String.format("%d", num));
                        actDetailAdapter.notifyItemChanged(1);
                        ActDetailBean.DataBean.LikeListBean b = new ActDetailBean.DataBean.LikeListBean();
                        b.setAvatar(actbean.getData().getCurrent_avatar());
                        b.setUid(actbean.getData().getCurrent_uid());
                        actbean.getData().getLike_list().add(0, b);
                        actDetailAdapter.notifyItemChanged(1);
                        actbean.getData().setHas_like(true);
                    }

                }
                break;
            case R.id.tv_send:
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    Tools.toastInBottom(mContext, "请填写评论内容");
                    return;
                }
                commentActPresenter.commnetAct();
                break;
            case R.id.rl_bottom:
                hideKeyboard();
                break;
            case R.id.iv_memu:
                showDeleteAct();
                break;
        }

    }

    @Override
    public void onGetActDetail(ActDetailBean bean) {
        if (bean.getError() != 0) {
            onBackPressed();
            return;
        }


        if (page == 1) {
            if (bean.getData().isIs_owner()) {
                ivMemu.setVisibility(View.VISIBLE);
            }
            actbean = bean;
            actDetailAdapter = new ActDetailAdapter(this, actbean);
            rclCircle.setAdapter(actDetailAdapter);
            actDetailAdapter.setOnItemClickLitener((view, position) -> {
                ActDetailBean.DataBean.CommentListBean b = actbean.getData().getComment_list().get(position - 2);
                if (b.isIs_owner()) {
                    mCommentBottomDialog = BottomDialog.create(getSupportFragmentManager());
                    mCommentBottomDialog.setDimAmount(0.5f);
                    mCommentBottomDialog.setLayoutRes(R.layout.item_delete_comment);
                    mCommentBottomDialog.setViewListener(v -> {
                        v.findViewById(R.id.tv_reply).setOnClickListener(v1 -> {
                            mCommentBottomDialog.dismiss();
                            new Handler().postDelayed(() -> {
                                toId = b.getUid();
                                etContent.setHint("回复 " + b.getUsername());
                                showKeyborad();
                            }, 500);

                        });
                        v.findViewById(R.id.tv_delete).setOnClickListener(v12 -> {
                            mCommentBottomDialog.dismiss();
                            deleteCommentPresenter.deleteComment(b.getId());
                            actbean.getData().getComment_list().remove(position - 2);
                            actbean.getData().setComment_num(actbean.getData().getComment_num() - 1);

                            actDetailAdapter.notifyItemChanged(1);
                            actDetailAdapter.notifyItemRemoved(position);
                            actDetailAdapter.notifyItemRangeChanged(2,
                                    actbean.getData().getComment_list().size());
//                                actDetailAdapter.notifyDataSetChanged();


                        });
                        v.findViewById(R.id.tv_cancel).setOnClickListener(v14 -> {
                            mCommentBottomDialog.dismiss();
                            toId = "";
                            etContent.setHint("评论动态");
                        });
                    });

                    mCommentBottomDialog.show();
                } else {
                    toId = b.getUid();
                    etContent.setHint("回复 " + b.getUsername());
                    showKeyborad();
                }


            });
        } else {
            actbean.getData().getComment_list().addAll(bean.getData().getComment_list());
        }
        tvZan.setText(bean.getData().getLike_num());
        if (actbean.getData().isHas_like()) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_homeitem_zan_off_on);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
            tvZan.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_homeitem_zan_off_off);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
            tvZan.setCompoundDrawables(drawable, null, null, null);
        }
        actDetailAdapter.notifyDataSetChanged();
        refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        refreshLayout.finishLoadMore();

    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        page++;
        getActDetailPresenter.getActDetail();
    }

    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .init();
    }

    private void showKeyborad() {
        rlBottom.setVisibility(View.VISIBLE);
        etContent.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etContent, InputMethodManager.SHOW_FORCED);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etContent.getWindowToken(), 0); //强制隐藏键盘
        rlBottom.setVisibility(View.GONE);
    }

    @Override
    public void onCommentAct(BaseBean bean) {
        Tools.toastInBottom(this, "评论成功");
        etContent.setText("");
        hideKeyboard();
        getActDetailPresenter.getActDetail();
    }

    @Override
    public void onDeleteActtResult(BaseBean bean) {
        onBackPressed();
    }

    @Override
    public String getDyId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public String getToUid() {
        return toId;
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }

    @Override
    public void onDeleteResult(BaseBean bean) {
        Tools.toastInBottom(this, "删除成功");

    }

    private void showDeleteAct() {
        mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialog.setDimAmount(0.5f);
        mDeleteBottomDialog.setLayoutRes(R.layout.item_delete_act);
        mDeleteBottomDialog.setViewListener(v -> {

            v.findViewById(R.id.tv_delete).setOnClickListener(v12 -> {
                mDeleteBottomDialog.dismiss();
                DeleteActPresenter deleteActPresenter = new DeleteActPresenter(this);
                deleteActPresenter.deleteAct();

            });
            v.findViewById(R.id.tv_cancel).setOnClickListener(v14 -> {
                mDeleteBottomDialog.dismiss();

            });
        });

        mDeleteBottomDialog.show();
    }
}
