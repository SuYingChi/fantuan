package com.wetime.fanc.my.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.lang.String;

import java.util.ArrayList;

/**
 * Created by zhoukang on 2018/1/29.
 */


public class MyFriendsPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mChannels;
    //    ArrayList<String> mChannels = new ArrayList<>();
    private FragmentManager fm;

    public MyFriendsPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, ArrayList<String> mChannels) {
        super(fm);
        this.fm = fm;
        this.mFragments = mFragments;
        this.mChannels = mChannels;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
////            super.destroyItem(container, position, object);
//    }
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        // 把 Object 强转为 View，然后将 view 从 ViewGroup 中清除
//        container.removeView((View) object);
//    }

//    public void setFragments(ArrayList<Fragment> fragments) {
//        if (this.mFragments != null) {
//            FragmentTransaction ft = fm.beginTransaction();
//            for (Fragment f : this.mFragments) {
//                ft.remove(f);
//            }
//            ft.commit();
//            ft = null;
//            fm.executePendingTransactions();
//        }
//        this.mFragments = fragments;
//        notifyDataSetChanged();
//    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void recreateItems(ArrayList<Fragment> mFragments, ArrayList<String> mChannels) {
//        if (this.mFragments != null) {
//            FragmentTransaction ft = fm.beginTransaction();
//            for (Fragment f : this.mFragments) {
//                ft.remove(f);
//            }
//            ft.commit();
//            fm.executePendingTransactions();
//        }


        this.mFragments = mFragments;
        this.mChannels = mChannels;
        notifyDataSetChanged();
    }

}
