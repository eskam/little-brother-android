package com.example.littlebrotherandroid.ui.userDoc;

import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.littlebrotherandroid.R;
import com.example.littlebrotherandroid.ui.bigBrothers.BigBrothersViewModel;

public class UserDocFragment extends Fragment {

    private UserDocViewModel userDocViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        userDocViewModel =
                ViewModelProviders.of(this).get(UserDocViewModel.class);
        View root = inflater.inflate(R.layout.user_doc_fragment, container, false);

        TextView userDoc = (TextView) root.findViewById(R.id.userDoc);
        userDoc.setText(Html.fromHtml(getString(R.string.html)));

        return root;
    }

}