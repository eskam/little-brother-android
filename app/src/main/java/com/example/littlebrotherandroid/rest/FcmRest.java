package com.example.littlebrotherandroid.rest;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FcmRest {

    @POST("/app/user")
    Call<ResponseBody> sendToken(@Header("Authorization") String userkey, @Body RequestBody token);
}
