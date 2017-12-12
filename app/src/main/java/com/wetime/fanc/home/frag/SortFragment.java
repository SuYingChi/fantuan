package com.wetime.fanc.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.king.batterytest.fbaselib.customview.GridViewForScrollView;
import com.king.batterytest.fbaselib.main.BaseFragment;
import com.wetime.fanc.R;
import com.wetime.fanc.home.act.HomeSearchActivity;
import com.wetime.fanc.home.adapter.SortGridAdapter;
import com.wetime.fanc.home.adapter.SortTitleAdapter;
import com.wetime.fanc.home.bean.SortPageBean;
import com.wetime.fanc.home.iviews.IGetSortView;
import com.wetime.fanc.home.presenter.GetSortPagePresenter;
import com.wetime.fanc.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SortFragment extends BaseFragment implements IGetSortView, AdapterView.OnItemClickListener {


    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.lv_title)
    ListView lvTitle;
    Unbinder unbinder;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.gv_all)
    GridViewForScrollView gvAll;
    @BindView(R.id.sc_content)
    ScrollView scContent;
    private GetSortPagePresenter getSortPagePresenter;
    private SortTitleAdapter titleadapter;
    private SortPageBean bean;
    private SortGridAdapter sortGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sort, null);
        getSortPagePresenter = new GetSortPagePresenter(this);
        getSortPagePresenter.getSortPage();
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onGetSortPage(final SortPageBean bean) {
        this.bean = bean;
        titleadapter = new SortTitleAdapter(getContext(), bean.getData().getAll_category());
        lvTitle.setAdapter(titleadapter);
        titleadapter.notifyDataSetChanged();
        lvTitle.setOnItemClickListener(this);
        sortGridAdapter = new SortGridAdapter(getContext(), bean.getData().getAll_category().get(0).getSubcates());
        gvAll.setAdapter(sortGridAdapter);
        sortGridAdapter.notifyDataSetChanged();
        gvAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent goweb = new Intent(getContext(), WebActivity.class);
                goweb.putExtra("url", bean.getData().getAll_category().get(0).getSubcates().get(i).getUrl());
                startActivity(goweb);
            }
        });
        Glide.with(this).load(bean.getData().getBanner()).into(ivBanner);
        tvName.setText(bean.getData().getAll_category().get(0).getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        if (bean == null)
            return;
        titleadapter.setSelected(i);
        scContent.scrollTo(0, 0);
        tvName.setText(bean.getData().getAll_category().get(i).getName());
        if (i == 0) {
            ivBanner.setVisibility(View.VISIBLE);
        } else {
            ivBanner.setVisibility(View.GONE);
        }
        sortGridAdapter.setList(bean.getData().getAll_category().get(i).getSubcates());
        gvAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent goweb = new Intent(getContext(), WebActivity.class);
                goweb.putExtra("url", bean.getData().getAll_category().get(i).getSubcates().get(index).getUrl());
                startActivity(goweb);
            }
        });
    }

    @OnClick({R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                Intent gosearch = new Intent(getContext(), HomeSearchActivity.class);
                startActivity(gosearch);
                break;


        }
    }
}
