package com.chsj.qingyue;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chsj.qingyue.adapter.MyFragmentPagerAdapter;
import com.chsj.qingyue.base.BaseActivity;
import com.chsj.qingyue.fragments.music.PlaySongService;
import com.chsj.qingyue.tools.NetWorkUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

    private static final String TAG = "MainActivity";
    private boolean isNetWorkAvalable;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ImageView ivLoginQQ, ivLoginWechat, ivLoginWeibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideBack(true);
        initViews();
        initDrawer();
        isNetWorkAvalable = NetWorkUtils.isConnect(this);
        if (!isNetWorkAvalable) {//当前没有网络，进入 网络设置界面
            //TODO 进行网络设置
            Intent intent = new Intent(MainActivity.this, SettingNetActivity.class);
            startActivity(intent);
        }
    }

    private void initDrawer() {
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        ivLoginQQ = (ImageView) mDrawerLayout.findViewById(R.id.iv_login_qq);
        ivLoginQQ.setOnClickListener(this);
        ivLoginWechat = (ImageView) mDrawerLayout.findViewById(R.id.iv_login_wechat);
        ivLoginWechat.setOnClickListener(this);
        ivLoginWeibo = (ImageView) mDrawerLayout.findViewById(R.id.iv_login_weibo);
        ivLoginWeibo.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_qq:
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.authorize();
                break;
            case R.id.iv_login_wechat:
                break;
            case R.id.iv_login_weibo:
                break;
            default:
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.i(TAG, "onComplete: " + platform.getDb().getUserIcon() + "===" + platform.getDb().getUserName() + "===" +
                platform.getDb().getUserGender() + "====" + platform.getDb().getPlatformVersion());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.i(TAG, "onError: " + throwable.toString());
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.i(TAG, "onCancel: ");
    }
}
