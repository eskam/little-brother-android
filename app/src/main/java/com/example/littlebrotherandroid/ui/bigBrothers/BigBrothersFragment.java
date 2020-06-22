package com.example.littlebrotherandroid.ui.bigBrothers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.littlebrotherandroid.data.DataCamera;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;

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


        cameraAdapter = new CameraAdapter(DataCamera.getInstance().bigBro, false);
        recyclerView.setAdapter(cameraAdapter);

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataCamera.getInstance().refreshBig(() -> {
                    cameraAdapter.notifyDataSetChanged();
                });
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        DataCamera.getInstance().refreshBig(() -> {
            cameraAdapter.notifyDataSetChanged();
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DataCamera.getInstance().refreshBig(() -> {cameraAdapter.notifyDataSetChanged();});

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            DataCamera.getInstance().refreshBig(() -> {cameraAdapter.notifyDataSetChanged();});

    }
}