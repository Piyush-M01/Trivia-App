package com.example.trivia.controller;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    private static AppController instance;
    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;
    private static Context ctx;

    public static synchronized AppController getInstance()
    {
        return instance;
    }
    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
