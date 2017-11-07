package com.wetime.fanc.home.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.batterytest.fbaselib.main.BaseFragment;
import com.wetime.fanc.R;
import com.wetime.fanc.home.bean.SortPageBean;
import com.wetime.fanc.home.iviews.IGetSortView;
import com.wetime.fanc.home.presenter.GetSortPagePresenter;


public class SortFragment extends BaseFragment implements IGetSortView {


    private GetSortPagePresenter getSortPagePresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sort, null);
        getSortPagePresenter = new GetSortPagePresenter(this);
        return v;
    }

    @Override
    public void onGetSortPage(SortPageBean bean) {

    }
}
