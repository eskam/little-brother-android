package com.example.littlebrotherandroid.ui.signin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.Auth;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;
import static android.text.TextUtils.isEmpty;

public class SignInFragment extends Fragment {

    private SignInViewModel signViewModel;
    private EditText edit_text_email;
    private EditText edit_text_password;

    private Button signin;
    private TextView signup;

    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        View root = inflater.inflate(R.layout.sign_in_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        edit_text_email = root.findViewById(R.id.edit_text_email);
        edit_text_password = root.findViewById(R.id.edit_text_password);

        signin = root.findViewById(R.id.signin);
        signup = root.findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_sign_up);
            }
        });

        testConnection();

        return root;
    }

    public void testConnection() {
        if (mAuth.getCurrentUser() != null) {
            Auth.getInstance().getToken( () ->
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_little_brothers));
        }
    }

    public void login() {
        String email = edit_text_email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(getActivity(),"Please entre your email and address", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                Auth.getInstance().getToken( () ->
                        Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_little_brothers)
                );

            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(getActivity(), "Registratation failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}