package com.example.littlebrotherandroid.rest;

import com.example.littlebrotherandroid.model.FcmResp;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FcmRest {

    @POST("/user")
    Call<Boolean> sendToken(@Header("Authorization") String userkey, @Query("token") String token);
}
