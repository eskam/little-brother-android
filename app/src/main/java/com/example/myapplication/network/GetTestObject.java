package com.example.myapplication.network;

import com.example.myapplication.models.TestObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetTestObject {
    @GET("/app/testobject")
    Call<TestObject> get(@Header("Authorization") String userkey);
}
