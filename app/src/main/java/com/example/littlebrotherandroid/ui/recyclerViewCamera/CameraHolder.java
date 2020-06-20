package com.example.littlebrotherandroid.ui.recyclerViewCamera;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.littlebrotherandroid.Auth;
import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.model.CameraModel;
import com.example.littlebrotherandroid.rest.Rest;
import com.google.android.gms.maps.MapView;


public class CameraHolder extends RecyclerView.ViewHolder {

    public CameraModel camera;

    public TextView name_camera;
    public TextView little_brother;
    public TextView big_brothers;
    public Button accepter;
    public Button refuser;
    public MapView minimap;


    public CameraHolder(@NonNull View itemView) {
        super(itemView);

        name_camera = itemView.findViewById(R.id.name_camera);
        little_brother = itemView.findViewById(R.id.little_brother);
        big_brothers = itemView.findViewById(R.id.big_brother);
        accepter = itemView.findViewById(R.id.accept);
        refuser = itemView.findViewById(R.id.denied);
        minimap = itemView.findViewById(R.id.minimap);
    }

    public void bind(CameraModel cameraModel){
        name_camera.setText(cameraModel.getName());
        little_brother.setText(cameraModel.getLittleBrother());
        big_brothers.setText(cameraModel.getBigBrother());

        refuser.setOnClickListener(e ->
                Rest.getInstance().cameraRest.delete("Bearer" + Auth.getInstance().firebaseKey, cameraModel.getId()));
    }

}
