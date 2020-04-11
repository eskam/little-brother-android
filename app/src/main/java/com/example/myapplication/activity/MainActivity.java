package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Navigation;
import com.example.myapplication.R;
import com.example.myapplication.listener.onclicklistener.LogOutOnClickListener;
import com.example.myapplication.models.CameraModel;
import com.example.myapplication.models.TestObject;
import com.example.myapplication.models.ZoneModel;
import com.example.myapplication.network.CameraNet;
import com.example.myapplication.network.GetTestObject;
import com.example.myapplication.network.RetrofitInstance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends Activity {
    private Navigation navigation;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private TextView raw_text;
    private Button logout;
    Retrofit retrofit;
    GetTestObject testobj;
    CameraNet cameraNet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = new Navigation();

        mAuth = FirebaseAuth.getInstance();

        raw_text = findViewById(R.id.raw_data);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new LogOutOnClickListener(this));

        retrofit = RetrofitInstance.getRetrofitInstance();
        testobj = retrofit.create(GetTestObject.class);
        cameraNet = retrofit.create(CameraNet.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseUser = mAuth.getCurrentUser();
        updateUI(firebaseUser);
        CameraModel cam = new CameraModel("moi", "lui", new ZoneModel(1.1, 1.2, 2.1, 2.2, 3.0));
        firebaseUser.getIdToken(true).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String idToken = task.getResult().getToken();
                Log.i("token", idToken);
                /*Call<CameraModel> camRequest = cameraNet.post("Bearer " + idToken, cam);
                camRequest.enqueue(new Callback<CameraModel>() {
                    @Override
                    public void onResponse(@NotNull Call<CameraModel> call, @NotNull Response<CameraModel> response) {
                        raw_text.setText(response.body().toString());
                    }
                    @Override
                    public void onFailure(@NotNull Call<CameraModel> call, @NotNull Throwable t) {
                        raw_text.setText("errror " + t.getMessage());
                    }
                });*/
                // Send token to your backend via HTTPS
                Call<TestObject> call = testobj.get("Bearer " + idToken);
                call.enqueue(new Callback<TestObject>() {
                    @Override
                    public void onResponse(@NotNull Call<TestObject> call, @NotNull Response<TestObject> response) {
                        raw_text.setText(response.body().getType());
                    }
                    @Override
                    public void onFailure(@NotNull Call<TestObject> call, @NotNull Throwable t) {
                        raw_text.setText(t.getMessage());
                    }
                });

            }
        });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            firebaseUser = currentUser;
            Toast.makeText(this, "Vous êtes déjà connecté",Toast.LENGTH_SHORT).show();
        } else {
            firebaseUser = null;
            Toast.makeText(this, "Merci de vous connecter",Toast.LENGTH_SHORT).show();
            navigation.navigateToSignIn(getApplicationContext(), this);
        }
    }

    public void logout() {
        mAuth.signOut();
        updateUI(mAuth.getCurrentUser());
    }

}
