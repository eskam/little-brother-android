package com.example.littlebrotherandroid.ui.userDoc;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.ui.bigBrothers.BigBrothersViewModel;

public class UserDocFragment extends Fragment {

    private UserDocViewModel userDocViewModel;

    public static UserDocFragment newInstance() {
        return new UserDocFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("userdoc", "userdoc");

        userDocViewModel =
                ViewModelProviders.of(this).get(UserDocViewModel.class);
        View root = inflater.inflate(R.layout.user_doc_fragment, container, false);

        return root;
    }

}