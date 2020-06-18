package com.example.littlebrotherandroid.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {
    public FcmRest fcm;
    private Rest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe-west1-little-brother-55371.cloudfunctions.net/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fcm = retrofit.create(FcmRest.class);
    }
    private static Rest instance = new Rest();
    public static Rest getInstance(){
        return instance;
    }

}