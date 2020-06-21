package com.example.littlebrotherandroid;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.littlebrotherandroid.rest.Rest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Timer;
import java.util.TimerTask;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Auth{
    private static Auth instance = new Auth();
    public static Auth getInstance(){return instance;}
    public String fcmKey = null;
    public String firebaseKey = null;

    private void getFcmToken(ActionAfter action) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task ->  {
            if (!task.isSuccessful()) {
                Log.w("error", "getInstanceId failed", task.getException());
                return;
            }
            fcmKey = task.getResult().getToken();
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), fcmKey);
            Call<ResponseBody> call = Rest.getInstance().fcm.sendToken("Bearer " + firebaseKey, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                    Log.i("code", "str" + response.code());
                    Log.i("response rest ok", response.message());
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.i("response rest error", t.getMessage());
                }
            });
            Log.d("fcm_token", fcmKey);
            if (action != null)
                action.then();
        });
    }

    public void getToken(ActionAfter action){
        autoRefresh(action);
    }
    public interface ActionAfter{
        void then();
    }
    public void autoRefresh(ActionAfter action){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseUser.getIdToken(true).addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        firebaseKey = task.getResult().getToken();
                    Log.i("firebase_token", firebaseKey);
                    getFcmToken(action);
                });
            }
        });
    }
}
