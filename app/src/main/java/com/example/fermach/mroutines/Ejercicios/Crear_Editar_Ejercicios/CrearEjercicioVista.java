package com.example.fermach.mroutines.Ejercicios.Crear_Editar_Ejercicios;

import android.os.Bundle;
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

import com.example.fermach.mroutines.Ejercicios.Listado_Ejercicios.ListaEjerciciosVista;
import com.example.fermach.mroutines.Modelos.Ejercicio.Ejercicio;
import com.example.fermach.mroutines.R;
import com.example.fermach.mroutines.Rutinas.Crear_Editar_Rutinas.CrearRutinaVista;

import java.util.List;


/**
 * Created by Fermach on 18/01/2018.
 */

public class CrearEjercicioVista extends Fragment implements CrearEjercicioContract.View{

    private CrearEjercicioContract.Presenter presenter;
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
    private TextView num_ejercicios;
    String nombre="";
    String series;
    String repeticiones;
    String duracion;
    String tipo;
    String rutina;
    Ejercicio ejercicio;
    private final String RUTINA ="RUTINA";

    public CrearEjercicioVista() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.formulario_ejercicio, container, false);

        fragment = new CrearEjercicioVista();

        Bundle args = getArguments();

        if((args.getSerializable("RUTINA_NOMBRE"))!=null) {

            rutina = (String) args.getSerializable("RUTINA_NOMBRE");
            Log.i("Argumentos", "RECOGIDOS =" + rutina);
        }else{
            Log.i("Argumentos", "NULOS" );

        }

        inicializarVistas();
        activarControladores();
        poblarSpinner();

        presenter=new CrearEjercicioPresenter(this);
        presenter.cargaEjercicios();

        return myView;
    }


    public void inicializarVistas(){

        et_nombre_ejercicio=myView.findViewById(R.id.editText_nombreEjercicio);
        et_series_ejercicio= myView.findViewById(R.id.editText_seriesEjercicio);
        et_reps_ejercicio= myView.findViewById(R.id.editText_repesEjercicio);
        et_duracion_ejercicio= myView.findViewById(R.id.editText_tiempoEjercicio);
        añadir_ejercicio= myView.findViewById(R.id.btn_crear_formEjer);
        cancelar_ejercicio= myView.findViewById(R.id.btn_cancelar_formEjer);
        sp_tipo_ejercicio= myView.findViewById(R.id.spinner_tipoEjercicio);
        num_ejercicios=myView.findViewById(R.id.num_Ejercicios);
        sp_unid_tiempo=myView.findViewById(R.id.spinner_unidadesTiempo);
    }

    public void activarControladores(){

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

                    String id = nombre + rutina;
                    ejercicio = new Ejercicio(id, nombre, series, repeticiones, duracion, tipo, rutina);

                    Log.i("Ejercicio creado:", ejercicio.toString());
                    presenter.crearEjercicio(ejercicio);


                    fragment = new ListaEjerciciosVista();

                    Bundle args = new Bundle();
                    args.putSerializable(RUTINA, rutina);
                    fragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_main, fragment, RUTINA)
                            .addToBackStack(RUTINA).commit();
                }
            }
        });

        cancelar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new ListaEjerciciosVista();

                Bundle args = new Bundle();
                args.putSerializable(RUTINA, rutina);
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
                "Hombros","Gluteos","Biceps Femoral","Cuadriceps", "Gemelos"};

        String[] valores_unid_tiempo= {"min","seg","hor"};

        sp_tipo_ejercicio.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_tipo));

        sp_unid_tiempo.setAdapter(new ArrayAdapter<String>
                (getContext(),R.layout.support_simple_spinner_dropdown_item,valores_unid_tiempo));


    }

    @Override
    public void mostrarEjercicios(List<Ejercicio> ejercicios) {
        num_ejercicios.setText("Numero de ejercicios: "+ ejercicios.size());
    }

    @Override
    public void mostrarError() {
    }
}
