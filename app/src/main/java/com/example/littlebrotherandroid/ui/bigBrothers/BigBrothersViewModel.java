package com.example.littlebrotherandroid.ui.bigBrothers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BigBrothersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BigBrothersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}