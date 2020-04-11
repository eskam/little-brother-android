package com.example.myapplication.network;

import com.example.myapplication.models.CameraModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CameraNet {
    @POST("/app/camera")
    Call<CameraModel> post(@Header("Authorization") String token, @Body CameraModel model);
}
