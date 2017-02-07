package com.chsj.qingyue.apiclient;

import com.chsj.qingyue.base.BaseApiClient;
import com.chsj.qingyue.bean.BaseEntity;
import com.chsj.qingyue.bean.HpEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhenqiang on 2017/1/22.
 */

public class Apiclient extends BaseApiClient {
    private static final String TAG = "Apiclient";


    private interface MainService {
        @GET("getHp_N")
        Call<HpEntity> getHpEntity(@Query("strDate") String strData, @Query("strRow") String id);
    }

    protected static MainService service = getService(MainService.class);

    public static Call<HpEntity> getHpEntity(String strData, String id) {
        return service.getHpEntity(strData, id);
    }

}
