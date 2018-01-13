package com.example.fermach.mroutines;

import android.app.Application;
import android.content.Context;

/**
 * Created by Fermach on 13/01/2018.
 */

public class App extends Application {
    private static Context context;
    public static Context getAppContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}