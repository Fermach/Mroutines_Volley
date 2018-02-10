package com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
 *
 * Fragmento con la vista de la lista de ejercicios
 * @author Fermach
 * @version 1.0.
 *
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
    private String id_ejercicio_eliminar;

    public ListaEjerciciosVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lista_ejercicios, container, false);

        id_ejercicio_eliminar=null;
        fragment = new ListaEjerciciosVista();

        //recibimos la rutina sobre la cual buscar los ejercicios
        //del fragmento padre
        Bundle args = getArguments();
        rutina_nombre =(String) args
                .getSerializable("RUTINA");

        //Si venimos de borrar ejercicio, recibimos el ejercicio a eliminar, de lo contrario
        //recibiremos null
        id_ejercicio_eliminar=(String)
                args.getSerializable("ID_EJERCICIO_A_ELIMINAR");
        Log.i("Argumentos01", "RECOGIDOS =" + id_ejercicio_eliminar);


        inicializarVistas();
        activarControladores();

        presenter=new ListaEjerciciosPresenter(this);
        presenter.cargaEjercicios(rutina_nombre);

        //si recibimos un ejercicio para eliminar lo eliminamos
        if(id_ejercicio_eliminar!=null){
            Log.i("Argumentos02", "RECOGIDOS =" + id_ejercicio_eliminar);
            presenter.borrarEjercicio(id_ejercicio_eliminar);

        }
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
                //Al clickar sobre el fab nos lleva al menu para crear ejercicios pasandole la rutina
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

                //Cuando clickemos en un un ejercicio de la lista nos lleva al fragmento con
                //el detalle de ese ejercicio pasandole el ejercicio
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


                //Cuando hagamos long click en la lista de ejercicios nos lleva al fragmento con
                //el menu de borrar o editar pasandole el ejercicio como instancia
                DialogFragment dialogFragment= ListaEjerciciosMenuLClick.newInstance(rutina_nombre, ejercicio);
                dialogFragment.show(fragmentManager, "menuEjercicios");

                return true;
            }
        });
    }


    @Override
    public void onEjercicioEliminado() {
          presenter.cargaEjercicios(rutina_nombre);
    }

    @Override
    public void mostrarEjercicios(List<Ejercicio> ejercicios) {
        num_ejercicios.setText("Numero de ejercicios: "+ ejercicios.size());
    }

}
