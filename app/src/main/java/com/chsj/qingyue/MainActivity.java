package com.chsj.qingyue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.chsj.qingyue.adapter.MyFragmentPagerAdapter;
import com.chsj.qingyue.base.BaseActivity;
import com.chsj.qingyue.fragments.music.PlaySongService;
import com.chsj.qingyue.tools.NetWorkUtils;

public class MainActivity extends BaseActivity {

    private boolean isNetWorkAvalable;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideBack(true);
        initViews();
        isNetWorkAvalable = NetWorkUtils.isConnect(this);
        if (!isNetWorkAvalable) {//当前没有网络，进入 网络设置界面
            //TODO 进行网络设置
            Intent intent = new Intent(MainActivity.this, SettingNetActivity.class);
            startActivity(intent);
        }
    }

    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mToolBarHelper.setTitle("轻阅");
        Constants.isExit = false;
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PlaySongService.class);
        Constants.isExit = true;
        stopService(intent);

        super.onDestroy();
    }

}
