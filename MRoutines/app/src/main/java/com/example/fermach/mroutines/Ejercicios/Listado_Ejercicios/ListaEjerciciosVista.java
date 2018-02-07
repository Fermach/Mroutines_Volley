package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fermach.mroutines.Ejercicios.Crear_Ejercicios.CrearEjercicioVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.R;

import java.util.List;


/**
 * Created by Fermach on 18/01/2018.
 */

public class ListaEjerciciosVista extends Fragment implements ListaEjerciciosContract.View{

    private ListaEjerciciosContract.Presenter presenter;
    private ListaEjerciciosAdapter ejerciciosAdapter;
    private ListView list_ejercicios;
    private TextView num_ejercicios;
    private View myView;
    private TextView rutina;
    private FloatingActionButton fab_ejercicios;
    private Fragment fragment;
    private Ejercicio ejercicio;
    private final String EJERCICIO="EJERCICIO";
    private final String RUTINA_NOMBRE="RUTINA_NOMBRE";
    private String rutina_nombre;

    public ListaEjerciciosVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lista_ejercicios, container, false);

        fragment = new ListaEjerciciosVista();

        Bundle args = getArguments();
        rutina_nombre =(String) args
                .getSerializable("RUTINA");

        inicializarVistas();
        activarControladores();

        presenter=new ListaEjerciciosPresenter(this);
        presenter.cargaEjercicios(rutina_nombre);

        return myView;
    }


    public void inicializarVistas(){
        list_ejercicios=myView.findViewById(R.id.list_ejercicios);
        num_ejercicios=myView.findViewById(R.id.num_Ejercicios_lista);
        fab_ejercicios=myView.findViewById(R.id.fab_ejercicios);
        rutina=myView.findViewById(R.id.rutina_enEjercicios);
        rutina.setText(rutina_nombre);
    }

    public void activarControladores() {

        fab_ejercicios.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CrearEjercicioVista();
                Bundle args = new Bundle();
                args.putSerializable(RUTINA_NOMBRE, rutina_nombre);
                fragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, fragment, RUTINA_NOMBRE)
                        .addToBackStack(RUTINA_NOMBRE).commit();
            }
        });

    }


    @Override
    public void poblarListaEjercicios(List<Ejercicio> ejercicios){
         ejerciciosAdapter= new ListaEjerciciosAdapter(myView.getContext(), ejercicios);
         list_ejercicios.setAdapter(ejerciciosAdapter);
    }

    @Override
    public void activarListaClickable(final List<Ejercicio> ejercicios) {
        list_ejercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ejercicio= ejercicios.get(position);
                FragmentManager fragmentManager=getFragmentManager();

                DialogFragment dialogFragment= ListaEjerciciosDetalle.newInstance(ejercicio);
                dialogFragment.show(fragmentManager, "detalle");
            }
        });

        list_ejercicios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                ejercicio= ejercicios.get(position);
                FragmentManager fragmentManager=getFragmentManager();

                DialogFragment dialogFragment= ListaEjerciciosMenuLClick.newInstance( ejercicio);
                dialogFragment.show(fragmentManager, "menuEjercicios");

                return true;
            }
        });
    }


    @Override
    public void mostrarError() {

    }

    @Override
    public void mostrarEjercicios(List<Ejercicio> ejercicios) {
        num_ejercicios.setText("Numero de ejercicios: "+ ejercicios.size());
    }

}
