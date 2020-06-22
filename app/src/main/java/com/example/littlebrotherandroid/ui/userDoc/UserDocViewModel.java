package com.example.littlebrotherandroid.ui.userDoc;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserDocViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserDocViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

}