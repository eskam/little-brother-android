package com.example.littlebrotherandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;


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
}