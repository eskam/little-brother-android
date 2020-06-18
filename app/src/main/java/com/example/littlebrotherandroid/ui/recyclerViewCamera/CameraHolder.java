package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;


public class CameraHolder extends RecyclerView.ViewHolder {

    public CameraModel camera;

    public TextView name_camera;
    public TextView little_brother;
    public TextView big_brothers;


    public CameraHolder(@NonNull View itemView) {
        super(itemView);

        name_camera = itemView.findViewById(R.id.name_camera);
        little_brother = itemView.findViewById(R.id.little_brother);
        big_brothers = itemView.findViewById(R.id.big_brother);
    }

    public void bind(CameraModel cameraModel){
        name_camera.setText(cameraModel.nameCamera);
        little_brother.setText(cameraModel.littleBrother);
        big_brothers.setText(cameraModel.bigBrother);
    }

}
