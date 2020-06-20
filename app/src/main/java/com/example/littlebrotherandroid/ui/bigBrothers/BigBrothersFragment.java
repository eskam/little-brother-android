package com.example.littlebrotherandroid.ui.bigBrothers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.littlebrotherandroid.R;

public class BigBrothersFragment extends Fragment {

    private BigBrothersViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(BigBrothersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_big_brothers, container, false);

        return root;
    }
}