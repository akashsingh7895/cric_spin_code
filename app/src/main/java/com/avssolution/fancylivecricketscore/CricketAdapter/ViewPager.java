package com.avssolution.fancylivecricketscore.CricketAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPager extends FragmentStatePagerAdapter {
    public List<Fragment> fragments;
    int tabCount;

    public ViewPager(FragmentManager fragmentManager, int i, List<Fragment> list) {
        super(fragmentManager);
        this.tabCount = i;
        this.fragments = list;
    }

    @Override 
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override 
    public int getCount() {
        return this.fragments.size();
    }
}
