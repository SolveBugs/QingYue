package com.chsj.qingyue.fragments.question;

import android.os.AsyncTask;

import com.chsj.qingyue.tools.HttpTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;



/**
 * 定义数据的回调接口  接受  json数据
 *
 */
public class QuestionAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private CallbackInterface callbackInterface;

    public QuestionAsyncTask(CallbackInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject ret= null;

        if (params[0] != null){

            byte[] bytes = HttpTools.doGet(params[0]);

            if (bytes != null) {
                try {

                    JSONObject jsonObject = new JSONObject(new String(bytes,"utf-8"));

                    ret = jsonObject;

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        callbackInterface.callResultJson(jsonObject);
    }

    public interface CallbackInterface{

        void callResultJson(JSONObject jsonObject);

    }

}
