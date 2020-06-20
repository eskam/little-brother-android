package com.example.littlebrotherandroid;

import android.location.LocationManager;
import android.util.Log;

import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraList {
    public List<CameraModel> bigBro = new ArrayList<>();
    public List<CameraModel> litBro = new ArrayList<>();

    private static CameraList instance = new CameraList();
    private CameraList(){
       refreshBig(null);
       refreshLittle(null);
    }
    public void refreshBig(Action action) {
        Call<Map<String, CameraModel>> callBig = Rest.getInstance().cameraRest.getBig("Bearer " + Auth.getInstance().firebaseKey);

        callBig.enqueue(new Callback<Map<String, CameraModel>>() {
            @Override
            public void onResponse(Call<Map<String, CameraModel>> call, Response<Map<String, CameraModel>> response) {
                Log.i("bigbro", response.body().toString());
                Map<String, CameraModel> bigMap = response.body();
                if (bigMap != null){
                    bigBro.clear();
                    bigBro.addAll(new ArrayList<CameraModel>(bigMap.values()));
                    if (action != null)
                        action.action();
                }
            }

            @Override
            public void onFailure(Call<Map<String, CameraModel>> call, Throwable t) {
                Log.i("bigbro", "error" + t.getMessage());
            }
        });
    }
    public void refreshLittle(Action action){
        Call<Map<String, CameraModel>> callLittle = Rest.getInstance().cameraRest.getLittle("Bearer " + Auth.getInstance().firebaseKey);

        callLittle.enqueue(new Callback<Map<String, CameraModel>>() {
            @Override
            public void onResponse(Call<Map<String, CameraModel>> call, Response<Map<String, CameraModel>> response) {
                Map<String, CameraModel> litMap = response.body();
                if (litMap != null) {
                    litBro.clear();
                    litBro.addAll(new ArrayList<CameraModel>(litMap.values()));
                    if (action != null) {
                        action.action();
                        litBro.forEach(x -> Log.i("refresh", x.getName()));
                    }
                }
            }
            @Override
            public void onFailure(Call<Map<String, CameraModel>> call, Throwable t) {
            }
        });
    }
    public static CameraList getInstance(){
        return instance;
    }
    public interface Action{
        public void action();
    }
}
