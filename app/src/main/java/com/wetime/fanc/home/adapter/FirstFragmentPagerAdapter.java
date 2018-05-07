package com.wetime.fanc.home.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/3.
 */

public class FirstFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;

    public FirstFragmentPagerAdapter(FragmentManager fm,
                                    List<Fragment> list_fragment) {
        super(fm);
        this.listFragment = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
    }
}
