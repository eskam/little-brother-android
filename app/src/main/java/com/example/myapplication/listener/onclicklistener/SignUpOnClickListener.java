package com.example.myapplication.listener.onclicklistener;

import android.app.Activity;
import android.view.View;

import com.example.myapplication.activity.SignUpActivity;

public class SignUpOnClickListener implements View.OnClickListener {
    SignUpActivity signUpActivity;
    public SignUpOnClickListener(Activity activity) {
        signUpActivity = (SignUpActivity) activity;
    }
    @Override
    public void onClick(View v) {
        signUpActivity.register();
    }
}
