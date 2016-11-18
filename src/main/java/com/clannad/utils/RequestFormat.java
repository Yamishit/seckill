package com.clannad.utils;

import com.google.gson.Gson;

/**
 * Created by F_ck on 2016/11/18.
 */
public class RequestFormat {

    private static Gson gson;

    private static RequestFormat requestFormat;

    public static RequestFormat getInstance(){
        if(requestFormat == null){
            synchronized (RequestFormat.class){
                if(requestFormat == null){
                    requestFormat = new RequestFormat();
                }
            }
        }
        return requestFormat;
    }

    public RequestFormat() {
        gson = new Gson();
    }

    public static Object getRequestObj(String requestJson,Object requestBean){
        return gson.fromJson(requestJson,requestBean.getClass());
    }
}
