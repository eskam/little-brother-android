package com.example.littlebrotherandroid.data;


import android.app.PendingIntent;
import android.location.LocationManager;

import java.util.HashMap;
import java.util.Map;

public class DataMap {
    private DataMap(){}
    public Map<String, PendingIntent> pendingIntent = new HashMap<>();
    public LocationManager locationManager;
    private static DataMap instance = new DataMap();
    public static DataMap getInstance(){return instance;};
}
