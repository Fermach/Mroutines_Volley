package com.example.fermach.mroutines;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fermach on 18/01/2018.
 */

public class Pantalla_temporal extends Fragment {

    private View myView;

    public Pantalla_temporal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.pantalla_temporal, container, false);


        return myView;
    }




}
