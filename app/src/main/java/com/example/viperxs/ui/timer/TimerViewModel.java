package com.example.viperxs.ui.timer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TimerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Таймер");
    }

    public LiveData<String> getText() {
        return mText;
    }
}