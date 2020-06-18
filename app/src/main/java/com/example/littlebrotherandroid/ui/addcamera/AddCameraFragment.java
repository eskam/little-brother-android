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
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.little_brother);
            }
        });

        return root;
    }

    public boolean addCamera() {
        return true;
    }

}