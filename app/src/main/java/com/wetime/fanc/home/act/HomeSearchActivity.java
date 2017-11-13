package com.wetime.fanc.home.act;

import android.content.Context;
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
import com.king.batterytest.fbaselib.main.BaseActivity;
import com.king.batterytest.fbaselib.utils.GsonUtils;
import com.king.batterytest.fbaselib.utils.Tools;
import com.wetime.fanc.R;
import com.wetime.fanc.home.adapter.HisAdapter;
import com.wetime.fanc.home.adapter.HotMallAdapter;
import com.wetime.fanc.home.adapter.HotWordAdapter;
import com.wetime.fanc.home.adapter.ResultAdapter;
import com.wetime.fanc.home.bean.HomeHotSearchBean;
import com.wetime.fanc.home.bean.SearchResult;
import com.wetime.fanc.home.iviews.IGetHomeHotSearchView;
import com.wetime.fanc.home.iviews.IGetHomeSugView;
import com.wetime.fanc.home.presenter.GetHomeHotSerachPresenter;
import com.wetime.fanc.home.presenter.GetHomeSugSerachPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseActivity implements IGetHomeHotSearchView, TextView.OnEditorActionListener, TextWatcher, IGetHomeSugView {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rcl_hotword)
    RecyclerView rclHotword;
    @BindView(R.id.rcl_hotmall)
    RecyclerView rclHotmall;
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
    private GetHomeHotSerachPresenter getHomeHotSerachPresenter;
    private List<String> hislist = new ArrayList<>();
    private HisAdapter hisAdapter;
    private GetHomeSugSerachPresenter getHomeSugSerachPresenter;
    private ResultAdapter resultAdapter;
    private List<SearchResult.DataBean.MerchantsBean> reList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        ButterKnife.bind(this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearch, InputMethodManager.SHOW_FORCED);
        getHomeHotSerachPresenter = new GetHomeHotSerachPresenter(this);
        getHomeHotSerachPresenter.getHotSearchPage();
        etSearch.setOnEditorActionListener(this);
        etSearch.addTextChangedListener(this);
        resultAdapter = new ResultAdapter(reList, this);
        rclResult.setLayoutManager(new LinearLayoutManager(this));
        rclResult.setAdapter(resultAdapter);
        getHomeSugSerachPresenter = new GetHomeSugSerachPresenter(this);

    }

    @Override
    public void onBackPressed() {
        Tools.hideSoftInput(this);
        super.onBackPressed();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) etSearch.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    HomeSearchActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            Tools.toastInBottom(this, "search");
            savaHis(etSearch.getText().toString());
            return true;
        }
        return false;
    }


    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_cancel:
//                onBackPressed();
                break;
            case R.id.iv_delete:
                spu.setValue("his", "");
                hislist.clear();
                ivDelete.setVisibility(View.GONE);
                hisAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (spu.getValue("his").equals("")) {
            llHis.setVisibility(View.GONE);
        } else {
            llHis.setVisibility(View.VISIBLE);
            hislist = GsonUtils.getGsonInstance().fromJson(spu.getValue("his"), new TypeToken<List<String>>() {
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
        }

    }

    @Override
    public void onGetHotSearchPage(HomeHotSearchBean bean) {
        llHot.setVisibility(View.VISIBLE);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this);
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);

        HotWordAdapter hotWordAdapter = new HotWordAdapter(bean.getData().getMerchant_hots(), this);
        rclHotword.setLayoutManager(manager);
        rclHotword.setAdapter(hotWordAdapter);
        hotWordAdapter.notifyDataSetChanged();

        FlexboxLayoutManager manager2 = new FlexboxLayoutManager(this);
        //设置主轴排列方式
        manager2.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager2.setFlexWrap(FlexWrap.WRAP);
        manager2.setAlignItems(AlignItems.STRETCH);
        HotMallAdapter hotMallAdapter = new HotMallAdapter(bean.getData().getMall_hots(), this);
        rclHotmall.setLayoutManager(manager2);
        rclHotmall.setAdapter(hotMallAdapter);
        hotMallAdapter.notifyDataSetChanged();


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

        spu.setValue("his", GsonUtils.getGsonInstance().toJson(hislist));
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
            llHot.setVisibility(View.GONE);
            getHomeSugSerachPresenter.getSugSearchPage(editable.toString());
        } else {
            reList.clear();
            resultAdapter.notifyDataSetChanged();
            llHot.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetHomeSug(SearchResult bean) {
        reList.clear();
        reList.addAll(bean.getData().getMerchants());
        resultAdapter.notifyDataSetChanged();
    }
}
