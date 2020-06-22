package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.ProximityReceiver;
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
import java.time.DateTimeException;
import java.util.Date;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CameraHolder extends RecyclerView.ViewHolder {

    private static final long POINT_RADIUS = 15;                                     // in meters
    private static final long PROX_ALERT_EXPIRATION = -1;
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
        if (cameraAdapter.showAccept){
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Log.i("long click", "long");
                    if (DataMap.getInstance().pendingIntent.get(cameraModel.getId()) != null) {
                        Log.i("long click", cameraModel.getId());
                        Intent intent = new Intent(PROX_ALERT_INTENT);
                        intent.putExtra("ID", ProximityReceiver.indent);
                        intent.putExtra("camera_id", cameraModel.getId());
                        intent.putExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);//added by addproximityalert
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ProximityReceiver.indent, intent, PendingIntent.FLAG_ONE_SHOT);
                        try {
                            pendingIntent.send(context, 0, intent);
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            });
        }

        refuser.setOnClickListener(e ->{
            Log.i("refuser", cameraModel.getName());
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
                    Log.i("refuser response ok", cameraModel.getName());
                    DataCamera.getInstance().refreshLittle(cameraAdapter::notifyDataSetChanged);
                    DataCamera.getInstance().refreshBig(cameraAdapter::notifyDataSetChanged);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("refuser response error", cameraModel.getName());

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
                    DataMap.getInstance().pendingIntent.put(cameraModel.getId(), addProximityAlert(cameraModel));
                    Log.i("proximity alert on", cameraModel.getName());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
                private PendingIntent addProximityAlert(CameraModel cameraModel) {
                    Intent intent = new Intent(PROX_ALERT_INTENT);
                    intent.putExtra("camera_id", cameraModel.getId());
                    PendingIntent proximityIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return null;
                    }
                    DataMap.getInstance().locationManager.addProximityAlert(
                            cameraModel.getLatitude(), // the latitude of the central point of the alert region
                            cameraModel.getLongitude(), // the longitude of the central point of the alert region
                            POINT_RADIUS, // the radius of the central point of the alert region, in meters
                            PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                            proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
                    );

                    //intent ajouter variable ID
                    intent.putExtra(PROX_ALERT_INTENT, cameraModel.getId());

                    IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
                    context.registerReceiver(new ProximityReceiver(), filter);
                    Toast.makeText(context, "id" + cameraModel.getId(), Toast.LENGTH_SHORT).show();
                    return proximityIntent;
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
