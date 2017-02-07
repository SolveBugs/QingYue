package com.chsj.qingyue.apiclient;

import java.io.Serializable;

/**
 * Created by zhenqiang on 2017/1/22.
 */

public class NetResponse<T> implements Serializable {
    private String result;
    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetResponse{" +
                "result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}
