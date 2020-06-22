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

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProximityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, true);
        String id = intent.getStringExtra("camera_id");
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), id);
        if (entering) {
            Log.d(getClass().getSimpleName(), "entering");
            Rest.getInstance().log.sendLog("Bearer "+ Auth.getInstance().firebaseKey, body, "entree");
        }
        else {
            Log.d(getClass().getSimpleName(), "exiting");
            Rest.getInstance().log.sendLog("Bearer "+ Auth.getInstance().firebaseKey, body, "sortie");
        }

    }
}