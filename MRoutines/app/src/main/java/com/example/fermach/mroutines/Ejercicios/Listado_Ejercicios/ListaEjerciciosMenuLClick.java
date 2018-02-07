package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fermach.mroutines.Ejercicios.Editar_Ejercicios.EditarEjercicioVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Editar_Rutinas.EditarRutinaVista;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fermach on 25/01/2018.
 */

public class ListaEjerciciosMenuLClick extends DialogFragment {

    private Ejercicio ejercicio;
    private ListaEjerciciosContract.Presenter presenter;
    private final String EJERCICIO ="EJERCICIO";


    public static ListaEjerciciosMenuLClick newInstance( Ejercicio ejercicio) {

        Bundle args = new Bundle();

        ListaEjerciciosMenuLClick fragment = new ListaEjerciciosMenuLClick();

        args.putSerializable("EJERCICIO", ejercicio);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter= new ListaEjerciciosPresenter();


        ejercicio =(Ejercicio) getArguments()
                .getSerializable("EJERCICIO");



        Log.i("Ejercicio en Detalle: ", ejercicio.toString());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      final View v = inflater.inflate(R.layout.menu_edicion_borrado_ejercicio, container, false);


        Button borrar_ejercicio= v.findViewById(R.id.btn_borrar_ejercicio);
        Button editar_ejercicio= v.findViewById(R.id.btn_editar_ejercicio);


        borrar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                myBuild.setMessage("¿Estás seguro de que deseas borrar este ejercicio?");
                myBuild.setTitle("Borrar Ejercicio");
                myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.borrarEjercicio(ejercicio.getId());
                        dialogInterface.cancel();
                        getDialog().dismiss();
                    }
                });
                myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                myBuild.show();
            }
        });

        editar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getDialog().dismiss();
                Bundle bundle = new Bundle();
                bundle.putSerializable(EJERCICIO, ejercicio);
                Fragment toFragment = new EditarEjercicioVista();
                toFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, EJERCICIO)
                        .addToBackStack(EJERCICIO).commit();
            }
        });

      return v;
    }


}
