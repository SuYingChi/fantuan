package com.wetime.fanc.circle.act;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.adapter.LongDetailAdapter;
import com.wetime.fanc.circle.bean.ActDetailBean;
import com.wetime.fanc.circle.bean.ClickNumBean;
import com.wetime.fanc.circle.bean.LongBean;
import com.wetime.fanc.circle.bean.ReplyCommBean;
import com.wetime.fanc.circle.iviews.ICommentActView;
import com.wetime.fanc.circle.iviews.IDeleteActView;
import com.wetime.fanc.circle.iviews.IDeleteCommentView;
import com.wetime.fanc.circle.iviews.IGetLongDetailView;
import com.wetime.fanc.circle.presenter.CommentActPresenter;
import com.wetime.fanc.circle.presenter.DeleteCommentPresenter;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.circle.presenter.GetLongDetailPresenter;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.main.model.BaseBean;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.DialogUtils;
import com.wetime.fanc.utils.KeyboardChangeListener;
import com.wetime.fanc.utils.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.bottomdialog.BottomDialog;

public class LongDetailActivity extends BaseActivity implements OnLoadMoreListener, KeyboardChangeListener.KeyBoardListener, ICommentActView, IDeleteCommentView, IDeleteActView, IGetLongDetailView, View.OnClickListener, WbShareCallback {


