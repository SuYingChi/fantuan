package com.wetime.fanc.web.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.R;
import com.wetime.fanc.customview.GoodView;
import com.wetime.fanc.customview.recycview.NestedScrollingWebView;
import com.wetime.fanc.my.act.UserCardActivity;
import com.wetime.fanc.news.act.ReplyActivity;
import com.wetime.fanc.news.adapter.CommentListAdapter;
import com.wetime.fanc.news.bean.CommentBean;
import com.wetime.fanc.news.bean.NewsListItemBean;
import com.wetime.fanc.utils.DialogUtils;
import com.wetime.fanc.web.NewsDetailWebActivity;
import com.wetime.fanc.web.bean.NewsWebBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/8.
 */

public class NewsWebAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<CommentBean.DataBean.ListBean> dataComm = new ArrayList<>();
    private List<NewsListItemBean> data = new ArrayList<>();
    private LayoutInflater inflater;
    private String url;
    private NewsHolder1 holder1;
    private RecommendReadAdapter adapter;
    private CommentListAdapter commentListAdapter;
    private GoodView mGoodView;

    public NewsWebAdapter(Context context, String url, List<CommentBean.DataBean.ListBean> dataComm, List<NewsListItemBean> data) {
        this.context = context;
        this.url = url;
        this.dataComm = dataComm;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        mGoodView = new GoodView(context);
    }

    public void setData(List<NewsListItemBean> data) {
        this.data = data;
        if (holder1 != null) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new RecommendReadAdapter(context, R.layout.item_recommend_read, data);
                holder1.webItemRc.setAdapter(adapter);
            }
        }
    }

    public void setDataComm(List<CommentBean.DataBean.ListBean> dataComm) {
        this.dataComm = dataComm;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new NewsHolder0(inflater.inflate(R.layout.item_web, parent, false));
        } else if (viewType == 1) {
            return new NewsHolder1(inflater.inflate(R.layout.item_web_ather, parent, false));
        } else {
            return new NewsHolder2(inflater.inflate(R.layout.item_comment, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NewsHolder0) {
            initWebView(((NewsHolder0) holder).webview);
            ((NewsHolder0) holder).webview.loadUrl(url);
            ((NewsHolder0) holder).webview.setOnScrollChangedCallback((dx, dy) -> {
                //这里我们根据dx和dy参数做自己想做的事情
                if (dy > 80) {
                    ((NewsDetailWebActivity) context).showTool();
                } else {
                    ((NewsDetailWebActivity) context).hideTool();
                }
            });
        }

        if (holder instanceof NewsHolder1) {
            holder1 = (NewsHolder1) holder;
            holder1.webAttention.setText(((NewsDetailWebActivity) context).like_num);

            if (((NewsDetailWebActivity) context).has_like) {
                Drawable top = context.getResources().getDrawable(R.drawable.icon_att_nor);
                holder1.webAttention.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            } else {
                Drawable top = context.getResources().getDrawable(R.drawable.icon_att_v2);
                holder1.webAttention.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            }
            holder1.llShareCopy.setOnClickListener(v -> {

            });
            holder1.llShareWx.setOnClickListener(v -> {

            });
            holder1.llShareWxq.setOnClickListener(v -> {

            });
            holder1.webItemRc.setLayoutManager(new LinearLayoutManager(context));
            adapter = new RecommendReadAdapter(context, R.layout.item_recommend_read, data);
            holder1.webItemRc.setAdapter(adapter);

            if (dataComm == null || dataComm.size() == 0) {
                ((NewsHolder1) holder).empty.setVisibility(View.VISIBLE);
            } else {
                ((NewsHolder1) holder).empty.setVisibility(View.GONE);
            }

//            holder1.webItemCommRc.setLayoutManager(new LinearLayoutManager(context));
//            commentListAdapter = new CommentListAdapter(context, R.layout.item_comment, dataComm);
//            holder1.webItemCommRc.setAdapter(commentListAdapter);
        }

        if (holder instanceof NewsHolder2) {
            CommentBean.DataBean.ListBean commentTestBean = dataComm.get(position - 2);
            if (commentTestBean.isIs_author()) {
                ((NewsHolder2) holder).comment_name.setText("我");
                ((NewsHolder2) holder).comment_delete.setVisibility(View.VISIBLE);
            } else {
                ((NewsHolder2) holder).comment_name.setText(commentTestBean.getUser().getUsername());
                ((NewsHolder2) holder).comment_delete.setVisibility(View.GONE);
            }
            ((NewsHolder2) holder).comment_content.setText(commentTestBean.getContent());
            if (commentTestBean.getReply_num().equals("0")) {
                ((NewsHolder2) holder).comment_number.setText("回复");
            } else {
                ((NewsHolder2) holder).comment_number.setText(commentTestBean.getReply_num() + "条回复");
            }
            ((NewsHolder2) holder).comment_good.setText(commentTestBean.getLike_num());
            ((NewsHolder2) holder).comment_time.setText(commentTestBean.getTime());
            Glide.with(context).load(commentTestBean.getUser().getAvatar()).into(((NewsHolder2) holder).comment_head);
            if (commentTestBean.isIs_like()) {
                ((NewsHolder2) holder).comment_image.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                ((NewsHolder2) holder).comment_good.setTextColor(Color.parseColor("#ff3f53"));
            } else {
                ((NewsHolder2) holder).comment_image.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                ((NewsHolder2) holder).comment_good.setTextColor(Color.parseColor("#999999"));
            }
//            holder.itemView.setOnClickListener(v -> ((CommentActivity) context).hideInput());
            ((NewsHolder2) holder).comment_delete.setOnClickListener(v -> {
                DialogUtils.showNormalDialog(context, null, "确认删除该评论？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((NewsDetailWebActivity) context).deleteComment(commentTestBean.getId());
                        dialog.dismiss();
                    }
                });

            });
            ((NewsHolder2) holder).comment_linear.setOnClickListener(v -> {//0、取消点赞1、点赞
                if (mGoodView.isShowing()) return;
                if (commentTestBean.isIs_like()) {
                    mGoodView.setText("-1");
                    mGoodView.show(v);
                    commentTestBean.setIs_like(false);
                    ((NewsHolder2) holder).comment_image.setImageResource(R.drawable.ic_homeitem_zan_off_off);
                    int i = Integer.parseInt(((NewsHolder2) holder).comment_good.getText().toString());
                    ((NewsHolder2) holder).comment_good.setText(String.valueOf(i - 1));
                    ((NewsHolder2) holder).comment_good.setTextColor(Color.parseColor("#999999"));
                    ((NewsDetailWebActivity) context).clickLike(commentTestBean.getId(), "0");
                } else {
                    mGoodView.setText("+1");
                    mGoodView.show(v);
                    commentTestBean.setIs_like(true);
                    ((NewsHolder2) holder).comment_image.setImageResource(R.drawable.ic_homeitem_zan_off_on);
                    int i = Integer.parseInt(((NewsHolder2) holder).comment_good.getText().toString());
                    ((NewsHolder2) holder).comment_good.setText(String.valueOf(i + 1));
                    ((NewsHolder2) holder).comment_good.setTextColor(Color.parseColor("#ff3f53"));
                    ((NewsDetailWebActivity) context).clickLike(commentTestBean.getId(), "1");
                }

            });

            ((NewsHolder2) holder).comment_number.setOnClickListener(v -> {
                ReplyActivity.startToReplyActivity(context, commentTestBean);
            });
            ((NewsHolder2) holder).comment_head.setOnClickListener(v -> {
                Intent go = new Intent(context, UserCardActivity.class);
                go.putExtra("num", "3");
                go.putExtra("index", 0);
                go.putExtra("id", commentTestBean.getUser().getId());
                context.startActivity(go);
            });
            holder.itemView.setOnClickListener(v -> {
                ReplyActivity.startToReplyActivity(context, commentTestBean);
            });
        }

    }


    @Override
    public int getItemCount() {
        if (dataComm != null) {
            return 2 + dataComm.size();
        } else {
            return 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    public void initWebView(WebView mWebView) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        mWebView.getSettings().setLayoutAlgorithm
                (WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
    }

    class NewsHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.web_webview)
        NestedScrollingWebView webview;


        NewsHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_name)
        TextView comment_name;
        @BindView(R.id.comment_delete)
        LinearLayout comment_delete;
        @BindView(R.id.comment_content)
        TextView comment_content;
        @BindView(R.id.comment_number)
        TextView comment_number;
        @BindView(R.id.comment_good)
        TextView comment_good;
        @BindView(R.id.comment_time)
        TextView comment_time;
        @BindView(R.id.comment_head)
        CircleImageView comment_head;
        @BindView(R.id.comment_image)
        ImageView comment_image;
        @BindView(R.id.comment_linear)
        LinearLayout comment_linear;

        NewsHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class NewsHolder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.web_attention)
        TextView webAttention;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.ll_share_wx)
        LinearLayout llShareWx;
        @BindView(R.id.ll_share_wxq)
        LinearLayout llShareWxq;
        @BindView(R.id.ll_share_copy)
        LinearLayout llShareCopy;
        @BindView(R.id.web_item_rc)
        RecyclerView webItemRc;
        @BindView(R.id.empty)
        LinearLayout empty;

        NewsHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
