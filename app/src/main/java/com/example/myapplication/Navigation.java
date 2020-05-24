package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.activity.CamerasActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.SignInActivity;
import com.example.myapplication.activity.SignUpActivity;

public class Navigation {

    public void navigateToMain(Context context, Activity activity) {
        activity.finish();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void navigateToSignUp(Context context, Activity activity) {
        activity.finish();
        Intent intent = new Intent(activity, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void navigateToSignIn(Context context, Activity activity) {
        activity.finish();
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void navigateToCameras(Context context, Activity activity) {
        activity.finish();
        Intent intent = new Intent(activity, CamerasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
