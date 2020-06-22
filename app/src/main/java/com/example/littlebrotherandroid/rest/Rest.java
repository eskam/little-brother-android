package com.example.littlebrotherandroid.rest;


import com.example.littlebrotherandroid.Auth;

import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {
    public FcmRest fcm;
    public CameraRest camera;
    public LogRest log;
    private Rest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe-west1-little-brother-55371.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        camera = retrofit.create(CameraRest.class);
        fcm = retrofit.create(FcmRest.class);
        log = retrofit.create(LogRest.class);
    }
    private static Rest instance = new Rest();
    public static Rest getInstance(){
        return instance;
    }

}
