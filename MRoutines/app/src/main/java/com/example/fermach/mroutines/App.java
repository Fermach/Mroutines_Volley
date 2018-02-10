package com.example.fermach.mroutines;

import android.app.Application;
import android.content.Context;

/**
 *
 * Clase para optener el contexto actual de la aplicación
 * @author Fermach
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