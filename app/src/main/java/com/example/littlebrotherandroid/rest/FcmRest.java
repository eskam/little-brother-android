package com.example.littlebrotherandroid.rest;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FcmRest {

    @POST("/app/user")
    Call<String> sendToken(@Header("Authorization") String userkey, @Body String token);
}
