package com.wetime.fanc.shopcenter.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.shopcenter.adapter.ShopListAdapter;
import com.wetime.fanc.shopcenter.bean.ShopCenterPageBean;
import com.wetime.fanc.shopcenter.iviews.IGetShopCenterPageView;
import com.wetime.fanc.shopcenter.presenter.GetShopCenterPagePresenter;
import com.wetime.fanc.web.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCenterActivity extends BaseActivity implements IGetShopCenterPageView {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.lv_shop)
    ListView lvShop;

    private GetShopCenterPagePresenter getShopCenterPagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcenter);
        ButterKnife.bind(this);
        tvTitle.setText("购物中心");
        getShopCenterPagePresenter = new GetShopCenterPagePresenter(this);
        getShopCenterPagePresenter.getShopCenterPage();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_search:
                Intent gosearch = new Intent(this, HomeSearchActivity.class);
                startActivity(gosearch);
                break;

        }
    }

    @Override
    public String getJd() {
        return spu.getValue("jd");
    }

    @Override
    public String getWd() {
        return spu.getValue("wd");
    }

    @Override
    public void onGetShopCenterPageBean(final ShopCenterPageBean bean) {
        ShopListAdapter adapter = new ShopListAdapter(this, bean.getData().getList());
        lvShop.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        View head = LayoutInflater.from(mContext).inflate(R.layout.item_shopcenter_head, null);
        lvShop.addHeaderView(head);
        TextView tvTitle = head.findViewById(R.id.tv_title);
        tvTitle.setText(bean.getData().getCount());


        Banner banner = head.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> b = new ArrayList<>();
        for (ShopCenterPageBean.DataBean.BannerBean bb : bean.getData().getBanner()) {
            b.add(bb.getImg());
        }

        banner.setImages(b);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (!bean.getData().getBanner().get(position).getUrl().equals("")) {
                    Intent goweb = new Intent(mContext, WebActivity.class);
                    goweb.putExtra("url", bean.getData().getBanner().get(position).getUrl());
                    mContext.startActivity(goweb);
                }
            }
        });

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }
    }
}
