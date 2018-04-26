package com.wetime.fanc.circle.act;

import android.content.Context;
import android.content.DialogInterface;
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
import com.wetime.fanc.circle.presenter.DeleteCommentPresenter;
import com.wetime.fanc.circle.presenter.GetActDetailPresenter;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.utils.DialogUtils;
import com.wetime.fanc.utils.KeyboardChangeListener;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;

public class ActDetailActivity extends BaseActivity implements IGetActDetailView, OnLoadMoreListener, KeyboardChangeListener.KeyBoardListener, ICommentActView, IDeleteCommentView, IDeleteActView {


    @BindView(R.id.et_content)
    public
    EditText etContent;
    public String toId = "";
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
        getActDetailPresenter.getActDetail(false);
        commentActPresenter = new CommentActPresenter(this);
        deleteCommentPresenter = new DeleteCommentPresenter(this);
    }

    //    @Override
//    protected void setSoftInPutMode() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//    }
    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        if (!isShow) {
            rlBottom.setVisibility(View.GONE);
            etContent.setHint("评论动态");
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
                    toId = "";
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
                if (spu.getToken() != null) {
                    if (actbean.getData().isIs_delete()) {
                        showDeleteAct(actbean.getData().isIs_owner(), false);
                    } else {
                        showReportAct();
                    }
                } else {
                    Tools.toastInBottom(this, "请先登录");
                    Intent goLogin = new Intent(this, LoginActivity.class);
                    startActivity(goLogin);
                }
                break;

        }

    }

    @Override
    public void onGetActDetail(ActDetailBean bean, boolean isComment) {
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
                if(TextUtils.isEmpty(spu.getToken())){
                    Intent gologin = new Intent(this, LoginActivity.class);
                    startActivity(gologin);
                    return;
                }

                if (view.getId() == R.id.iv_delete) {
                    showDeleteAct(true, true, position);
                    return;
                }

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
                            DialogUtils.showNormalDialog(ActDetailActivity.this, null, "是否删除该评论", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteCommentPresenter.deleteComment("0", actbean.getData().getComment_list().get(position - 2).getId(), "违反圈子规章");
                                    actbean.getData().getComment_list().remove(position - 2);
                                    actbean.getData().setComment_num(actbean.getData().getComment_num() - 1);

                                    actDetailAdapter.notifyItemChanged(1);
                                    actDetailAdapter.notifyItemRemoved(position);
                                    actDetailAdapter.notifyItemRangeChanged(2,
                                            actbean.getData().getComment_list().size());
                                }
                            });
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
//        if(rclCircle.getAdapter()
        if (isComment)
            rclCircle.scrollToPosition(2);

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
        getActDetailPresenter.getActDetail(false);
    }

    protected void initStateBar() {
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.white_lib)
//                .statusBarDarkFont(true, 0.2f)
//                .fitsSystemWindows(true)
//                .keyboardEnable(true)
//                .init();
    }

    public void showKeyborad() {
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
        getActDetailPresenter.getActDetail(true);
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
    public void onDeleteResult(BaseBean bean, String type) {
        if (!type.equals("0")) {
            this.finish();
        }
//        Toast.makeText(mContext, "删除成功!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReportResult(BaseBean bean) {
        // Tools.toastInBottom(this, "举报成功");
//        this.finish();
    }

    private void showDeleteAct(boolean b, boolean b1) {
        showDeleteAct(b, b1, -1);
    }

    private void showDeleteAct(boolean b, boolean b1, int position) {
        mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialog.setDimAmount(0.5f);
        mDeleteBottomDialog.setLayoutRes(R.layout.item_delete_act);
        mDeleteBottomDialog.setViewListener(v -> {

            v.findViewById(R.id.tv_delete).setOnClickListener(v12 -> {
                mDeleteBottomDialog.dismiss();
//                DeleteActPresenter deleteActPresenter = new DeleteActPresenter(this);
//                deleteActPresenter.deleteAct();

                if (b) {
                    DialogUtils.showNormalDialog(ActDetailActivity.this, null, b1 ? "是否删除该评论" : "是否删除该动态", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (b1) {
                                deleteCommentPresenter.deleteComment("0", actbean.getData().getComment_list().get(position - 2).getId(), "违反圈子规章");
                            } else {
                                deleteCommentPresenter.deleteComment("1", actbean.getData().getId(), "欺诈骗钱");
                            }

                        }
                    });
                    return;
                }

                BottomDialog mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
                mDeleteBottomDialog.setDimAmount(0.5f);
                mDeleteBottomDialog.setCancelOutside(true);
                mDeleteBottomDialog.setLayoutRes(R.layout.bottom_anim_dialog_layout);
                mDeleteBottomDialog.setViewListener(v11 -> {
                    v11.findViewById(R.id.tv_item2).setOnClickListener(v1 -> {
                        delete(mDeleteBottomDialog, "1", v1.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item3).setOnClickListener(v2 -> {
                        delete(mDeleteBottomDialog, "1", v2.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item4).setOnClickListener(v3 -> {
                        delete(mDeleteBottomDialog, "1", v3.getId(), v11);

                    });
                    v11.findViewById(R.id.tv_item5).setOnClickListener(v4 -> {
                        delete(mDeleteBottomDialog, "1", v4.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item6).setOnClickListener(v5 -> {
                        delete(mDeleteBottomDialog, "1", v5.getId(), v11);

                    });
                    v11.findViewById(R.id.tv_item7).setOnClickListener(v6 -> {
                        delete(mDeleteBottomDialog, "1", v6.getId(), v11);
                    });
                });

                mDeleteBottomDialog.show();


            });
            v.findViewById(R.id.tv_cancel).setOnClickListener(v14 -> {
                mDeleteBottomDialog.dismiss();

            });
        });

        mDeleteBottomDialog.show();
    }

    private void showReportAct() {
        BottomDialog mDeleteBottomDialogShare = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialogShare.setDimAmount(0.5f);
        mDeleteBottomDialogShare.setCancelOutside(true);
        mDeleteBottomDialogShare.setLayoutRes(R.layout.bottom_report_dialog_layout);
        mDeleteBottomDialogShare.setViewListener(v -> {

            v.findViewById(R.id.tv_item1).setOnClickListener(v12 -> {
                mDeleteBottomDialogShare.dismiss();
                BottomDialog mDeleteBottomDialog = BottomDialog.create(getSupportFragmentManager());
                mDeleteBottomDialog.setDimAmount(0.5f);
                mDeleteBottomDialog.setCancelOutside(true);
                mDeleteBottomDialog.setLayoutRes(R.layout.bottom_delete_dialog_layout);
                mDeleteBottomDialog.setViewListener(v11 -> {
                    v11.findViewById(R.id.tv_item2).setOnClickListener(v1 -> {
                        report(mDeleteBottomDialog, v1.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item3).setOnClickListener(v2 -> {
                        report(mDeleteBottomDialog, v2.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item4).setOnClickListener(v3 -> {
                        report(mDeleteBottomDialog, v3.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item5).setOnClickListener(v4 -> {
                        report(mDeleteBottomDialog, v4.getId(), v11);
                    });
                    v11.findViewById(R.id.tv_item6).setOnClickListener(v5 -> {
                        report(mDeleteBottomDialog, v5.getId(), v11);
                    });
                });

                mDeleteBottomDialog.show();

            });
            v.findViewById(R.id.tv_item2).setOnClickListener(v14 -> {
                mDeleteBottomDialogShare.dismiss();
            });
        });

        mDeleteBottomDialogShare.show();
    }

    private void report(BottomDialog mDeleteBottomDialog, int id, View v11) {
        deleteCommentPresenter.reportComment("1",
                actbean.getData().getId(),
                String.valueOf(((TextView) v11.findViewById(id)).getText()));
        mDeleteBottomDialog.dismiss();
    }

    private void delete(BottomDialog mDeleteBottomDialog, String type, int id, View v11) {
        deleteCommentPresenter.deleteComment(type,
                actbean.getData().getId(),
                String.valueOf(((TextView) v11.findViewById(id)).getText()));
        mDeleteBottomDialog.dismiss();
    }

}
