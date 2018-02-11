package com.example.fermach.mroutines.Ejercicios.Editar_Ejercicios;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fermach.mroutines.Ejercicios.Crear_Ejercicios.CrearEjercicioContract;
import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.Modelos.Rutina.Rutina;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Listado_Rutinas.ListaRutinasVista;


/**
 * Esta es la vista del fragmento para editar rutinas
 * @author Fermach
 * @version 1.0.
 *
 */

public class EditarEjercicioVista extends Fragment implements EditarEjercicioContract.View{


    private EditarEjercicioContract.Presenter presenter;
    private EditText et_nombre_ejercicio;
    private EditText et_series_ejercicio;
    private EditText et_reps_ejercicio;
    private EditText et_duracion_ejercicio;
    private Spinner sp_tipo_ejercicio;
    private Spinner sp_unid_tiempo;
    private Button añadir_ejercicio;
    private Button cancelar_ejercicio;
    View myView;
    private Fragment fragment;
    String nombre="";
    String series;
    String repeticiones;
    String duracion;
    String tipo;
    Ejercicio ejercicioActualizar;
    Ejercicio ejercicio;
    private final String RUTINA ="RUTINA";


    public EditarEjercicioVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_editar_ejercicio, container, false);

        fragment = new EditarEjercicioVista();

        //recibimos el ejercicio a actualizar del fragmento padre
        Bundle args = getArguments();
        ejercicioActualizar =(Ejercicio) args
                .getSerializable("EJERCICIO");

        inicializarVistas();
        activarControladores();
        poblarSpinner();

        presenter=new EditarEjercicioPresenter(this);

        return myView;
    }


    public void inicializarVistas(){

        et_nombre_ejercicio=myView.findViewById(R.id.editText_nombreEjercicio_editar);
         et_nombre_ejercicio.setText(ejercicioActualizar.getNombre());

        et_series_ejercicio= myView.findViewById(R.id.editText_seriesEjercicio_editar);
        et_reps_ejercicio= myView.findViewById(R.id.editText_repesEjercicio_editar);
        et_duracion_ejercicio= myView.findViewById(R.id.editText_tiempoEjercicio_editar);

        añadir_ejercicio= myView.findViewById(R.id.btn_crear_formEjer_editar);
        cancelar_ejercicio= myView.findViewById(R.id.btn_cancelar_formEjer_editar);

        sp_tipo_ejercicio= myView.findViewById(R.id.spinner_tipoEjercicio_editar);
        sp_unid_tiempo=myView.findViewById(R.id.spinner_unidadesTiempo_editar);

    }

    public void activarControladores(){


        //si pulsamos sobre añadir ejercicio nos crea unna rutina con
        //los datos intoducidos en los campos y llama al presentador para que la actualice
        añadir_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = et_nombre_ejercicio.getText().toString();
                series = et_series_ejercicio.getText().toString();
                repeticiones = et_reps_ejercicio.getText().toString();
                duracion = et_duracion_ejercicio.getText().toString() + " " + sp_unid_tiempo.getSelectedItem().toString();
                tipo = sp_tipo_ejercicio.getSelectedItem().toString().toLowerCase();

                if (nombre.length() < 1) {
                    Snackbar.make(myView,"Debe introducir un nombre valido", Snackbar.LENGTH_SHORT).show();
                } else {

                    ejercicio = new Ejercicio(ejercicioActualizar.getId(), nombre, series, repeticiones, duracion, tipo, ejercicioActualizar.getRutina());

                    Log.i("Ejercicio a actualizar:", ejercicio.toString());
                    presenter.editarEjercicio(ejercicioActualizar.getId(),ejercicio);

                }
            }
        });

        //si pulsamos cancelar volvemos a la lista
        cancelar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new ListaEjerciciosVista();

                Bundle args = new Bundle();
                args.putSerializable(RUTINA, ejercicioActualizar.getRutina());
                fragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, fragment, RUTINA)
                        .addToBackStack(RUTINA).commit();
            }
        });
    }

    public void poblarSpinner(){
            String[] valores_tipo= {"Pierna","Torso", "Brazos","Pecho", "Espalda","Triceps","Biceps",
                    "Hombros","Gluteos","Biceps Femoral","Cuadriceps", "Gemelos", "FullBody"};

            String[] valores_unid_tiempo= {"min","seg","hor"};

            sp_tipo_ejercicio.setAdapter(new ArrayAdapter<String>
                    (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_tipo));

            sp_unid_tiempo.setAdapter(new ArrayAdapter<String>
                    (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_unid_tiempo));



    }


    @Override
    public void onEjercicioActualizadoError() {
        Snackbar.make(myView,"No se ha podido actualizar el ejercicio",Snackbar.LENGTH_SHORT);

    }

    @Override
    public void onEjercicioActualizado() {

        Snackbar.make(myView, "Se ha actualizado el ejercicio correctamente", Snackbar.LENGTH_LONG).show();

        // cuando se actualiza la rutina lanzamos un snackbar junto a un hilo que nos lleve a la lista en 1,2 sec

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 1.2 segundos, pasamos a la actividad principal de la aplicación
                fragment = new ListaEjerciciosVista();

                Bundle args = new Bundle();
                args.putSerializable(RUTINA, ejercicioActualizar.getRutina());
                fragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, fragment, RUTINA)
                        .addToBackStack(RUTINA).commit();
            };
        }, 1200);
    }
}
