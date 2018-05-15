package com.wetime.fanc.circle.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.act.ActDetailActivity;
import com.wetime.fanc.circle.act.LongDetailActivity;
import com.wetime.fanc.circle.bean.CircleListBean;
import com.wetime.fanc.circle.presenter.ZanActPresenter;
import com.wetime.fanc.customview.CanDoBlankGridView;
import com.wetime.fanc.customview.MixtureTextView;
import com.wetime.fanc.home.adapter.FoucsNineGridLAdapter;
import com.wetime.fanc.home.bean.Cover;
import com.wetime.fanc.utils.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuxun on 2017/4/15.
 */

public class CircleListAdapterV2 extends RecyclerView.Adapter {


    private List<CircleListBean.DataBean.ListBean> list;
    private Activity mContext;
    private ZanActPresenter zanpresenter;
    private OnZanClickLitener mOnZanClickLitener;

//    tpye 10=无图 11=1图 14=四图 19=九宫格 18=长文

    public CircleListAdapterV2(List<CircleListBean.DataBean.ListBean> list, Activity mContext) {
        this.list = list;
        this.mContext = mContext;
        zanpresenter = new ZanActPresenter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 10 || viewType == 11) {
            return new V10ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_act_type10_v2, parent, false));
        }
        if (viewType == 18) {
            return new V18ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_act_type18_v2, parent, false));
        }
        if (viewType == 14 || viewType == 19) {
            return new V19ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_act_type19_v2, parent, false));
        }

        return new NewsHolderTemp(LayoutInflater.from(mContext).inflate(R.layout.item_news_type_temp, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CircleListBean.DataBean.ListBean bean = list.get(position);


        holder.itemView.setOnClickListener(view -> {
            switch (Integer.parseInt(bean.getType())) {
                case 18:
                    LongDetailActivity.startToLongDetail(mContext, bean.getId());
                    break;
                default:
                    Intent goDet = new Intent(mContext, ActDetailActivity.class);
                    goDet.putExtra("id", bean.getId());
                    mContext.startActivity(goDet);
                    break;
            }
        });

        if (holder instanceof V10ViewHolder) {
            V10ViewHolder mHolder = (V10ViewHolder) holder;
            Glide.with(mContext).load(bean.getAvatar()).into(mHolder.ivHead);
            mHolder.tvName.setText(bean.getUsername());
            mHolder.tvTime.setText(bean.getTime());
            mHolder.llHeadCircle.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(bean.getContent())) {
                mHolder.tvContent.setVisibility(View.VISIBLE);
                mHolder.tvContent.setText(bean.getContent());
            } else {
                mHolder.tvContent.setVisibility(View.GONE);
                mHolder.tvContent.setText(bean.getContent());
            }

            if (TextUtils.isEmpty(bean.getLocation())) {
                mHolder.tvLoc.setVisibility(View.GONE);
            } else {
                mHolder.tvLoc.setVisibility(View.VISIBLE);
                mHolder.tvLoc.setText(bean.getLocation());
            }

            mHolder.tvZannum.setText(Tools.getZanStr(Integer.valueOf(bean.getLike_num())));
            if (bean.isHas_like()) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_red_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#FF3F53"));
            } else {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#666666"));
            }
            if (mOnZanClickLitener != null) {
                mHolder.tvZannum.setOnClickListener(v -> mOnZanClickLitener.onItemClick(v, position));
            }


            if (bean.getComment_num().equals("0")) {
                mHolder.tvCommentNum.setText("评论");
            } else {
                mHolder.tvCommentNum.setText(bean.getComment_num());
            }


            int sw = Tools.getScreenW(mContext);
            //图片 1图
            if (bean.getCovers().size() == 0) {
                mHolder.rlIv.setVisibility(View.GONE);
            } else {
                int w, h;
                Cover coversBean = bean.getCovers().get(0);
                if (coversBean.isLongCover()) {
                    //长图
                    mHolder.ivIsLong.setVisibility(View.VISIBLE);

                    Double rate = 120.0 / 750;
                    w = (int) (sw * rate);
                    Double rate2 = 360.0 * 1.0 / 750;
                    h = (int) (sw * rate2);
                } else {
                    mHolder.ivIsLong.setVisibility(View.GONE);
                    //宽大

                    if (coversBean.getWidth() >= coversBean.getHeight()) {
                        Double rate = 360.0 / 750;
                        w = (int) (sw * rate);
                        Double rate2 = coversBean.getHeight() * 1.0 / coversBean.getWidth();
                        h = (int) (w * rate2);
                    } else {
                        //高大
                        Double rate = 360.0 / 750;
                        h = (int) (sw * rate);
                        Double rate2 = coversBean.getWidth() * 1.0 / coversBean.getHeight();
                        w = (int) (h * rate2);
                    }
                }
                ViewGroup.LayoutParams params = mHolder.rlIv.getLayoutParams();
                params.width = w;
                params.height = h + Tools.dip2px(mContext, 12);//12pading
                mHolder.rlIv.setLayoutParams(params);

                Glide.with(mContext).load(coversBean.getCompress())
                        .apply(new RequestOptions().override(w, h)
                                .centerCrop()
                                .placeholder(R.drawable.iv_default))
                        .into(mHolder.ivCover);
                mHolder.ivCover.setOnClickListener(v -> Tools.goPicGallery(mContext, bean.getCovers(), 0));

            }

        }
        if (holder instanceof V19ViewHolder) {
            V19ViewHolder mHolder = (V19ViewHolder) holder;
            Glide.with(mContext).load(bean.getAvatar()).into(mHolder.ivHead);
            mHolder.tvName.setText(bean.getUsername());
            mHolder.tvTime.setText(bean.getTime());
            mHolder.llHeadCircle.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(bean.getContent())) {
                mHolder.tvContent.setVisibility(View.VISIBLE);
                mHolder.tvContent.setText(bean.getContent());
            } else {
                mHolder.tvContent.setVisibility(View.GONE);
                mHolder.tvContent.setText(bean.getContent());
            }

            if (TextUtils.isEmpty(bean.getLocation())) {
                mHolder.tvLoc.setVisibility(View.GONE);
            } else {
                mHolder.tvLoc.setVisibility(View.VISIBLE);
                mHolder.tvLoc.setText(bean.getLocation());
            }

            mHolder.tvZannum.setText(Tools.getZanStr(Integer.valueOf(bean.getLike_num())));
            if (bean.isHas_like()) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_red_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#FF3F53"));
            } else {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#666666"));
            }
            if (mOnZanClickLitener != null) {
                mHolder.tvZannum.setOnClickListener(v -> mOnZanClickLitener.onItemClick(v, position));
            }
            if (bean.getComment_num().equals("0")) {
                mHolder.tvCommentNum.setText("评论");
            } else {
                mHolder.tvCommentNum.setText(bean.getComment_num());
            }


            int sw = Tools.getScreenW(mContext);
            ViewGroup.LayoutParams params = mHolder.gv.getLayoutParams();
            int iW = sw - (Tools.dip2px(mContext, 15 + 15 + 3 + 3)) / 3;
            //四宫格
            if (getItemViewType(position) == 14) {
                params.width = iW * 2 + Tools.dip2px(mContext, 3);
                params.height = iW * 2 + Tools.dip2px(mContext, 3);
                mHolder.gv.setNumColumns(2);

            } else {//九宫格
                params.width = iW * 3 + Tools.dip2px(mContext, 3 + 3);
                params.height = iW * 2 + Tools.dip2px(mContext, 3);
                mHolder.gv.setNumColumns(3);
            }
            FoucsNineGridLAdapter adapter = new FoucsNineGridLAdapter(mContext, bean.getCovers());
            mHolder.gv.setLayoutParams(params);
            mHolder.gv.setAdapter(adapter);
            mHolder.gv.setOnTouchInvalidPositionListener(motionEvent -> false);
            mHolder.gv.setOnItemClickListener((adapterView, view, i, l) -> Tools.goPicGallery(mContext, bean.getCovers(), i));

        }

        if (holder instanceof V18ViewHolder) {
            V18ViewHolder mHolder = (V18ViewHolder) holder;
            Glide.with(mContext).load(bean.getAvatar()).into(mHolder.ivHead);
            mHolder.tvName.setText(bean.getUsername());
            mHolder.tvTime.setText(bean.getTime());
            mHolder.llHeadCircle.setVisibility(View.GONE);

            mHolder.tvContent.setText(" " + bean.getTitle());

            if (TextUtils.isEmpty(bean.getLocation())) {
                mHolder.tvLoc.setVisibility(View.GONE);
            } else {
                mHolder.tvLoc.setVisibility(View.VISIBLE);
                mHolder.tvLoc.setText(bean.getLocation());
            }
            mHolder.tvZannum.setText(Tools.getZanStr(Integer.valueOf(bean.getLike_num())));
            if (bean.isHas_like()) {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_red_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#FF3F53"));
            } else {
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_zan_v2);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
                mHolder.tvZannum.setCompoundDrawables(drawable, null, null, null);
                mHolder.tvZannum.setTextColor(Color.parseColor("#666666"));
            }
            if (mOnZanClickLitener != null) {
                mHolder.tvZannum.setOnClickListener(v -> mOnZanClickLitener.onItemClick(v, position));
            }
            if (bean.getComment_num().equals("0")) {
                mHolder.tvCommentNum.setText("评论");
            } else {
                mHolder.tvCommentNum.setText(bean.getComment_num());
            }

            //右侧feng'm封面图
            if (bean.getCovers().size() > 0) {
                Glide.with(mContext).load(bean.getCovers().get(0).getCompress()).into(mHolder.ivCover);
            }


        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnZanClickLitener(OnZanClickLitener mOnZanClickLitener) {
        this.mOnZanClickLitener = mOnZanClickLitener;
    }

    public interface OnZanClickLitener {
        void onItemClick(View view, int position);
    }

    class NewsHolderTemp extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;


        NewsHolderTemp(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class V10ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_loc)
        TextView tvLoc;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentNum;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.iv_islong)
        ImageView ivIsLong;
        @BindView(R.id.rl_iv)
        RelativeLayout rlIv;


        V10ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class V19ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_loc)
        TextView tvLoc;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentNum;
        @BindView(R.id.gv)
        CanDoBlankGridView gv;

        @BindView(R.id.rl_iv)
        RelativeLayout rlIv;


        V19ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class V18ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_circlename)
        TextView tvCirclename;
        @BindView(R.id.ll_head_circle)
        LinearLayout llHeadCircle;
        @BindView(R.id.tv_zannum)
        TextView tvZannum;
        @BindView(R.id.tv_content)
        MixtureTextView tvContent;
        @BindView(R.id.tv_loc)
        TextView tvLoc;
        @BindView(R.id.tv_commentnum)
        TextView tvCommentNum;
        @BindView(R.id.iv_cover)
        ImageView ivCover;


        V18ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
