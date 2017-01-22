package com.chsj.qingyue.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chsj.qingyue.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by zhenqiang on 2016/12/28.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private static final String TAG = "BaseActivity";

    public ToolBarHelper mToolBarHelper;
    public Toolbar toolbar;
    private boolean show;
    private boolean active, created;
    private SystemBarTintManager tintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        // create our manager instance after the content view is set
        tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.common_tint_bar_color);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        toolbar = mToolBarHelper.getToolBar();
        mToolBarHelper.getContentView().setFitsSystemWindows(true);
        setContentView(mToolBarHelper.getContentView());
        setSupportActionBar(toolbar);
        onCreateCustomToolBar(toolbar);
        hideBack(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideBack(boolean hide) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(!hide);
    }


    public boolean isShow() {
        return show;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isCreated() {
        return created;
    }

    @Override
    protected void onResume() {
        super.onResume();
        show = true;
        active = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        show = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    protected void onDestroy() {
        created = false;
        super.onDestroy();
    }
}
