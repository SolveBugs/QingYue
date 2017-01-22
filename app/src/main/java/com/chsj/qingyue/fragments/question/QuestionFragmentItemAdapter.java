package com.chsj.qingyue.fragments.question;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class QuestionFragmentItemAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;

    public QuestionFragmentItemAdapter(FragmentManager childFragmentManager, List<Fragment> fragments) {
        super(childFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;

        if (fragments.size() != 0){
             ret = fragments.size();
        }

        return ret;
    }


}
