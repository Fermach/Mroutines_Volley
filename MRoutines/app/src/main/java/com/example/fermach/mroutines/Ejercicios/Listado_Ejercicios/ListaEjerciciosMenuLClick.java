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
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasVista;

import java.io.Serializable;
import java.util.List;

/**
 * Fragmento dialogo con un pequeño menú para borrar o editar rutinas
 *
 * @author Fermach
 * @version 1.0.
 *
 */

public class ListaEjerciciosMenuLClick extends DialogFragment {

    private Ejercicio ejercicio;
    private String rutina_nombre;
    private ListaEjerciciosContract.Presenter presenter;
    private final String EJERCICIO ="EJERCICIO";
    private final String RUTINA ="RUTINA";
    private final String ID_EJERCICIO_A_ELIMINAR="ID_EJERCICIO_A_ELIMINAR";


    public static ListaEjerciciosMenuLClick newInstance(String rutina, Ejercicio ejercicio) {

        Bundle args = new Bundle();

        ListaEjerciciosMenuLClick fragment = new ListaEjerciciosMenuLClick();

        //mandamos la rutina y el ejercicio recibido en la instancia

        args.putSerializable("EJERCICIO", ejercicio);
        args.putSerializable("RUTINA", rutina);



        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter= new ListaEjerciciosPresenter();

        //al crearse el fragmento recibimos la rutina y el ejercicio de la instancia
        ejercicio =(Ejercicio) getArguments()
                .getSerializable("EJERCICIO");
        rutina_nombre=(String)getArguments().getSerializable("RUTINA");


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

                //Al pulsar en el boton de borrar ejercicio se crea un dialogo que pregunte
                //si estamos seguros de su borrado
                AlertDialog.Builder myBuild = new AlertDialog.Builder(view.getContext());
                myBuild.setMessage("¿Estás seguro de que deseas borrar este ejercicio?");
                myBuild.setTitle("Borrar Ejercicio");
                myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //si elegimos Si se cierra el dialogo y el fragmento
                        //y realizamos un transaccion al fragmento de la lista de rutinas pasandole
                        //el nombre de la rutina y el ejercicio que queremos eliminar una rutina

                        dialogInterface.cancel();
                        getDialog().dismiss();
                        Bundle args_eli = new Bundle();
                        args_eli.putSerializable(RUTINA, rutina_nombre);
                        args_eli.putSerializable(ID_EJERCICIO_A_ELIMINAR,ejercicio.getId());
                        Fragment toFragment = new ListaEjerciciosVista();
                        toFragment.setArguments(args_eli);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_main, toFragment)
                                .addToBackStack(RUTINA)
                                .addToBackStack(ID_EJERCICIO_A_ELIMINAR)
                                .commit();

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

                //Si seleccionamos "Editar rutina" nos lleva al fragmento de editar ejercicio pasandole el nombre del ejercicio a editar

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
