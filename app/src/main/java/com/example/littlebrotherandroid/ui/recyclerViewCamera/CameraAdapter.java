package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;

import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraHolder> {

    private List<CameraModel> cameraModels;
    public boolean showAccept;
    public CameraAdapter(List<CameraModel> cameraModels, boolean showAccept){

        this.cameraModels=cameraModels;
        this.showAccept = showAccept;
    }

    @NonNull
    @Override
    public CameraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_holder_layout, parent, false);

        return new CameraHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CameraHolder holder, int position) {
        holder.bind(cameraModels.get(position), this);
    }




    @Override
    public int getItemCount() {
        return cameraModels.size();
    }
}
