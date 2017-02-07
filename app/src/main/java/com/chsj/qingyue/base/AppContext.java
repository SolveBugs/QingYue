package com.chsj.qingyue.base;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by zhenqiang on 2017/1/22.
 */

public class AppContext extends Application {

    private static AppContext sAppcontext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppcontext = this;
        ShareSDK.initSDK(this);
    }

    public static AppContext getsAppcontext() {
        return sAppcontext;
    }
}
