package com.wetime.fanc.home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fan.http.okhttp.OkHttpUtils;
import com.fan.http.okhttp.callback.Callback;
import com.fan.http.okhttp.callback.StringCallback;
import com.wetime.fanc.R;
import com.wetime.fanc.circle.presenter.FocusPresenter;
import com.wetime.fanc.home.act.MainActivity;
import com.wetime.fanc.home.bean.HeadRecommendBean;
import com.wetime.fanc.login.act.LoginActivity;
import com.wetime.fanc.main.model.ErrorBean;
import com.wetime.fanc.utils.Const;
import com.wetime.fanc.utils.DataStringCallback;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/6.
 */

class RecommendHeaderRecyclerViewAdapter extends RecyclerView.Adapter  {

    private final Activity activity;
    private final LayoutInflater inflate;
    private  List<HeadRecommendBean.DataBean.CirclesBean> circles;
    private OnItemClickLitener onItemClickLitener;

    public RecommendHeaderRecyclerViewAdapter(List<HeadRecommendBean.DataBean.CirclesBean> circles, Activity activity) {
        this.circles = circles;
        this.activity = activity;
        this.inflate = LayoutInflater.from(activity);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendHeaderViewHolder(inflate.inflate(R.layout.item_recommend_circle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendHeaderViewHolder recommendHeaderViewHolder = (RecommendHeaderViewHolder)holder;
        HeadRecommendBean.DataBean.CirclesBean circleBean = circles.get(position);
        Glide.with(activity).load(circles.get(position).getCover().getCompress())
                .apply(new RequestOptions().centerCrop())
                .into(recommendHeaderViewHolder.circleImage);
        recommendHeaderViewHolder.circleName.setText(circles.get(position).getName());
        recommendHeaderViewHolder.textView.setText(circles.get(position).getNum());
        recommendHeaderViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FocusPresenter focusPresenter = new FocusPresenter();
                if (((MainActivity) activity).getToken().isEmpty()) {
                    Tools.toastInBottom(activity, "请先登录");
                    Intent goLogin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(goLogin);
                    return;
                }
                if (TextUtils.isEmpty(Tools.getSpu(activity).getToken())) {
                    Tools.toastInBottom(activity, "请先登录");
                    Intent goLogin = new Intent(activity, LoginActivity.class);
                    activity.startActivity(goLogin);
                } else {
                    OkHttpUtils
                            .post()
                            .url(Const.ATTENTION_CIRLCLE)
                            .addHeader("token", Tools.getSpu(activity).getToken())
                            .addParams("id", circleBean.getId())
                            .addParams("follow", "1")
                            .build()
                            .execute(new Callback<ErrorBean>() {
                                @Override
                                public ErrorBean parseNetworkResponse(Response response, int i) throws IOException {
                                 String s = response.body().string();
                                  return   GsonUtils.getGsonInstance().fromJson(s, ErrorBean.class);

                                }
                                @Override
                                public void onError(Call call, Exception e, int i) {

                                }
                                @Override
                                public void onResponse(ErrorBean bean, int i) {
                                    if (bean.getError() == 0){
                                        recommendHeaderViewHolder.button.setBackgroundResource(R.drawable.bg_btn_gray_circle);
                                    }
                                }

                            });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return circles.size();
    }

    public void setRecommendList(List<HeadRecommendBean.DataBean.CirclesBean> recommendList) {
        this.circles = recommendList;
    }



    public static class RecommendHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_image)
        ImageView circleImage;
        @BindView(R.id.recommend_circle_name)
        TextView circleName;
        @BindView(R.id.recommend_circle_attention_num)
        TextView textView;
        @BindView(R.id.is_attention)
        Button button;


        public RecommendHeaderViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }
    }
   void  setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
       this.onItemClickLitener = onItemClickLitener;
    }
    public interface OnItemClickLitener{
       void onclick(View view, int position);
    }
}

