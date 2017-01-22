package com.chsj.qingyue;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chsj.qingyue.base.BaseActivity;

public class RegardActivity extends BaseActivity implements View.OnClickListener {

    ImageView backIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regard_activity);
        backIMG = (ImageView) findViewById(R.id.regard_back);
        backIMG.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v != null) {
            int id = v.getId();
            switch (id) {
//                回退按键，退出当前Activity
                case R.id.regard_back:
                    finish();
                    break;
            }
        }
    }
}
