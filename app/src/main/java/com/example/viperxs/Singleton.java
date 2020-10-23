package com.example.viperxs;

import android.util.Log;

public class Singleton {

    public static final Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public void someMethod() {
        Log.i("Log", "I am a some Method");
    }
}
