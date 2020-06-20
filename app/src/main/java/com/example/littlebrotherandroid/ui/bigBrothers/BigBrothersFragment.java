package com.example.littlebrotherandroid.ui.bigBrothers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.CameraList;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BigBrothersFragment extends Fragment {

    private BigBrothersViewModel slideshowViewModel;
    private CameraAdapter cameraAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(BigBrothersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_big_brothers, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.rc_big);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        cameraAdapter = new CameraAdapter(CameraList.getInstance().bigBro);
        recyclerView.setAdapter(cameraAdapter);

        CameraList.getInstance().refreshBig(() -> {cameraAdapter.notifyDataSetChanged();});
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        CameraList.getInstance().refreshBig(() -> {cameraAdapter.notifyDataSetChanged();});

    }
}