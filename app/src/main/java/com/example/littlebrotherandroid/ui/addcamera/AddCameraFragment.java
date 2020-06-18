package com.example.littlebrotherandroid.ui.addcamera;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.model.RestString;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCameraFragment extends Fragment {

    private AddCameraViewModel mViewModel;

    private EditText littleBrother;
    private EditText nameCamera;

    public static AddCameraFragment newInstance() {
        return new AddCameraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(AddCameraViewModel.class);
        View root = inflater.inflate(R.layout.add_camera_fragment, container, false);

        littleBrother = root.findViewById(R.id.edittext_littlebrother_mail);
        nameCamera = root.findViewById(R.id.edittext_name_camera);

        Button buttonAddCamera = root.findViewById(R.id.button_add_camera);
        buttonAddCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addCamera())
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_little_brothers);
            }
        });

        return root;
    }

    public boolean addCamera() {
        boolean statusRequest=true;
        Log.i("AddCameraFragment", nameCamera.getText().toString().trim() + "FirebaseToken" + littleBrother.getText().toString().trim());
        CameraModel camera= new CameraModel(nameCamera.getText().toString().trim(),littleBrother.getText().toString().trim(), FirebaseAuth.getInstance().getCurrentUser().getEmail(),1.0,2.0,3.0);
        Call<ResponseBody> call = Rest.getInstance().cameraRest.send("Bearer "+Auth.getInstance().firebaseKey,camera);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i("response rest ok", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w("response rest error", "error!!!!!",t);
                //statusRequest=false;
            }
        });

        return statusRequest;
    }

}