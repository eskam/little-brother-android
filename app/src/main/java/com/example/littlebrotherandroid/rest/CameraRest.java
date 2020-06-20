package com.example.littlebrotherandroid.rest;

import com.example.littlebrotherandroid.model.CameraModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CameraRest {
    @POST("/app/camera")
    Call<ResponseBody> send(@Header("Authorization") String userkey, @Body CameraModel cameraModel);

    @GET("/app/camera/big")
    Call<Map<String,CameraModel>> getBig(@Header("Authorization") String userkey);

    @GET("/app/camera/little")
    Call<Map<String, CameraModel>> getLittle(@Header("Authorization") String userkey);

    @DELETE("/app/camera/{id}")
    Call<ResponseBody> delete(@Header("Authorization") String userkey,@Path("id") String id);
}
