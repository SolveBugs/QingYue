package com.chsj.qingyue.sharedsdk;

import android.widget.RelativeLayout;

import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.AuthorizeAdapter;

public class CustomLoginUI extends AuthorizeAdapter {



    public void onCreate() {
//        System.out.println("> ShareSDKUIShell created!");
//        隐藏ShateSDK的Logo
        hideShareSDKLogo();
//获取标题栏控件
        TitleLayout llTitle = getTitleLayout();
        llTitle.getBtnRight().setText("helllo");

        RelativeLayout bodyView = getBodyView();
//        bodyView.getChildAt(0).setBackgroundColor(Color.BLACK);
//        getBodyView()

        //标题栏的文字修改
        llTitle.getTvTitle().setText("轻阅");
    }

    public void onDestroy() {
//        System.out.println("> ShareSDKUIShell will be destroyed.");
    }
}
