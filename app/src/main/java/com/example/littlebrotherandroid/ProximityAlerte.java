package com.example.littlebrotherandroid;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class ProximityAlerte extends AppCompatActivity {

    //Test localisation
    LocationManager locationManager;
    //définir deux variables pour la latitude et la longitude
    double latitude = 34.727518, longitude = 10.717547;
    //le Rayon en Radius
    float radius = 100;
    //Intent Action
    String ACTION_FILTER = "com.example.littlebrotherandroid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //l'enregistrement de notre BrodcastReceiver
        //registerReceiver(new ProximityReceiver(), new IntentFilter(ACTION_FILTER));

        //s'abonner au service de géolocalisation
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //s'abonner au mise à jour de la géolocalisation
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, (LocationListener) this);
            //Configurer l'Intent de Broadcast
            Intent i = new Intent(ACTION_FILTER);
            PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), -1, i, 0);
            //Configurer notre  addProximityAlert
            locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
        }
    }
}