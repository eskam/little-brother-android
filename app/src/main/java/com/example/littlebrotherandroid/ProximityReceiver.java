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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;


public class ProximityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, true);
        if (entering) {
            Log.d(getClass().getSimpleName(), "entering");

        }
        else {
            Log.d(getClass().getSimpleName(), "exiting");
        }

    }


    /*
    @Override
    public void onReceive(Context context, Intent intent) {
        // Key for determining whether user is leaving or entering
        String key = LocationManager.KEY_PROXIMITY_ENTERING;

        //Gives whether the user is entering or leaving in boolean form
        boolean state = intent.getBooleanExtra(key, false);

        if(state){
            // Call the Notification Service or anything else that you would like to do here
            Log.i("Entrer", "Welcome to my Area");
            Toast.makeText(context, "Welcome to my Area", Toast.LENGTH_SHORT).show();
        }else {
            //Other custom Notification
            Log.i("Sortie", "Thank you for visiting my Area,come back again !!");
            Toast.makeText(context, "Thank you for visiting my Area,come back again !!" + state, Toast.LENGTH_SHORT).show();
        }
    }*/
}