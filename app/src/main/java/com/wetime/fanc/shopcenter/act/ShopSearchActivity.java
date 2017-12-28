package com.wetime.fanc.shopcenter.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.reflect.TypeToken;
import com.wetime.fanc.main.act.BaseActivity;
import com.wetime.fanc.shop.act.ShopDetailActivity;
import com.wetime.fanc.utils.GsonUtils;
import com.wetime.fanc.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HisAdapter;
import com.wetime.fanc.home.adapter.HotWordAdapter;
import com.wetime.fanc.home.adapter.ResultAdapter;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.home.bean.SearchResult;
import com.wetime.fanc.home.iviews.IGetHomeSugView;
import com.wetime.fanc.home.presenter.GetHomeSugSerachPresenter;
import com.wetime.fanc.shopcenter.event.ShopKeyWordEvent;
import com.wetime.fanc.shopcenter.iviews.IGetShopHotSearchView;
import com.wetime.fanc.shopcenter.presenter.GetShopHotSerachPresenter;
import com.wetime.fanc.web.WebActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopSearchActivity extends BaseActivity implements IGetShopHotSearchView, TextView.OnEditorActionListener, TextWatcher, IGetHomeSugView {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rcl_hotword)
    RecyclerView rclHotword;
    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.ll_his)
    RelativeLayout llHis;
    @BindView(R.id.rcl_his)
    RecyclerView rclHis;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.rcl_result)
    RecyclerView rclResult;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    private GetShopHotSerachPresenter getShopHotSerachPresenter;
    private List<String> hislist = new ArrayList<>();
    private HisAdapter hisAdapter;
    private GetHomeSugSerachPresenter getHomeSugSerachPresenter;
    private ResultAdapter resultAdapter;
    private List<SearchResult.DataBean.MerchantsBean> reList = new ArrayList();
    private String millId = "";
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_search);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearch, InputMethodManager.SHOW_FORCED);
        name = getIntent().getStringExtra("name");
        millId = getIntent().getStringExtra("id");
        etSearch.setHint("在" + name + "中的搜索结果");

        
        getShopHotSerachPresenter = new GetShopHotSerachPresenter(this);
        getShopHotSerachPresenter.getHotSearchPage();

        etSearch.setOnEditorActionListener(this);
        etSearch.addTextChangedListener(this);


        resultAdapter = new ResultAdapter(reList, this);
        rclResult.setLayoutManager(new LinearLayoutManager(this));
        rclResult.setAdapter(resultAdapter);
        getHomeSugSerachPresenter = new GetHomeSugSerachPresenter(this);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("key"))) {
            etSearch.setText(getIntent().getStringExtra("key"));
            etSearch.setSelection(etSearch.getText().length());
            llHot.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            ((InputMethodManager) etSearch.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    ShopSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            if (etSearch.getText().toString().equals(""))
                return false;


//            Tools.toastInBottom(this, "search");
            savaHis(etSearch.getText().toString());
            goResult(etSearch.getText().toString());
//            if (!TextUtils.isEmpty(getIntent().getStringExtra("key"))) {
//                finish();
//            }
            return true;
        }
        return false;
    }


    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.iv_delete,R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.iv_delete:
                spu.setValue("his", "");
                hislist.clear();
                ivDelete.setVisibility(View.GONE);
                hisAdapter.notifyDataSetChanged();
                break;
            case R.id.iv_close:
                etSearch.setText("");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (spu.getValue("shophis").equals("")) {
            llHis.setVisibility(View.GONE);
        } else {
            llHis.setVisibility(View.VISIBLE);
            hislist = GsonUtils.getGsonInstance().fromJson(spu.getValue("shophis"), new TypeToken<List<String>>() {
            }.getType());
            hisAdapter = new HisAdapter(hislist, this);

            FlexboxLayoutManager manager3 = new FlexboxLayoutManager(this);
            //设置主轴排列方式
            manager3.setFlexDirection(FlexDirection.ROW);
            //设置是否换行
            manager3.setFlexWrap(FlexWrap.WRAP);
            manager3.setAlignItems(AlignItems.STRETCH);
            rclHis.setLayoutManager(manager3);
            rclHis.setAdapter(hisAdapter);
            hisAdapter.setOnItemClickLitener(new HisAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    goResult(hislist.get(position));
                }
            });
        }

    }

    @Override
    public String getMailId() {
        return millId;
    }

    @Override
    public void onGetHotSearchPage(final HomeHotSearchBean bean) {
        if (TextUtils.isEmpty(etSearch.getText())) {
            llHot.setVisibility(View.VISIBLE);
        } else {
            llHot.setVisibility(View.GONE);
        }

        FlexboxLayoutManager manager = new FlexboxLayoutManager(this);
        manager.setFlexDirection(FlexDirection.ROW);
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);

        HotWordAdapter hotWordAdapter = new HotWordAdapter(bean.getData().getMerchant_hots(), this);
        rclHotword.setLayoutManager(manager);
        rclHotword.setAdapter(hotWordAdapter);
        hotWordAdapter.notifyDataSetChanged();
        hotWordAdapter.setOnItemClickLitener(new HotWordAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                goResult(bean.getData().getMerchant_hots().get(position).getName());
            }
        });


    }

    private void savaHis(String word) {
        int pos = -1;
        for (int i = 0; i < hislist.size(); i++) {
            if (TextUtils.equals(word, hislist.get(i))) {
                pos = i;
            }
        }
        if (pos > -1) {
            hislist.remove(pos);
        }

        hislist.add(0, word);

        if (hislist.size() > 10)
            hislist.remove(9);

        spu.setValue("shophis", GsonUtils.getGsonInstance().toJson(hislist));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() > 0) {
            ivClose.setVisibility(View.VISIBLE);
            llHot.setVisibility(View.GONE);
            getHomeSugSerachPresenter.getSugSearchPage(editable.toString());
        } else {
            ivClose.setVisibility(View.GONE);
            reList.clear();
            resultAdapter.notifyDataSetChanged();
            llHot.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetHomeSug(SearchResult bean,String word) {
        if(!word.equals(etSearch.getText().toString())){
            return;
        }
        reList.clear();
        reList.addAll(bean.getData().getMerchants());
        resultAdapter.notifyDataSetChanged();
        resultAdapter.setOnItemClickLitener(new ResultAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent goShop = new Intent(mContext, ShopDetailActivity.class);
                goShop.putExtra("mid",reList.get(position).getMid());
                startActivity(goShop);
            }
        });
    }

    private void goResult(String key) {
        Intent go = new Intent(this, ShopSearchResultActivity.class);
        go.putExtra("key", key);
        go.putExtra("id", millId);
        go.putExtra("name", key);
        startActivity(go);
    }

    private void goWeb(String url) {
        Intent goweb = new Intent(this, WebActivity.class);
        goweb.putExtra("url", url);
        startActivity(goweb);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final ShopKeyWordEvent event) {
        etSearch.setText(event.getKey());
        etSearch.setSelection(etSearch.getText().length());
    }
}
