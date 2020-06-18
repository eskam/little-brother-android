package com.example.littlebrotherandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.littlebrotherandroid.model.RestString;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.firebase.messaging.FirebaseMessagingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("FCM", "Refreshed token: " + token);
        Call<String> call = Rest.getInstance().fcm.sendToken(Auth.getInstance().firebaseKey, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("response rest ok", response.message());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("response rest error", "error!!!!!");
            }
        });
    }
}
