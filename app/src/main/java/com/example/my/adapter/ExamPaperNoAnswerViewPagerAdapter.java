package com.example.my.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Describe
 * Created by hxc on 2017/9/20.
 */

public class ExamPaperNoAnswerViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public ExamPaperNoAnswerViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

}