    @BindView(R.id.et_content)
    public EditText etContent;
    public String toId = "";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcl_circle)
    RecyclerView rclCircle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.iv_memu)
    ImageView ivMemu;
    @BindView(R.id.friend_base_head)
    CircleImageView friendBaseHead;
    @BindView(R.id.friend_base_title)
    TextView friendBaseTitle;
    @BindView(R.id.friend_base_linear)
    LinearLayout friendBaseLinear;
    @BindView(R.id.tv_focus)
    ImageView tvFocus;
    @BindView(R.id.friend_image_linear)
    LinearLayout friendImageLinear;
    private GetLongDetailPresenter getActDetailPresenter;
    private int page = 1;
    private LongDetailAdapter actDetailAdapter;
    private LongBean actbean;
    private CommentActPresenter commentActPresenter;
    private BottomDialog mCommentBottomDialog;
    private BottomDialog mDeleteBottomDialog;
    private DeleteCommentPresenter deleteCommentPresenter;
    private PopupWindow pop;
    private String imageurl;
    private String name;
    private String content;
    private String titleUrl;
    private WbShareHandler shareHandler;
    private boolean isComm = false;
    private int pn = 1;

    public static void startToLongDetail(Context context, String longId) {
        Intent intent = new Intent(context, LongDetailActivity.class);
        intent.putExtra("id", longId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSoftInPutMode();
        setContentView(R.layout.activity_longdetail);
        ButterKnife.bind(this);
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        tvTitle.setText("长文详情");
        getActDetailPresenter = new GetLongDetailPresenter(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setEnableRefresh(false);
        rclCircle.setLayoutManager(new LinearLayoutManager(this));
        KeyboardChangeListener mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setKeyBoardListener(this);
        getActDetailPresenter.getLongDetail();
        commentActPresenter = new CommentActPresenter(this);
        deleteCommentPresenter = new DeleteCommentPresenter(this);
        rclCircle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scollYDistance = getScollYDistance();
                if (scollYDistance >= 250) {
                    friendBaseHead.setVisibility(View.VISIBLE);
                    friendBaseTitle.setVisibility(View.VISIBLE);
                    friendBaseLinear.setVisibility(View.VISIBLE);
                    if (actbean.getData().isIs_follow()) {
                    } else {
                        if (actbean.getData().isIs_owner()) {
                        } else {
                            tvFocus.setVisibility(View.VISIBLE);
                        }
                    }
                    friendImageLinear.setVisibility(View.VISIBLE);
                    tvTitle.setVisibility(View.GONE);
                } else {
                    friendBaseHead.setVisibility(View.GONE);
                    friendBaseTitle.setVisibility(View.GONE);
                    friendBaseLinear.setVisibility(View.GONE);
                    tvFocus.setVisibility(View.GONE);
                    friendImageLinear.setVisibility(View.GONE);
                    tvTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public synchronized void getCommReply(String commentId, int position) {
        pn++;
        getActDetailPresenter.getCommReply(commentId, String.valueOf(pn), "10", position);
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rclCircle.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        if (position > 0) {
            return (position) * itemHeight - firstVisiableChildView.getTop() + 250;
        } else {
            return (position) * itemHeight - firstVisiableChildView.getTop();
        }

    }

    @Override
    protected void setSoftInPutMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        if (!isShow) {
            rlBottom.setVisibility(View.GONE);
            etContent.setHint("发表一下高见吧~");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showComment() {
        if (spu.getToken().equals("")) {
            Intent gologin = new Intent(this, LoginActivity.class);
            startActivity(gologin);
        } else {
            toId = "";
            showKeyborad();
        }
    }

    @OnClick({R.id.iv_back, R.id.rl_bottom,R.id.rl_linear_bottom, R.id.tv_send, R.id.iv_memu, R.id.tv_focus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.tv_send:
                if (!TextUtils.isEmpty(spu.getToken())) {
                    if (TextUtils.isEmpty(etContent.getText().toString())) {
                        Tools.toastInBottom(mContext, "请填写评论内容");
                        return;
                    }
                    commentActPresenter.commnetAct();
                } else {
                    Tools.toastInBottom(this, "请先登录");
                    Intent goLogin = new Intent(this, LoginActivity.class);
                    startActivity(goLogin);
                }
                break;
            case R.id.rl_bottom:
                hideKeyboard();
                break;
            case R.id.rl_linear_bottom:
                showComment();
                break;
            case R.id.tv_focus:
                FocusPresenter focusPresenter = new FocusPresenter();
                if (this.getToken().isEmpty()) {
                    Tools.toastInBottom(this, "请先登录");
                    Intent goLogin = new Intent(this, LoginActivity.class);
                    this.startActivity(goLogin);
                    return;
                }
                actbean.getData().setIs_follow(!actbean.getData().isIs_follow());
                focusPresenter.focusUser(this, Tools.getSpu(this).getToken(),
                        actbean.getData().isIs_follow() ? "1" : "0",
                        actbean.getData().getUid());
                if (actbean.getData().isIs_follow()) {
                    tvFocus.setVisibility(View.GONE);
                } else {
                    if (actbean.getData().isIs_owner()) {
                        tvFocus.setVisibility(View.GONE);
                    } else {
                        tvFocus.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.iv_memu:
                if (!TextUtils.isEmpty(spu.getToken())) {
                    if (actbean.getData() == null) return;
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
    public void onGetLongDetail(LongBean bean) {
        if (bean.getError() != 0) {
            onBackPressed();
            return;
        }
        Glide.with(this).load(bean.getData().getAvatar()).into(friendBaseHead);
        friendBaseTitle.setText(bean.getData().getUsername());
        if (bean.getData().isIs_follow()) {
            tvFocus.setVisibility(View.GONE);
        } else {
            if (bean.getData().isIs_owner()) {
                tvFocus.setVisibility(View.GONE);
            } else {
                tvFocus.setVisibility(View.VISIBLE);
            }
        }

        if (page == 1) {
            imageurl = bean.getData().getCoverStr();
            name = bean.getData().getTitle();
            if (TextUtils.isEmpty(bean.getData().getContent())) {
                content = "来自" + bean.getData().getUsername() + "的分享";
            } else {
                content = bean.getData().getContent();
            }

            titleUrl = bean.getData().getArticle_url();
            if (bean.getData().isIs_owner()) {
                ivMemu.setVisibility(View.VISIBLE);
            }
            actbean = bean;
            actDetailAdapter = new LongDetailAdapter(this, actbean);
            rclCircle.setAdapter(actDetailAdapter);
            getActDetailPresenter.getClickNub();
            actDetailAdapter.setOnItemClickLitener((view, position) -> {

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
                            DialogUtils.showNormalDialog(LongDetailActivity.this, null, "是否删除该评论", (dialog, which) -> {
                                deleteCommentPresenter.deleteComment("0", actbean.getData().getComment_list().get(position - 2).getId(), "违反圈子规章");
                                actbean.getData().getComment_list().remove(position - 2);
                                actbean.getData().setComment_num(String.valueOf(Integer.parseInt(actbean.getData().getComment_num()) - 1));

                                actDetailAdapter.notifyItemChanged(1);
                                actDetailAdapter.notifyItemRemoved(position);
                                actDetailAdapter.notifyItemRangeChanged(2,
                                        actbean.getData().getComment_list().size());
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

        actDetailAdapter.notifyDataSetChanged();
        if (bean.getData().getComment_list().size() == 0) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(!bean.getData().getPaging().isIs_end());
        }

        refreshLayout.finishLoadMore();

        if (isComm) {
            rclCircle.scrollToPosition(actDetailAdapter.getItemCount() - 2);
            isComm = false;
        }


    }

    public void clickLike(boolean b) {
        getActDetailPresenter.getClickLike("0", b);
    }

    @Override
    public void onGeClickNumber(ClickNumBean bean) {
        if (bean.getError() != 0) {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        actDetailAdapter.setLikeNumber(bean);
    }

    @Override
    public void onGeClickLike(ErrorBean bean, boolean like) {
        if (bean.getError() == 0) getActDetailPresenter.getClickNub();
    }

    @Override
    public void onGetReply(ReplyCommBean bean, int position) {
        if (bean.getError() != 0) {
            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        actDetailAdapter.setRecAdapter(bean, position);
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
        getActDetailPresenter.getLongDetail();
    }

    protected void initStateBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white_lib)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .keyboardEnable(true)
                .init();
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
        getActDetailPresenter.getLongDetail();
        isComm = true;
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
        if (b1) {
            mDeleteBottomDialog.setLayoutRes(R.layout.item_delete_act);
        } else {
            mDeleteBottomDialog.setLayoutRes(R.layout.item_delete_share);
        }
        mDeleteBottomDialog.setViewListener(v -> {

            v.findViewById(R.id.tv_delete).setOnClickListener(v12 -> {
                mDeleteBottomDialog.dismiss();
//                DeleteActPresenter deleteActPresenter = new DeleteActPresenter(this);
//                deleteActPresenter.deleteAct();

                if (b) {
                    DialogUtils.showNormalDialog(LongDetailActivity.this, null, b1 ? "是否删除该评论" : "是否删除该长文", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (b1) {
                                deleteCommentPresenter.deleteComment("0", actbean.getData().getComment_list().get(position - 2).getId(), "违反圈子规章");
                                actbean.getData().getComment_list().remove(position - 2);
                                actbean.getData().setComment_num(String.valueOf(Integer.parseInt(actbean.getData().getComment_num()) - 1));

                                actDetailAdapter.notifyItemChanged(1);
                                actDetailAdapter.notifyItemRemoved(position);
                                actDetailAdapter.notifyItemRangeChanged(2, actbean.getData().getComment_list().size());
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
                mDeleteBottomDialog.setLayoutRes(R.layout.bottom_delete_dialog_layout);
                mDeleteBottomDialog.setViewListener(v11 -> {
                    ((TextView) v11.findViewById(R.id.tv_item1)).setText("请选择删除原因");
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
                    v11.findViewById(R.id.tv_item7).setOnClickListener(v5 -> {
                        mDeleteBottomDialog.dismiss();

                    });
                });

                mDeleteBottomDialog.show();


            });
            v.findViewById(R.id.tv_cancel).setOnClickListener(v14 -> {
                mDeleteBottomDialog.dismiss();

            });
            if (b1) {
            } else {
                v.findViewById(R.id.tv_share).setOnClickListener(v14 -> {
                    mDeleteBottomDialog.dismiss();
                    showPop();
                });
            }

        });

        mDeleteBottomDialog.show();
    }

    private void initPopListener(View shareView) {
        shareView.findViewById(R.id.ll_share_wx).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wxq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_wb).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qq).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_qqkj).setOnClickListener(this);
        shareView.findViewById(R.id.ll_share_copy).setOnClickListener(this);
        shareView.findViewById(R.id.pop_cancel).setOnClickListener(this);
    }

    public void showPop() {
        View shareView = LayoutInflater.from(this).inflate(R.layout.view_popupwindow, null);

        initPopListener(shareView);

        // 创建PopupWindow对象
        pop = new PopupWindow(shareView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new ColorDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.5f;
        this.getWindow().setAttributes(lp);

        pop.setOnDismissListener(() -> {
            // popupWindow隐藏时恢复屏幕正常透明度
            WindowManager.LayoutParams lp1 = this.getWindow().getAttributes();
            lp1.alpha = 1.0f;
            this.getWindow().setAttributes(lp1);
        });

        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void showReportAct() {
        BottomDialog mDeleteBottomDialogShare = BottomDialog.create(getSupportFragmentManager());
        mDeleteBottomDialogShare.setDimAmount(0.5f);
        mDeleteBottomDialogShare.setCancelOutside(true);
        mDeleteBottomDialogShare.setLayoutRes(R.layout.bottom_report_share_layout);
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
                    v11.findViewById(R.id.tv_item7).setOnClickListener(v5 -> {
                        mDeleteBottomDialog.dismiss();
                    });
                });

                mDeleteBottomDialog.show();

            });
            v.findViewById(R.id.tv_item2).setOnClickListener(v14 -> {
                mDeleteBottomDialogShare.dismiss();
            });
            v.findViewById(R.id.tv_share).setOnClickListener(v14 -> {
                mDeleteBottomDialogShare.dismiss();
                showPop();
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

    public Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_share_wx:
                Glide.with(this).load(imageurl).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWx(LongDetailActivity.this, drawableToBitmap(resource), titleUrl, SendMessageToWX.Req.WXSceneSession, name, content, actbean.getData().getCover().size());
                    }
                });


                break;
            case R.id.ll_share_wxq:
                Glide.with(this).load(imageurl).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWx(LongDetailActivity.this, drawableToBitmap(resource), titleUrl, SendMessageToWX.Req.WXSceneTimeline, name, content, actbean.getData().getCover().size());
                    }
                });


                break;
            case R.id.ll_share_wb:
                Glide.with(this).load(imageurl).into(new SimpleTarget<Drawable>() {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Tools.shareWb(LongDetailActivity.this, shareHandler, drawableToBitmap(resource), titleUrl, "分享来自范团APP的《" + name + "》", "分享来自范团APP的《" + content + "》");
                    }
                });


                break;
            case R.id.ll_share_qq:

                Tools.shareQQ(this, titleUrl, actbean.getData().getCoverStr(), name, content, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(LongDetailActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(LongDetailActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LongDetailActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.ll_share_qqkj:

                Tools.shareToQzone(this, titleUrl, actbean.getData().getCoverStr(), name, content, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(LongDetailActivity.this, "分享成功!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(LongDetailActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LongDetailActivity.this, "分享取消!", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.ll_share_copy:
                ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                if (cm != null) {
                    cm.setPrimaryClip(ClipData.newPlainText("text", titleUrl));
                    Tools.toastInBottom(LongDetailActivity.this, "复制成功");
                } else {
                    Tools.toastInBottom(LongDetailActivity.this, "复制失败");
                }

                break;
            case R.id.pop_cancel:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
        }
    }


    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
        hidePop();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
        hidePop();
    }


    @Override
    public void onWbShareFail() {
        Toast.makeText(this, getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ", Toast.LENGTH_LONG).show();
        hidePop();
    }

    public void hidePop() {
        if (pop.isShowing()) {
            pop.dismiss();
        }
    }

}
