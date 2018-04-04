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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.GoodView;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.adapter.ReplyAdapter;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.ReplyCommentBean;
import com.wetime.fanc.news.iviews.IGetCommentReplyView;
import com.wetime.fanc.news.presenter.GetCommentReplyPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReplyActivity extends BaseActivity implements OnRefreshListener, IGetCommentReplyView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.reply_head)
    CircleImageView replyHead;
    @BindView(R.id.reply_name)
    TextView replyName;
    @BindView(R.id.reply_content)
    TextView replyContent;
    @BindView(R.id.reply_time)
    TextView replyTime;
    @BindView(R.id.reply_good)
    TextView replyGood;
    @BindView(R.id.reply_image)
    ImageView replyImage;
    @BindView(R.id.reply_listview)
    RecyclerView replyListview;
    @BindView(R.id.reply_refreshLayout)
    SmartRefreshLayout replyRefreshLayout;
    @BindView(R.id.gallery_curr_EditText)
    EditText galleryCurrEditText;
    @BindView(R.id.gallery_curr_LinearLayout)
    LinearLayout galleryCurrLinearLayout;
    @BindView(R.id.gallery_linear)
    LinearLayout galleryLinear;
    private boolean isShowInput = false;
    private CommentBean.DataBean.ListBean commentTestBean = null;
    private GoodView mGoodView;
    private ArrayList<ReplyCommentBean.DataBean.ReplyBean> data = new ArrayList<>();
    private ReplyAdapter adapter;
    private GetCommentReplyPresenter getCommentReplyPresenter;
    private String pid;
    private String commentId;

    public static void startToReplyActivity(Context context, CommentBean.DataBean.ListBean commentTestBean) {
        Intent intent = new Intent(context, ReplyActivity.class);
        intent.putExtra("commentTestBean", commentTestBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ButterKnife.bind(this);
        init();
        initData();
        initLintsner();
    }

    private void initLintsner() {
        replyRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        commentTestBean = (CommentBean.DataBean.ListBean) getIntent().getSerializableExtra("commentTestBean");
        sendReply("0", commentTestBean.getId(), commentTestBean.getUser().getUsername());
        tvTitle.setText(commentTestBean.getReply_num() + "条回复");
        replyName.setText(commentTestBean.getUser().getUsername());
        replyContent.setText(commentTestBean.getContent());
        replyGood.setText(commentTestBean.getLike_num());
        replyTime.setText(commentTestBean.getTime());
        Glide.with(this).load(commentTestBean.getUser().getAvatar()).into(replyHead);
        if (commentTestBean.isIs_like()) {
            replyImage.setImageResource(R.drawable.good_checked);
        } else {
            replyImage.setImageResource(R.drawable.good);
        }
        getCommentReplyPresenter = new GetCommentReplyPresenter(this);
        getCommentReplyPresenter.getCommentReply(commentTestBean.getId());
    }

    private void init() {
        View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(getGlobalLayoutListener(decorView, galleryCurrLinearLayout));
        replyListview.setLayoutManager(new LinearLayoutManager(this));
        replyRefreshLayout.setEnableLoadMore(false);
        mGoodView = new GoodView(this);
    }

    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final LinearLayout contentView) {
        return () -> {
            Rect r = new Rect();
            decorView.getWindowVisibleDisplayFrame(r);

            int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
            int diff = height - r.bottom;

            if (diff > 0) {
                galleryCurrLinearLayout.setVisibility(View.VISIBLE);
                galleryLinear.setVisibility(View.GONE);
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
                galleryLinear.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideInput();
        return super.onTouchEvent(event);
    }

    @OnClick({R.id.iv_back, R.id.reply_linear,R.id.reply_head, R.id.gallery_linear, R.id.gallery_curr_TextView, R.id.reply_all_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (isShowInput) {
                    hideInput();
                } else {
                    onBackPressed();
                }
                break;
            case R.id.reply_linear:
                if (mGoodView.isShowing()) return;
                if (commentTestBean.isIs_like()) {
                    mGoodView.setText("-1");
                    mGoodView.show(view);
                    commentTestBean.setIs_like(false);
                    replyImage.setImageResource(R.drawable.good);
                    int i = Integer.parseInt(replyGood.getText().toString());
                    replyGood.setText(String.valueOf(i - 1));
                    clickLike(commentTestBean.getId(), "0");
                } else {
                    mGoodView.setText("+1");
                    mGoodView.show(view);
                    commentTestBean.setIs_like(true);
                    replyImage.setImageResource(R.drawable.good_checked);
                    int i = Integer.parseInt(replyGood.getText().toString());
                    replyGood.setText(String.valueOf(i + 1));
                    clickLike(commentTestBean.getId(), "1");
                }
                break;
            case R.id.gallery_linear:
                isShowInput = true;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.reply_head:
                Intent go = new Intent(this, UserCardActivity.class);
                go.putExtra("num", "3");
                go.putExtra("index", 0);
                go.putExtra("id", commentTestBean.getUser().getId());
                startActivity(go);
                break;
            case R.id.reply_all_comment:
                sendReply("0", commentTestBean.getId(), commentTestBean.getUser().getUsername());
                break;
            case R.id.gallery_curr_TextView:
                String s = String.valueOf(galleryCurrEditText.getText());
                if (s.equals("null")) {
                    Toast.makeText(this, "评论不能为空哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                getCommentReplyPresenter.sendCommentReply(pid, commentId, s);
                break;
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getCommentReplyPresenter.getCommentReply(commentTestBean.getId());
    }

    public void hideInput() {
        if (isShowInput) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(galleryCurrEditText.getWindowToken(), 0);
            isShowInput = false;
        }
    }

    @Override
    public void onGetCommentReply(ReplyCommentBean bean) {
        data = new ArrayList<>();
        data.addAll(bean.getData().getReply());
        replyRefreshLayout.finishRefresh();
        adapter = new ReplyAdapter(this, R.layout.item_reply, data);
        replyListview.setAdapter(adapter);
    }

    @Override
    public void onSendCommentReply(String bean) {
        if (bean.equals("0")) {
            Toast.makeText(mContext, "评论成功", Toast.LENGTH_SHORT).show();
            hideInput();
            galleryCurrEditText.setText("");
            onRefresh(replyRefreshLayout);
        }
    }

    @Override
    public void onClickLike(CommentBean bean) {

    }

    public void sendReply(String pid, String commentId, String username) {
        this.pid = pid;
        this.commentId = commentId;
        galleryCurrEditText.setHint("@" + username);
        isShowInput = true;
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void clickLike(String comment_id, String like) {
        getCommentReplyPresenter.clickLike(comment_id, like);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}