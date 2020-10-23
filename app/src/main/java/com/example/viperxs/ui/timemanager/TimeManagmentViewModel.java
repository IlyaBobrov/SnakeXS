package com.example.viperxs.ui.timemanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeManagmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TimeManagmentViewModel() {
        mText = new MediatorLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

}

