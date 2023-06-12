package com.qingbai.idylls.shilu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private final ArrayList<ContentFragment> fragments;

    ViewPageAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<ContentFragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }
    //返回当前位置的fragment
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    //fragment的总数
    @Override
    public int getCount() {
        return fragments.size();
    }
    //得到页面标题

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
