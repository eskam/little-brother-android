package com.example.littlebrotherandroid;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.android.material.navigation.NavigationView;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1;               // in meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1;                  // in Milliseconds
    private static final long POINT_RADIUS = 30;                                     // in meters
    private static final long PROX_ALERT_EXPIRATION = -1;
    private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";

    private static final String PROX_ALERT_INTENT = "King Hendo Tools";

    private LocationManager lm;
    private MyLocationListener mylistener;

    private IntentFilter intFilter;

    private AppBarConfiguration mAppBarConfiguration;
/*
    //Test localisation
    LocationManager locationManager;
    //définir deux variables pour la latitude et la longitude
    double latitude = 48.8180786, longitude = 2.2121773;
    //le Rayon en Radius
    float radius = 100;
    //Intent Action
    String ACTION_FILTER = "com.example.littlebrotherandroid";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_little_brothers, R.id.nav_big_brothers, R.id.nav_add_camera, R.id.nav_log_out)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


// Get the location Manager  (code from http://examples.javacodegeeks.com/android/core/location/android-location-based-services-example/)
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATE,
                MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                new MyLocationListener());

        // the last known location of the provider
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Toast.makeText(this, "Lat" + location.getLatitude() + "Lon" + location.getLongitude(), Toast.LENGTH_SHORT).show();
        addProximityAlert(48.8180786, 2.2121773);


        mylistener = new MyLocationListener();

        if (location != null) {
            mylistener.onLocationChanged(location);
        } else {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        // location updates: at least 15 meters and 1 seconds change
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 15, mylistener);
        Toast.makeText(this, "Lat" + location + "Lon" + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

    private void addProximityAlert(double latitude, double longitude) {
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.addProximityAlert(
                latitude, // the latitude of the central point of the alert region
                longitude, // the longitude of the central point of the alert region
                POINT_RADIUS, // the radius of the central point of the alert region, in meters
                PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration
                proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
        );


/*
        lm.addProximityAlert(48.8180786, 2.2121773, 30, 10000, proximityIntent);
        lm.addProximityAlert(38.196, -84.8674, 6, 10000, proximityIntent);
        lm.addProximityAlert(38.19518, -84.8666, 6, 10000, proximityIntent);
        lm.addProximityAlert(38.19472, -84.8661, 6, 10000, proximityIntent);
        lm.addProximityAlert(38.19388, -84.8649, 6, 10000, proximityIntent);
        lm.addProximityAlert(38.19353, -84.8647, 6, 10000, proximityIntent);
        lm.addProximityAlert(38.19373, -84.8661, 6, 10000, proximityIntent);
*/
        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityReceiver(), filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 15, mylistener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(mylistener);
    }


        /*
        IntentFilter filter = new IntentFilter(ACTION_FILTER);
        registerReceiver(new ProximityReceiver(), filter);
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
            Toast.makeText(this, "Added new proximity alert event...", Toast.LENGTH_SHORT).show();
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}