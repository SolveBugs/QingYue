package com.chsj.qingyue.base;

import android.content.Context;

import com.chsj.qingyue.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhenqiang on 2017/1/22.
 */

public class BaseApiClient {
    private static final String TAG = "BaseApiClient";

    private static Retrofit sRetrofit;
    private static OkHttpClient sOkHttpClient;

    static {
        Context context = AppContext.getsAppcontext();
        try {
            sOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_1)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return sRetrofit.create(serviceClass);
    }
}
