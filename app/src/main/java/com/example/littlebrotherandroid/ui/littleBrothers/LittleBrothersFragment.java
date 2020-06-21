package com.example.littlebrotherandroid.ui.littleBrothers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.CameraList;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LittleBrothersFragment extends Fragment {

    private LittleBrothersViewModel galleryViewModel;
    private CameraAdapter cameraAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LittleBrothersViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();


        View root = inflater.inflate(R.layout.fragment_little_brothers, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        cameraAdapter = new CameraAdapter(CameraList.getInstance().litBro);
        recyclerView.setAdapter(cameraAdapter);

        FloatingActionButton fab_add_camera = root.findViewById(R.id.fab_add_camera);
        fab_add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_add_camera);
            }
        });
        CameraList.getInstance().refreshLittle(() -> {cameraAdapter.notifyDataSetChanged();});

        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        CameraList.getInstance().refreshLittle(() -> {cameraAdapter.notifyDataSetChanged();});
    }
}