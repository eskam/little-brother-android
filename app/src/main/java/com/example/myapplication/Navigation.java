package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.SignInActivity;
import com.example.myapplication.activity.SignUpActivity;

public class Navigation {

    public void navigateToMain(Context context, Activity activity) {
        activity.finish();
        context.startActivity(new Intent(activity, MainActivity.class));
    }

    public void navigateToSignUp(Context context, Activity activity) {
        activity.finish();
        context.startActivity(new Intent(activity, SignUpActivity.class));
    }

    public void navigateToSignIn(Context context, Activity activity) {
        activity.finish();
        context.startActivity(new Intent(activity, SignInActivity.class));
    }
}
