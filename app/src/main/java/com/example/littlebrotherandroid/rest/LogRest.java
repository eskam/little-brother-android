package com.example.littlebrotherandroid.rest;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LogRest {

    @POST("/app/camera/log")
    Call<ResponseBody> sendLog(@Header("Authorization") String userkey, @Body RequestBody log);
}