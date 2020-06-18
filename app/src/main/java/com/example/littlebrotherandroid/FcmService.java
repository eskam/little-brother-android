package com.example.littlebrotherandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.littlebrotherandroid.model.RestString;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.firebase.messaging.FirebaseMessagingService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FcmService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("FCM", "Refreshed token: " + token);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), token);
        Call<ResponseBody> call = Rest.getInstance().fcm.sendToken(Auth.getInstance().firebaseKey, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("response rest ok", response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("response rest error", "error!!!!!");
            }
        });
    }
}
