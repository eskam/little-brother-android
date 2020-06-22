package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.data.DataCamera;
import com.example.littlebrotherandroid.data.DataMap;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CameraHolder extends RecyclerView.ViewHolder {


    public View itemView;
    public TextView name_camera;
    public TextView little_brother;
    public TextView big_brothers;
    public ImageButton accepter;
    public ImageButton refuser;
    public MapView minimap;
    public GoogleMap gmap;
    private static final String PROX_ALERT_INTENT = "com.example.littlebrotherandroid.ProximityReceiver";



    public CameraHolder(@NonNull View itemView) {
        super(itemView);

        name_camera = itemView.findViewById(R.id.name_camera);
        little_brother = itemView.findViewById(R.id.little_brother);
        big_brothers = itemView.findViewById(R.id.big_brother);
        accepter = itemView.findViewById(R.id.accept);
        refuser = itemView.findViewById(R.id.denied);
        minimap = itemView.findViewById(R.id.minimap);
        this.itemView = itemView;
    }

    public void bind(CameraModel cameraModel, CameraAdapter cameraAdapter, Context context){
        name_camera.setText(cameraModel.getName());
        little_brother.setText(cameraModel.getLittleBrother());
        big_brothers.setText(cameraModel.getBigBrother());

        if(cameraModel.getAccept() || !cameraAdapter.showAccept)
            accepter.setVisibility(View.GONE);

        if (cameraAdapter.showAccept && cameraModel.getAccept()){
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(PROX_ALERT_INTENT);
                    intent.putExtra("camera_id", cameraModel.getId());
                    intent.putExtra(LocationManager.KEY_PROXIMITY_ENTERING, cameraModel.getId());//added by addproximityalert
                    try {
                        DataMap.getInstance().pendingIntent.get(cameraModel.getId()).send(context,0, intent);
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        }

        refuser.setOnClickListener(e ->{
            if (cameraAdapter.showAccept) {
                PendingIntent pendingIntent = DataMap.getInstance().pendingIntent.get(cameraModel.getId());
                if (pendingIntent != null) {
                    DataMap.getInstance().locationManager.removeProximityAlert(pendingIntent);
                }

            }
            Call<ResponseBody> call = Rest.getInstance().camera.delete("Bearer " + Auth.getInstance().firebaseKey, cameraModel.getId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    try {
                        Log.i("response deleted", response.body().string());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    DataCamera.getInstance().refreshLittle(cameraAdapter::notifyDataSetChanged);
                    DataCamera.getInstance().refreshBig(cameraAdapter::notifyDataSetChanged);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });


        });
        accepter.setOnClickListener(e ->{
            Call<ResponseBody> call = Rest.getInstance().camera.accept("Bearer " + Auth.getInstance().firebaseKey, cameraModel.getId());
            call.enqueue(new Callback<ResponseBody>(){

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    DataCamera.getInstance().refreshLittle(cameraAdapter::notifyDataSetChanged);
                    DataCamera.getInstance().refreshBig(cameraAdapter::notifyDataSetChanged);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });
        minimap.onCreate(new Bundle());
        minimap.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                gmap = mMap;
                gmap.getUiSettings().setMapToolbarEnabled(false);

                LatLng marker = new LatLng(cameraModel.getLatitude(), cameraModel.getLongitude());
                gmap.addMarker(new MarkerOptions().position(marker).title(cameraModel.getName()));

                // For zooming automatically to the location of the marker
                gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
                minimap.onResume();

            }
        });
    }
}
