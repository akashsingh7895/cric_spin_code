package com.gsbusiness.fancylivecricketscore.CricketAdapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Different extends FragmentStatePagerAdapter {
    private String mDataList;
    private final List<Fragment> mFragmentList = new ArrayList();
    private final List<String> mFragmentTitleList = new ArrayList();

    public Different(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override 
    public Fragment getItem(int i) {
        return this.mFragmentList.get(i);
    }

    @Override 
    public int getCount() {
        return this.mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String str, String str2) {
        this.mFragmentList.add(fragment);
        this.mFragmentTitleList.add(str);
        this.mDataList = str2;
        Log.e(" addFragment ", " addFragment ");
    }

    @Override 
    public CharSequence getPageTitle(int i) {
        return this.mFragmentTitleList.get(i);
    }
}
