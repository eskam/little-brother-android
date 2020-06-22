package com.example.littlebrotherandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProximityReceiver extends BroadcastReceiver {

    public static HashSet<Integer> set = new HashSet<>();
    public static Integer indent = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Integer i = intent.getIntExtra("ID", 0);
        if (!set.contains(i)){
            set.add(i);
            indent ++;
        }
        else
            return;
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        boolean entering = intent.getBooleanExtra(key, true);
        String id = intent.getStringExtra("camera_id");
        Log.i("camera notification", id);

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), id);
        if (entering) {
            Log.d(getClass().getSimpleName(), "entering");
            Call<ResponseBody> call= Rest.getInstance().log.sendLog("Bearer "+ Auth.getInstance().firebaseKey, body, true);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("log sent", "in");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
        else {
            Log.d(getClass().getSimpleName(), "exiting");
            Call<ResponseBody> call=Rest.getInstance().log.sendLog("Bearer "+ Auth.getInstance().firebaseKey, body, false);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("log sent", "out");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

    }
}