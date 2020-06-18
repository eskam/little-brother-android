package com.example.littlebrotherandroid.ui.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.Auth;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;
import static android.text.TextUtils.isEmpty;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }
    private FirebaseAuth mAuth;
    private EditText edit_text_email;
    private EditText edit_text_password;

    private Button signup;
    private TextView signin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sign_up_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        edit_text_email = root.findViewById(R.id.edit_text_email);
        edit_text_password = root.findViewById(R.id.edit_text_password);

        signin = root.findViewById(R.id.signin);
        signup = root.findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_sign_in);
            }
        });
        return root;
    }


    public void register() {
        String email = edit_text_email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        if (isEmpty(email) || isEmpty(password)) {
            Toast.makeText(getActivity(), "Please entre your email and address", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                Auth.getInstance().getToken(() ->
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.nav_little_brothers)
                );
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(getActivity(), "Registratation failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}