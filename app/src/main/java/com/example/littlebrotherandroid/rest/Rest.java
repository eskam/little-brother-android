package com.example.littlebrotherandroid.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {
    public FcmRest fcm;
    public CameraRest cameraRest;
    public LogRest log;
    private Rest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe-west1-little-brother-55371.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cameraRest = retrofit.create(CameraRest.class);
        fcm = retrofit.create(FcmRest.class);
    }
    private static Rest instance = new Rest();
    public static Rest getInstance(){
        return instance;
    }

}
