package com.example.myapplication.listener.onclicklistener;

import android.app.Activity;
import android.view.View;

import com.example.myapplication.activity.MainActivity;

public class CameraOnClickListener implements View.OnClickListener {

    private MainActivity mainActivity;

    public CameraOnClickListener(Activity activity) {
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onClick(View v) { mainActivity.cameras(); }

}
