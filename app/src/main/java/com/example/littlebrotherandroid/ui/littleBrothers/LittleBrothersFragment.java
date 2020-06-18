package com.example.littlebrotherandroid.ui.littleBrothers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.ui.recyclerViewCamera.CameraAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class LittleBrothersFragment extends Fragment {

    private LittleBrothersViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LittleBrothersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_little_brothers, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<CameraModel> cameraModelList = new ArrayList<CameraModel>();
        cameraModelList.add(new CameraModel("Ecole","Jean","Alfred"));
        cameraModelList.add(new CameraModel("Gymnase","Corentin","Alfred"));
        cameraModelList.add(new CameraModel("Dijon","Dealer","Tchetchen"));

        recyclerView.setAdapter(new CameraAdapter(cameraModelList));

        return root;
    }
}