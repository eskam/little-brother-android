package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.CameraList;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class CameraHolder extends RecyclerView.ViewHolder {

    public CameraModel camera;

    public TextView name_camera;
    public TextView little_brother;
    public TextView big_brothers;
    public Button accepter;
    public Button refuser;
    public MapView minimap;
    public GoogleMap gmap;


    public CameraHolder(@NonNull View itemView) {
        super(itemView);

        name_camera = itemView.findViewById(R.id.name_camera);
        little_brother = itemView.findViewById(R.id.little_brother);
        big_brothers = itemView.findViewById(R.id.big_brother);
        accepter = itemView.findViewById(R.id.accept);
        refuser = itemView.findViewById(R.id.denied);
        minimap = itemView.findViewById(R.id.minimap);
    }

    public void bind(CameraModel cameraModel, CameraAdapter cameraAdapter){
        name_camera.setText(cameraModel.getName());
        little_brother.setText(cameraModel.getLittleBrother());
        big_brothers.setText(cameraModel.getBigBrother());

        refuser.setOnClickListener(e ->{
            Rest.getInstance().cameraRest.delete("Bearer " + Auth.getInstance().firebaseKey, cameraModel.getId());
            CameraList.getInstance().refreshLittle(cameraAdapter::notifyDataSetChanged);
            CameraList.getInstance().refreshBig(cameraAdapter::notifyDataSetChanged);

        });
        accepter.setOnClickListener(e ->{
            Rest.getInstance().cameraRest.accept("Bearer " + Auth.getInstance().firebaseKey, cameraModel.getId());
            CameraList.getInstance().refreshLittle(cameraAdapter::notifyDataSetChanged);
            CameraList.getInstance().refreshBig(cameraAdapter::notifyDataSetChanged);
        });
        minimap.onCreate(new Bundle());
        minimap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                gmap = mMap;

                LatLng marker = new LatLng(cameraModel.getLatitude(), cameraModel.getLongitude());
                gmap.addMarker(new MarkerOptions().position(marker).title(cameraModel.getName()));

                // For zooming automatically to the location of the marker
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
                minimap.onResume();
            }
        });
    }
}
