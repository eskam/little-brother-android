package com.example.littlebrotherandroid.rest;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Rest {
    public FcmRest fcm;
    public SendCameraRest scr;
    private Rest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe-west1-little-brother-55371.cloudfunctions.net/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        fcm = retrofit.create(FcmRest.class);
        scr = retrofit.create(SendCameraRest.class);
    }
    private static Rest instance = new Rest();
    public static Rest getInstance(){
        return instance;
    }

}
