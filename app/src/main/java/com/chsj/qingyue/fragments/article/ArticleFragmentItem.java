package com.chsj.qingyue.fragments.article;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;

public class ArticleFragmentItem extends Fragment implements ArticleTask.ArticleCompleteListener, CompoundButton.OnCheckedChangeListener {
    //    Fragment中当前页数
    private int page;
    private int praiseNum;
    private ScrollView scrollView;
    private ImageView loadImage;
    //    post请求地址中的参数
    private String paramers = "strDate=null&strRow=%d";
    private CheckBox praiseBtn;
    AnimationDrawable animation;
    private TextView authorNameTV,
            markeTimeTV,
            contTitleTV,
            contentTV,
            weixinTV,
            authorIntroduce,
            authorNameW_article_fragment_item,
            introduce_article_fragment_item;
    private ArticleEntity articleEntity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        page = getArguments().getInt("page");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment_item, container, false);
        initView(view);
        //开启异步任务请求资源
        ArticleTask task = (ArticleTask) new ArticleTask(this)
                .execute(Constants.ARTICLE_URL, String.format(paramers, page));

        return view;
    }


    //初始化Fragment上的Ui控件，
    private void initView(View view) {
        loadImage = (ImageView) view.findViewById(R.id.article_progress_loading);
        animation = (AnimationDrawable) loadImage.getBackground();
        animation.start();
        authorNameTV = (TextView) view.findViewById(R.id.authorName_article_fragment_item);
        markeTimeTV = (TextView) view.findViewById(R.id.markeTime_article_fragment_item);
        contTitleTV = (TextView) view.findViewById(R.id.contTitle_article_fragment_item);
        contentTV = (TextView) view.findViewById(R.id.content_article_fragment_item);
        praiseBtn = (CheckBox) view.findViewById(R.id.praiseNumber_article_fragment_item);
        praiseBtn.setOnCheckedChangeListener(this);
        weixinTV = (TextView) view.findViewById(R.id.weixin_article_fragment_item);
        authorIntroduce = (TextView) view.findViewById(R.id.authorIntroduce_article_fragment_item);
        authorNameW_article_fragment_item = (TextView) view.findViewById(R.id.authorNameW_article_fragment_item);
        introduce_article_fragment_item = (TextView) view.findViewById(R.id.introduce_article_fragment_item);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView_article_fragment_item);
        scrollView.setVisibility(View.INVISIBLE);
    }

    //    异步任务返回接口，字符的分割和UI的显示
    @Override
    public void resultComplete(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
        animation.stop();
        loadImage.setVisibility(View.INVISIBLE);
        authorNameTV.setText(articleEntity.getStrContAuthor());
        String time = articleEntity.getStrContMarketTime();

        String times[] = time.split("-");
        if (times != null) {
            int month = Integer.parseInt(times[1]);
            String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            time = months[month - 1] + " " + times[2] + "," + times[0];
        }

        markeTimeTV.setText(time);
        contTitleTV.setText(articleEntity.getStrContTitle());

        String content = articleEntity.getStrContent();
        String str[] = content.split("<b>");
        contentTV.setText(str[0].replace("<br>", "\n"));

        praiseBtn.setText(articleEntity.getStrPraiseNumber());
        praiseNum = Integer.parseInt(articleEntity.getStrPraiseNumber());
        weixinTV.setText(articleEntity.getSWbN());
        authorIntroduce.setText(articleEntity.getStrContAuthorIntroduce());
        authorNameW_article_fragment_item.setText(articleEntity.getStrContAuthor());
        introduce_article_fragment_item.setText(str[1].substring(0, str[1].indexOf("。") + 1));
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            praiseNum++;
        } else {
            praiseNum--;
        }
        praiseBtn.setText("" + praiseNum);
    }
}
