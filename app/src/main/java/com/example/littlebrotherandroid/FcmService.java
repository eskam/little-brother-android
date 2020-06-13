package com.example.littlebrotherandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.littlebrotherandroid.rest.Rest;
import com.google.firebase.messaging.FirebaseMessagingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("FCM", "Refreshed token: " + token);
        Call<Boolean> call = Rest.getInstance().fcm.sendToken(Auth.getInstance().firebaseKey, token);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.i("response rest ok", response.message());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.i("response rest error", "error!!!!!");
            }
        });
    }
}
