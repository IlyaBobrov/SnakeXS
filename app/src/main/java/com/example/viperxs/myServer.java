package com.example.viperxs;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class myServer extends Lifecycle implements LifecycleObserver {


    //TODO: реализовать работу с сервером
    @OnLifecycleEvent(Event.ON_START)
    public void connect() {

    }

    @OnLifecycleEvent(Event.ON_STOP)
    public void disconnect() {

    }


    @Override
    public void addObserver(@NonNull LifecycleObserver observer) {

    }

    @Override
    public void removeObserver(@NonNull LifecycleObserver observer) {

    }

    @NonNull
    @Override
    public State getCurrentState() {
        return null;
    }
}
