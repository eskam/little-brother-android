package com.example.littlebrotherandroid.rest;

import com.example.littlebrotherandroid.model.CameraModel;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SendCameraRest {
    @POST("/camera")
    Call<Boolean> sendCamera(@Header("Authorization") String userkey, @Query("camera") CameraModel camera);
}
