package com.example.littlebrotherandroid.ui.addcamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.model.RestString;
import com.example.littlebrotherandroid.rest.Rest;
import com.example.littlebrotherandroid.ui.map.MyMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCameraFragment extends Fragment {

    MapView mMapView;

    private GoogleMap googleMap;
    private SupportMapFragment fragment;
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
        mMapView = root.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                mMapView.onResume();
            }
        });
        fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        return root;
    }



    public boolean addCamera() {
        boolean statusRequest=true;
        String name = nameCamera.getText().toString().trim();
        String little = littleBrother.getText().toString().trim();
        String big = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        if (name.isEmpty() || little.isEmpty()) {
            Toast.makeText(getActivity(), "Veuillez remplir les champs", Toast.LENGTH_LONG).show();
            return false;
        }
        CameraModel camera= new CameraModel(name,little, big,1.0,2.0,3.0);
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