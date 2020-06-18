package com.example.littlebrotherandroid.rest;

import com.example.littlebrotherandroid.model.CameraModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CameraRest {
    @POST("/app/camera")
    Call<ResponseBody> send(@Header("Authorization") String userkey, @Body CameraModel cameraModel);

    @GET("/app/camera")
    Call<CameraModel> get(@Header("Authorization") String userkey, @Query("brother") RequestBody brother);
}
