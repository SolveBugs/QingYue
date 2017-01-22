package com.chsj.qingyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chsj.qingyue.fragments.article.ArticleFragment;
import com.chsj.qingyue.fragments.homepage.HomePageFragment;
import com.chsj.qingyue.fragments.music.SongFragment;
import com.chsj.qingyue.fragments.person.PersonFragment;
import com.chsj.qingyue.fragments.question.QuestionFragment;

/**
 * Created by zhenqiang on 2017/1/22.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"首页", "文章", "问题", "音乐", "我的"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new HomePageFragment();
        } else if (position == 2) {
            return new ArticleFragment();
        } else if (position == 3) {
            return new QuestionFragment();
        } else if (position == 4) {
            return new SongFragment();
        } else if (position == 5) {
            return new PersonFragment();
        }
        return new HomePageFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
