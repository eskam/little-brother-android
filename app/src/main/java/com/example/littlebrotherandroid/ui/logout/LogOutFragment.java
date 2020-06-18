package com.example.littlebrotherandroid.ui.logout;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littlebrotherandroid.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutFragment extends Fragment {

    private LogOutViewModel mViewModel;

    public static LogOutFragment newInstance() {
        return new LogOutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(LogOutViewModel.class);
        View root = inflater.inflate(R.layout.log_out_fragment, container, false);

        if (logout())
            Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.nav_sign_in);

        return root;
    }

    public boolean logout() {
        FirebaseAuth.getInstance().signOut();
        return true;
    }

}