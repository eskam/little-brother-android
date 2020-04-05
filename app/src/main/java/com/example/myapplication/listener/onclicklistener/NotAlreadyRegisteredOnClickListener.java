package com.example.myapplication.listener.onclicklistener;

import android.app.Activity;
import android.view.View;

import com.example.myapplication.activity.SignInActivity;

public class NotAlreadyRegisteredOnClickListener implements View.OnClickListener {
    SignInActivity signInActivity;
    public NotAlreadyRegisteredOnClickListener(Activity activity) {
        signInActivity = (SignInActivity) activity;
    }
    @Override
    public void onClick(View v) {
        signInActivity.navigateToSignUp();
    }
}
