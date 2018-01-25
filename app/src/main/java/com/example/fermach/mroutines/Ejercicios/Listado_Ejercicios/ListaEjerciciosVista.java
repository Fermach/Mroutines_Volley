package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fermach.mroutines.Ejercicios.Crear_Editar_Ejercicios.CrearEjercicioVista;
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
                Bundle args = new Bundle();
                args.putSerializable(EJERCICIO, ejercicio);
                ListaEjerciciosDetalle toFragment = new ListaEjerciciosDetalle();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, toFragment, EJERCICIO)
                        .addToBackStack(EJERCICIO).commit();

            }
        });
    }


    @Override
    public void mostrarError() {


    }

}