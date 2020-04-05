package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Navigation;
import com.example.myapplication.R;
import com.example.myapplication.listener.onclicklistener.NotAlreadyRegisteredOnClickListener;
import com.example.myapplication.listener.onclicklistener.SignInOnClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

import static android.content.ContentValues.TAG;
import static android.text.TextUtils.isEmpty;

public class SignInActivity extends Activity {
    private Navigation navigation;

    private EditText edit_text_email;
    private EditText edit_text_password;

    private Button signin;
    private TextView signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        navigation = new Navigation();

        mAuth = FirebaseAuth.getInstance();

        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(new SignInOnClickListener(this));
        signup.setOnClickListener(new NotAlreadyRegisteredOnClickListener(this));
    }

    public void login() {
        String email = edit_text_email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(this,"Please entre your email and address", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            navigation.navigateToMain(getApplicationContext(),SignInActivity.this);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Registratation failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public void navigateToSignUp() {
        navigation.navigateToSignUp(getApplicationContext(),this);
    }
}
