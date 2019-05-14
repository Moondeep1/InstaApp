package com.company.insta.instaapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by LENOVO on 4/29/2019.
 */

public class VolleyHandler  {

    private static VolleyHandler mVolleyHandler;
    private static Context mContext;
    private RequestQueue requestQueue;
    private VolleyHandler(Context mContext)
    {
        this.mContext=mContext;
        this.requestQueue=getRequestQueue();
    }
    public static synchronized VolleyHandler getInstance(Context context){
        if(mVolleyHandler==null)
        {
            mVolleyHandler = new VolleyHandler(context);
        }
        return mVolleyHandler;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());

        }
        return requestQueue;
    }
    public <T> void addRequestToQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}
