package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Navigation;
import com.example.myapplication.R;
import com.example.myapplication.listener.onclicklistener.LogOutOnClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {
    private Navigation navigation;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = new Navigation();

        mAuth = FirebaseAuth.getInstance();

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new LogOutOnClickListener(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            firebaseUser = currentUser;
            Toast.makeText(this, "Vous êtes déjà connecté",Toast.LENGTH_SHORT).show();
        } else {
            firebaseUser = null;
            Toast.makeText(this, "Merci de vous connecter",Toast.LENGTH_SHORT).show();
            navigation.navigateToSignIn(getApplicationContext(), this);
        }
    }

    public void logout() {
        mAuth.signOut();
        updateUI(mAuth.getCurrentUser());
    }
}
