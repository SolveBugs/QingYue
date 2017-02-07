package com.chsj.qingyue.fragments.homepage;


import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.apiclient.Apiclient;
import com.chsj.qingyue.bean.BaseEntity;
import com.chsj.qingyue.tools.ImageUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "HomePageFragment";
    //图片网址
    private static String imgUrl = null;

    private RecyclerView recyclerView;
    private List<HpEntity> datas;
    private MyRecyclerAdapter adapter;


    //点击某张图片后出来的预览图
    private static ImageView imgShow;
    //占位的帧布局
    private static FrameLayout frameLayout;
    private ImageView imageView;
    private AnimationDrawable anim;

    private static boolean hasLoadData;

    public HomePageFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new LinkedList<HpEntity>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.fragment_home_page, container, false);
        imageView = (ImageView) ret.findViewById(R.id.fragment_home_progress_loading);

        recyclerView = (RecyclerView) ret.findViewById(R.id.fragment_homepage_recycler_view);
        imgShow = (ImageView) ret.findViewById(R.id.fragment_home_page_showbig_img);
        frameLayout = (FrameLayout) ret.findViewById(R.id.fragment_home_page_framelayout);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        imgShow.setOnClickListener(this);
        imgShow.setOnLongClickListener(this);

        return ret;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (!hasLoadData) {
            anim = (AnimationDrawable) imageView.getBackground();
            anim.start();
            datas.clear();
            for (int i = 1; i <= 10; i++) {
                Call<com.chsj.qingyue.bean.HpEntity> responseBodyCall = Apiclient.getHpEntity("null", String.valueOf(i));
                responseBodyCall.enqueue(new Callback<com.chsj.qingyue.bean.HpEntity>() {
                    @Override
                    public void onResponse(Call<com.chsj.qingyue.bean.HpEntity> call, Response<com.chsj.qingyue.bean.HpEntity> response) {
                        HpEntity hpEntity = new HpEntity();
                        com.chsj.qingyue.bean.HpEntity.HpEntityBean bean = response.body().getHpEntity();
                        hpEntity.setAuthor(bean.getStrAuthor());
                        hpEntity.setStrContent(bean.getStrContent());
                        hpEntity.setStrPn(bean.getStrPn());
                        hpEntity.setStrThumbnaiUrl(bean.getStrThumbnailUrl());

                        adapter.notifyDataSetChanged();
                        anim.stop();
                        imageView.setVisibility(View.GONE);
                        datas.add(hpEntity);
                    }

                    @Override
                    public void onFailure(Call<com.chsj.qingyue.bean.HpEntity> call, Throwable t) {
                        Log.i(TAG, "onFailure: " + t.toString());
                    }
                });
                adapter.notifyDataSetChanged();
                hasLoadData = true;
            }
        }
    }

    /**
     * 点击预览图，预览图消失
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        frameLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 长按预览图，进行保存操作
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {

        View view = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.dialog_view, null);


        TextView txtLoad = (TextView) view.findViewById(R.id.dialog_view_load_img);
        TextView txtCancle = (TextView) view.findViewById(R.id.dialog_view_cancle);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final AlertDialog dialog = builder.setTitle(getString(R.string.more_controller))
                .setView(view)
                .create();

        dialog.show();
        /**
         * 取消长按操作
         */
        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /**
         * 保存图片到本地
         */
        txtLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = imgShow.getDrawable();
                BitmapDrawable d = (BitmapDrawable) drawable;
                Bitmap bitmap = d.getBitmap();

                ImageUtils.saveImageToGallery(getActivity().getApplicationContext(), bitmap);
                Toast.makeText(getActivity(), getString(R.string.save_sucess), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        return true;
    }

    /**
     * RecyclerView的适配器
     */
    private class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<HpEntity> datas;

        public MyRecyclerAdapter(List<HpEntity> datas) {
            this.datas = datas;
        }


        @Override
        public int getItemCount() {
            int ret = 0;
            if (datas != null) {
                ret = datas.size();
            }
            return ret;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder ret = null;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity().getApplicationContext());
            View view = layoutInflater.inflate(R.layout.fragment_homepage_recyclerview_item, parent, false);
            ret = new ViewHolder(view);
            return ret;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            HpEntity hpEntity = datas.get(position);

            imgUrl = hpEntity.getStrThumbnaiUrl();

            //判断图片是否已经缓存，，，
            Bitmap bitmap = ImageUtils.getImg("qingyue1" + imgUrl.replace("jpg", "png"));
            if (bitmap != null) {//从sdk中获取图片
                Log.d("sdk", "load from sdk----homd");
                holder.imageView.setImageBitmap(bitmap);
            } else {//从网络下载图片
                Log.d("sdk", "load from net----homd");
                Picasso.with(getActivity().getApplicationContext()).load(hpEntity.getStrThumbnaiUrl()).into(holder.imageView);

                Drawable drawable = holder.imageView.getDrawable();
                if (drawable != null) {
                    BitmapDrawable d = (BitmapDrawable) drawable;
                    Bitmap bitmap1 = d.getBitmap();
                    try {
                        ImageUtils.saveImg("qingyue1" + hpEntity.getStrThumbnaiUrl().replace("jpg", "png"), bitmap1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            holder.txtContent.setText(hpEntity.getStrContent());
        }
    }


    /**
     * ViewHolder
     */
    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView txtContent;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_item_icon);
            txtContent = (TextView) itemView.findViewById(R.id.fragment_homepage_item_content);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.fragment_homepage_item_icon:
                    frameLayout.setVisibility(View.VISIBLE);
                    imgShow.setImageDrawable(imageView.getDrawable());
                    Animation mAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.imgshowscal);
                    mAnimation.setFillAfter(true);
                    imgShow.startAnimation(mAnimation);
                    break;
            }
        }
    }
}
