package com.example.littlebrotherandroid.ui.littleBrothers;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.littlebrotherandroid.ProximityReceiver;
import com.example.littlebrotherandroid.data.DataCamera;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.data.DataMap;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LittleBrothersFragment extends Fragment {

    private LittleBrothersViewModel galleryViewModel;
    private CameraAdapter cameraAdapter;

    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1;               // in meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1;                  // in Milliseconds
    private static final long POINT_RADIUS = 15;                                     // in meters
    private static final long PROX_ALERT_EXPIRATION = -1;

    private static final String PROX_ALERT_INTENT = "com.example.littlebrotherandroid.ProximityReceiver";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LittleBrothersViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();


        View root = inflater.inflate(R.layout.fragment_little_brothers, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        cameraAdapter = new CameraAdapter(DataCamera.getInstance().litBro, true);
        recyclerView.setAdapter(cameraAdapter);

        FloatingActionButton fab_add_camera = root.findViewById(R.id.fab_add_camera);
        fab_add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_add_camera);
            }
        });

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataCamera.getInstance().refreshLittle(() -> {
                    cameraAdapter.notifyDataSetChanged();
                    initProximityAlert();
                });
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        DataCamera.getInstance().refreshLittle(() -> {
            cameraAdapter.notifyDataSetChanged();
            initProximityAlert();
        });
        return root;
    }

    public void initProximityAlert(){
        for (CameraModel cameraModel: DataCamera.getInstance().litBro) {
            if(cameraModel.getAccept()) {
                DataMap.getInstance().pendingIntent.put(cameraModel.getId(), addProximityAlert(cameraModel));
                Log.i("proximity alert on", cameraModel.getName());
            }
        }
    }

    private PendingIntent addProximityAlert(CameraModel cameraModel) {
        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra("camera_id", cameraModel.getId());
        PendingIntent proximityIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        intent.putExtra("ID", ProximityReceiver.indent);
        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        requireActivity().registerReceiver(new ProximityReceiver(), filter);
        return proximityIntent;
    }
}