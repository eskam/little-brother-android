package com.example.littlebrotherandroid.ui.littleBrothers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LittleBrothersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LittleBrothersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}