package com.chsj.qingyue;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.chsj.qingyue.adapter.MyFragmentPagerAdapter;
import com.chsj.qingyue.fragments.article.ArticleEntity;
import com.chsj.qingyue.fragments.music.PlaySongService;
import com.chsj.qingyue.fragments.music.SongDetails;
import com.chsj.qingyue.fragments.question.QuestionEntity;
import com.chsj.qingyue.tools.NetWorkUtils;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton first, article, question, things, personal;

    private ImageView share;
    private boolean isNetWorkAvalable;

    //本地广播管理器对象：
    private LocalBroadcastManager localBroadcastManager;
    //广播接受者：
    private ShareBroadcastReceiver shareBroadcastReceiver;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        isNetWorkAvalable = NetWorkUtils.isConnect(this);
        if (!isNetWorkAvalable) {//当前没有网络，进入 网络设置界面
            //TODO 进行网络设置
            Intent intent = new Intent(MainActivity.this, SettingNetActivity.class);
            startActivity(intent);
        }

        //实例化广播接受者：
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        shareBroadcastReceiver = new ShareBroadcastReceiver();
        //注册本地广播接受者：
        localBroadcastManager.registerReceiver(shareBroadcastReceiver, new IntentFilter(Constants.GET_DATA_TO_SHARE));
    }

    private void initViews() {

        share = (ImageView) findViewById(R.id.title_share);
        share.setOnClickListener(this);


        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    //初始化单击事件：
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //点击分享事件：
            case R.id.title_share:

                if (shareBroadcastReceiver.data != null) {
                    if (shareBroadcastReceiver.data instanceof ArticleEntity) {
                        //分享   文章的数据


                    } else if (shareBroadcastReceiver.data instanceof QuestionEntity) {
                        //分享问题页的数据

                        //判断数据类型：
                        QuestionEntity questionEntity = (QuestionEntity) shareBroadcastReceiver.data;

                        ShareSDK.initSDK(this);
                        OnekeyShare oks = new OnekeyShare();
                        //关闭sso授权
                        oks.disableSSOWhenAuthorize();

                        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                        oks.setTitle(getString(R.string.share));
                        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                        oks.setTitleUrl(questionEntity.getSWebLk());
                        // text是分享文本，所有平台都需要这个字段
                        oks.setText(questionEntity.getStrQuestionTitle() + questionEntity.getSWebLk());
                        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                        oks.setImageUrl("http://img4.imgtn.bdimg.com/it/u=281071708,647194968&fm=21&gp=0.jpg");

                        // url仅在微信（包括好友和朋友圈）中使用
                        oks.setUrl(questionEntity.getSWebLk());
//                        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                        oks.setComment();
                        // site是分享此内容的网站名称，仅在QQ空间使用
                        oks.setSite(getString(R.string.app_name));
                        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                        oks.setSiteUrl(questionEntity.getSWebLk());

                        // 启动分享GUI
                        oks.show(this);

                    } else if (shareBroadcastReceiver.data instanceof SongDetails) {
                        //分享歌曲信息

                        //分享问题页的数据

                        //判断数据类型：
                        SongDetails songDetails = (SongDetails) shareBroadcastReceiver.data;

                        ShareSDK.initSDK(this);
                        OnekeyShare oks = new OnekeyShare();
                        //关闭sso授权
                        oks.disableSSOWhenAuthorize();

                        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                        oks.setTitle(getString(R.string.share));
                        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                        oks.setTitleUrl(songDetails.getSongLink());
                        // text是分享文本，所有平台都需要这个字段
                        oks.setText(songDetails.getSongName() + " " + songDetails.getSongLink());
                        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                        oks.setImageUrl("http://img4.imgtn.bdimg.com/it/u=281071708,647194968&fm=21&gp=0.jpg");


                        // url仅在微信（包括好友和朋友圈）中使用
                        oks.setUrl(songDetails.getSongLink());
//                        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                        oks.setComment();
                        // site是分享此内容的网站名称，仅在QQ空间使用
                        oks.setSite(getString(R.string.app_name));
                        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                        oks.setSiteUrl(songDetails.getSongLink());

                        // 启动分享GUI
                        oks.show(this);

                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.now_no_data_to_share), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Constants.isExit = false;
    }


    @Override
    protected void onDestroy() {
        //取消注册  广播
        localBroadcastManager.unregisterReceiver(shareBroadcastReceiver);

        Intent intent = new Intent(this, PlaySongService.class);
        Constants.isExit = true;
        stopService(intent);

        super.onDestroy();
    }


    //分享广播接受者
    final class ShareBroadcastReceiver extends BroadcastReceiver {
        private Object data;//传入的数据：

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            data = bundle.get(Constants.DATA_TO_EXTRA);
        }
    }
}
