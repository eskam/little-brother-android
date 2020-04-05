package com.example.myapplication.listener.onclicklistener;

import android.app.Activity;
import android.view.View;

import com.example.myapplication.activity.SignInActivity;

public class SignInOnClickListener implements View.OnClickListener {
    SignInActivity signInActivity;
    public SignInOnClickListener(Activity activity) {
        signInActivity = (SignInActivity) activity;
    }
    @Override
    public void onClick(View v) {
        signInActivity.login();
    }
}
