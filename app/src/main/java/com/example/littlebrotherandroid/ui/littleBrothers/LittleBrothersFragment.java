package com.example.littlebrotherandroid.ui.littleBrothers;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.MainActivity;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.ui.map.Map;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LittleBrothersFragment extends Fragment {

    private LittleBrothersViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LittleBrothersViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();


        View root = inflater.inflate(R.layout.fragment_little_brothers, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<CameraModel> cameraModelList = new ArrayList<CameraModel>();
        cameraModelList.add(new CameraModel("Ecole","Jean","Alfred",1.0,2.,3.0));
        cameraModelList.add(new CameraModel("Gymnase","Corentin","Alfred",1.0,2.,3.0));
        cameraModelList.add(new CameraModel("Dijon","Dealer","Tchetchen",1.0,2.,3.0));

        recyclerView.setAdapter(new CameraAdapter(cameraModelList));

        FloatingActionButton fab_add_camera = root.findViewById(R.id.fab_add_camera);
        fab_add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_add_camera);
            }
        });

        return root;
    }
}